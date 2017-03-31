package com.simbirsoft.drools.clienthandler.service;

import com.simbirsoft.drools.clienthandler.service.file.ClientStorage;
import com.simbirsoft.drools.clienthandler.service.file.LoadClientException;
import com.simbirsoft.drools.clienthandler.service.file.StoreClientException;
import com.simbirsoft.drools.clienthandler.model.Client;
import com.simbirsoft.drools.clienthandler.model.ClientResult;
import com.simbirsoft.drools.clienthandler.model.DroolsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("DroolsClientProcessing")
public class ClientProcessingServiceImpl implements ClientProcessingService {

    private final ClientStorage clientStorage;
    private final DroolsClientsService droolsClientsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientProcessingServiceImpl.class);

    @Autowired
    public ClientProcessingServiceImpl(ClientStorage clientStorage, DroolsClientsService droolsClientsService) {
        this.clientStorage = clientStorage;
        this.droolsClientsService = droolsClientsService;
    }

    @Override
    public void startProcess() {
        List<DroolsClient> clients = new ArrayList<>();
        while (clientStorage.hasNextClient()) {
            try {
                Client client = clientStorage.loadNextClient();
                ClientResult result = new ClientResult();
                result.setClientId(client.getClientId());
                DroolsClient droolsClient = new DroolsClient(client, result);
                clients.add(droolsClient);
            } catch (LoadClientException e) {
                LOGGER.error("Не возможно было загрузить данные о клиенте.", e);
            }
        }

        droolsClientsService.process(clients);

        for (DroolsClient client : clients) {
            try {
                clientStorage.storeClientResult(client.getClientResult());
                clientStorage.markProcessed(client.getClient());
            } catch (StoreClientException e) {
                LOGGER.error("Не удалось сохранить результаты обработки", e);
            }
        }

    }
}
