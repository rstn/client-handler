import com.simbirsoft.drools.clienthandler.service.ClientProcessingService;
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
public class AutoTests {
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
    private ClientProcessingService clientProcessingMachine;
    
    private static boolean isRun = false;
    
    @Before
    public void createClientResults(){
        if (!isRun) {
            File file = new File(OUTPUT_DIR);
            if (file.exists()){
                File[] fileList = file.listFiles((File f, String name) -> name.matches("^\\d*.json$"));
                for(File f : fileList){
                    f.delete();
                }
            }
            clientProcessingMachine.startProcess();
            isRun = true;
        }
    }

    @Test
    public void testOutClientIdEqualsInClientId10010() {
        JSONParser parser = new JSONParser();
        Long id = null;
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(FILENAME_10010));
            id = (Long) object.get(CLIENT_ID);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(10010L == id);
    }

    @Test
    public void testSpentTotalCheck10002() {
        JSONParser parser = new JSONParser();
        Long spentTotal = 0L;
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(FILENAME_10002));
            spentTotal = (Long) object.get(SPENT_TOTAL);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(Objects.equals(SPENT_TOTAL__10002, spentTotal));
    }

    @Test
    public void testSpentTotalCheck10010() {
        JSONParser parser = new JSONParser();
        Long spentTotal = 0L;
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(FILENAME_10010));
            spentTotal = (Long) object.get(SPENT_TOTAL);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(Objects.equals(SPENT_TOTAL__10010, spentTotal));
    }

    @Test
    public void testSpentTotalCheck10006() {
        JSONParser parser = new JSONParser();
        Long spentTotal = 0L;
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(FILENAME_10006));
            spentTotal = (Long) object.get(SPENT_TOTAL);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(Objects.equals(SPENT_TOTAL__10006, spentTotal));
    }

    @Test
    public void testSpentTotalCheck10001() {
        JSONParser parser = new JSONParser();
        Long spentTotal = 0L;
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(FILENAME_10001));
            spentTotal = (Long) object.get(SPENT_TOTAL);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(Objects.equals(SPENT_TOTAL__10001, spentTotal));
    }

    @Test
    public void testIsBigFalse10001() {
        JSONParser parser = new JSONParser();
        Boolean isBig = false;
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(FILENAME_10001));
            isBig = (Boolean) object.get(IS_BIG);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(!isBig);
    }

    @Test
    public void testIsBigTrue10010() {
        JSONParser parser = new JSONParser();
        Boolean isBig = false;
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(FILENAME_10010));
            isBig = (Boolean) object.get(IS_BIG);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(isBig);
    }

    @Test
    public void testIsBigFalse10006() {
        JSONParser parser = new JSONParser();
        Boolean isBig = false;
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(FILENAME_10006));
            isBig = (Boolean) object.get(IS_BIG);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(!isBig);
    }

    @Test
    public void testIsBigTrue10002() {
        JSONParser parser = new JSONParser();
        Boolean isBig = false;
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(FILENAME_10002));
            isBig = (Boolean) object.get(IS_BIG);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(isBig);
    }
}
