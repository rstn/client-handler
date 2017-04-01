package com.simbirsoft.drools.clienthandler.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class ClientProcessingServiceTest {
    private static final String IS_BIG = "isBig";
    private static final String SPENT_TOTAL = "spentTotal";
    private static final String CLIENT_ID = "clientId";
    private static final String OUTPUT_DIR = "src\\test\\resources\\outbox";
    private static final String FILENAME_10010 = OUTPUT_DIR + "\\10010.json";
    private static final String FILENAME_10006 = OUTPUT_DIR + "\\10006.json";
    private static final String FILENAME_10001 = OUTPUT_DIR + "\\10001.json";
    private static final String FILENAME_10002 = OUTPUT_DIR + "\\10002.json";
    private static final Long SPENT_TOTAL__10010 = 15150L;
    private static final Long SPENT_TOTAL__10006 = 14950L;
    private static final Long SPENT_TOTAL__10001 = 1045L;
    private static final Long SPENT_TOTAL__10002 = 5009950000L;
    

    @Autowired
    private ClientProcessingService clientProcessingService;
    
    private static boolean isRun = false;

    private void deleteFiles(String path) {
        File file = new File(path);
        if (file.exists()){
            File[] fileList = file.listFiles((File f, String name) -> name.matches("^\\d*.json$"));
            for(File f : fileList){
                f.delete();
            }
        }
    }

    private Long getLongProperty(String name, String file, Long def) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(file));
            return (Long) object.get(name);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return def;
    }

    private Boolean getBoolProperty(String name, String file, Boolean def) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(file));
            return (Boolean) object.get(name);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return def;
    }
    
    @Before
    public void createClientResults(){
        if (!isRun) {
            deleteFiles(OUTPUT_DIR);
            clientProcessingService.startProcess();
            isRun = true;
        }
    }

    @Test
    public void testOutClientIdEqualsInClientId10010() {
        Long id = getLongProperty(CLIENT_ID, FILENAME_10010, null);
        Assert.assertTrue(10010L == id);
    }

    @Test
    public void testSpentTotalCheck10002() {
        Long spentTotal = getLongProperty(SPENT_TOTAL, FILENAME_10002, 0L);
        Assert.assertTrue(Objects.equals(SPENT_TOTAL__10002, spentTotal));
    }

    @Test
    public void testSpentTotalCheck10010() {
        Long spentTotal = getLongProperty(SPENT_TOTAL, FILENAME_10010, 0L);
        Assert.assertTrue(Objects.equals(SPENT_TOTAL__10010, spentTotal));
    }

    @Test
    public void testSpentTotalCheck10006() {
        Long spentTotal = getLongProperty(SPENT_TOTAL, FILENAME_10006, 0L);
        Assert.assertTrue(Objects.equals(SPENT_TOTAL__10006, spentTotal));
    }

    @Test
    public void testSpentTotalCheck10001() {
        Long spentTotal = getLongProperty(SPENT_TOTAL, FILENAME_10001, 0L);
        Assert.assertTrue(Objects.equals(SPENT_TOTAL__10001, spentTotal));
    }

    @Test
    public void testIsBigFalse10001() {
        Boolean isBig = getBoolProperty(IS_BIG, FILENAME_10001, false);;
        Assert.assertTrue(!isBig);
    }

    @Test
    public void testIsBigTrue10010() {
        Boolean isBig = getBoolProperty(IS_BIG, FILENAME_10010, false);
        Assert.assertTrue(isBig);
    }

    @Test
    public void testIsBigFalse10006() {
        Boolean isBig = getBoolProperty(IS_BIG, FILENAME_10006, false);;
        Assert.assertTrue(!isBig);
    }

    @Test
    public void testIsBigTrue10002() {
        Boolean isBig = getBoolProperty(IS_BIG, FILENAME_10002, false);;
        Assert.assertTrue(isBig);
    }
}
