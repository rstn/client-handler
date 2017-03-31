package com.simbirsoft.drools.clienthandler.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simbirsoft.drools.clienthandler.model.Client;
import com.simbirsoft.drools.clienthandler.model.ClientResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class FileClientStorage implements ClientStorage {

    private static final String OUTPUT_FILE_TEMPLATE = "\\%d.json";

    private ObjectMapper mapper = new ObjectMapper();

    private File[] inboxFiles;
    private File outboxDir;
    private int currentInboxPos = -1;

    public FileClientStorage(@Value("${inbox.dir}") String inboxDirPath, @Value("${outbox.dir}") String outboxDirPath) {
        checkNotNull(inboxDirPath, "Директория inbox не задана");
        checkNotNull(outboxDirPath, "Директория outbox не задана");
        //TODO добавить другие проверки

        File inboxDir = new File(inboxDirPath);

        this.inboxFiles = inboxDir.listFiles((File f, String name) -> name.matches("^\\d*.json$"));
        this.outboxDir = new File(outboxDirPath);
    }

    @Override
    public boolean hasNextClient() {
        return currentInboxPos + 1 < inboxFiles.length;
    }

    @Override
    public Client loadNextClient() throws LoadClientException {
        if (currentInboxPos + 1 == inboxFiles.length) {
            throw new LoadClientException("Входных файлов с клиентами больше нет");
        }

        currentInboxPos++;
        try {
            return mapper.readValue(inboxFiles[currentInboxPos], Client.class);
        } catch (IOException ex) {
            throw new LoadClientException("Не возможно прочитать входной файл", ex);
        }
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
