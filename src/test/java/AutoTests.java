import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

public class AutoTests {
    private static final String IS_BIG = "isBig";
    private static final String SPENT_TOTAL = "spentTotal";
    private static final String CLIENT_ID = "clientId";
    private static final String FILENAME_10010 = "outbox/10010.json";
    private static final String FILENAME_10006 = "outbox/10006.json";
    private static final String FILENAME_10001 = "outbox/10001.json";
    private static final String FILENAME_10002 = "outbox/10002.json";
    private static final Long SPENT_TOTAL__10010 = 15150L;
    private static final Long SPENT_TOTAL__10006 = 14950L;
    private static final Long SPENT_TOTAL__10001 = 1045L;
    private static final Long SPENT_TOTAL__10002 = 5009950000L;

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
