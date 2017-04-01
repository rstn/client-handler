package com.simbirsoft.drools.clienthandler.service.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
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

    private static final String FILE_TEMPLATE = "\\%d.json";

    @Autowired
    private ObjectMapper mapper;

    private final File[] inboxFiles;
    private final File inboxDir;
    private final File outboxDir;
    private final File processedDir;
    private int currentInboxPos = 0;

    public FileClientStorage(@Value("${inbox.dir}") String inboxDirPath,
                             @Value("${outbox.dir}") String outboxDirPath,
                             @Value("${processed.dir}") String processedDirPath) {
        checkNotNull(inboxDirPath, "Директория inbox не задана");
        checkNotNull(outboxDirPath, "Директория outbox не задана");
        inboxDir = new File(inboxDirPath);
        checkArgument(inboxDir.exists() && inboxDir.isDirectory() && inboxDir.canRead(),
                "Входная директория указана не верно или нет прав на чтение");

        outboxDir = new File(outboxDirPath);
        if (outboxDir.exists()) {
            checkArgument(outboxDir.isDirectory() && outboxDir.canWrite(),
                    "Выходная директория указана не верно или нет прав на запись");
        } else {
            outboxDir.mkdir();
        }
        if (!"".equals(processedDirPath)) {
            processedDir = new File(processedDirPath);
            if (processedDir.exists()) {
                checkArgument(processedDir.isDirectory() && processedDir.canWrite(),
                        "Директория с обработанными входными файлами указана не верно или нет прав на запись");
            } else {
                processedDir.mkdir();
            }
        } else {
            processedDir = null;
        }

        inboxFiles = inboxDir.listFiles((File f, String name) -> name.matches("^\\d*.json$"));

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
        File output = new File(outboxDir, String.format(FILE_TEMPLATE, clientResult.getClientId()));
        try {
            mapper.writeValue(output, clientResult);
        } catch (IOException ex) {
            throw new StoreClientException("Не удалось записать результаты в файл", ex);
        }
    }

    @Override
    public void markProcessed(Client client) throws StoreClientException {
        if (processedDir != null) {
            String fileName = String.format(FILE_TEMPLATE, client.getClientId());
            File inputFile = new File(inboxDir, fileName);
            File processedFile = new File(processedDir, fileName);
            try {
                Files.move(inputFile, processedFile);
            } catch (IOException ex) {
                throw new StoreClientException("Не удалось перенести обработанный файл в другую директорию", ex);
            }
        }
    }
}
