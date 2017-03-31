package com.simbirsoft.drools.clienthandler.service;

import com.simbirsoft.drools.clienthandler.model.Client;
import com.simbirsoft.drools.clienthandler.model.ClientResult;
import com.simbirsoft.drools.clienthandler.model.DroolsClient;
import com.simbirsoft.drools.clienthandler.model.Subscriber;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class DroolsClientsServiceImplTest {

    private Client testSumClient;

    private Client testBigClient;

    @Before
    public void init() {

        testSumClient = new Client();
        testSumClient.setClientId(1L);

        List<Subscriber> subscribers = new ArrayList<Subscriber>();
        for (int i = 1; i < 6; i++) {
            Subscriber subscriber = new Subscriber();
            subscriber.setId(i);
            subscriber.setSpent(5L);
            subscribers.add(subscriber);
        }
        testSumClient.setSubscribers(subscribers);

        testBigClient = new Client();
        testBigClient.setClientId(2L);

        subscribers = new ArrayList<Subscriber>();
        for (int i = 1; i < 102; i++) {
            Subscriber subscriber = new Subscriber();
            subscriber.setId(i);
            subscriber.setSpent(100L);
            subscribers.add(subscriber);
        }
        testBigClient.setSubscribers(subscribers);
    }

    @Autowired
    @Qualifier("DroolsClientsServiceImpl")
    DroolsClientsService droolsClientsService;


    @Test
    public void testSpentTotal() throws Exception {

        DroolsClient droolsClient = new DroolsClient(testSumClient, new ClientResult());

        droolsClientsService.process(newArrayList(droolsClient));

        assertEquals(25L, droolsClient.getClientResult().getSpentTotal());
    }

    @Test
    public void testIsBig() throws Exception {

        DroolsClient droolsClient = new DroolsClient(testBigClient, new ClientResult());

        droolsClientsService.process(newArrayList(droolsClient));

        assertTrue(droolsClient.getClientResult().isBig());
    }
}
