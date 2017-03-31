package com.simbirsoft.drools.clienthandler.service.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simbirsoft.drools.clienthandler.model.Client;
import com.simbirsoft.drools.clienthandler.model.ClientResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class FileClientStorage implements ClientStorage {

    private static final String OUTPUT_FILE_TEMPLATE = "\\%d.json";

    @Autowired
    private ObjectMapper mapper;

    private final File[] inboxFiles;
    private final File outboxDir;
    private int currentInboxPos = 0;

    public FileClientStorage(@Value("${inbox.dir}") String inboxDirPath, @Value("${outbox.dir}") String outboxDirPath) {
        checkNotNull(inboxDirPath, "Директория inbox не задана");
        checkNotNull(outboxDirPath, "Директория outbox не задана");
        File inboxDir = new File(inboxDirPath);
        checkArgument(inboxDir.exists() && inboxDir.isDirectory() && inboxDir.canRead(),
                "Входная директория указана не верно или нет прав на чтение");

        outboxDir = new File(outboxDirPath);
        if (!outboxDir.exists()) {
            outboxDir.mkdir();
        }

        this.inboxFiles = inboxDir.listFiles((File f, String name) -> name.matches("^\\d*.json$"));

    }

    @Override
    public boolean hasNextClient() {
        return currentInboxPos < inboxFiles.length;
    }

    @Override
    public Client loadNextClient() throws LoadClientException {
        if (currentInboxPos == inboxFiles.length) {
            throw new LoadClientException("Входных файлов с клиентами больше нет");
        }

        Client client;
        try {
            client = mapper.readValue(inboxFiles[currentInboxPos], Client.class);
        } catch (IOException ex) {
            throw new LoadClientException("Не возможно прочитать входной файл", ex);
        }
        currentInboxPos++;
        return client;
    }

    @Override
    public void storeClientResult(ClientResult clientResult) throws StoreClientException {
        File output = new File(outboxDir, String.format(OUTPUT_FILE_TEMPLATE, clientResult.getClientId()));
        try {
            mapper.writeValue(output, clientResult);
        } catch (IOException ex) {
            throw new StoreClientException("Не удалось записать результаты в файл", ex);
        }
    }
}
