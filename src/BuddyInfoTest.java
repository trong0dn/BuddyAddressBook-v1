import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BuddyInfoTest {

    private static final String BUDDY_NAME = "name";
    private static final String BUDDY_ADDRESS = "address";
    private static final String BUDDY_PHONE = "phone";
    private BuddyInfo testBuddyInfo = null;

    @Before
    public void setUp() {
        testBuddyInfo = new BuddyInfo(BUDDY_NAME, BUDDY_ADDRESS, BUDDY_PHONE);
    }

    @Test
    public void testToXML() {
        String xmlString = testBuddyInfo.toXML();
        assertTrue("Must contain BuddyInfo opening tag", xmlString.contains("<" + BuddyInfo.BUDDY_TAG + ">" ));
        assertTrue("Must contain BuddyInfo closing tag", xmlString.contains("</" + BuddyInfo.BUDDY_TAG + ">" ));
        assertTrue("Must contain name tag", xmlString.contains("<" + BuddyInfo.NAME_TAG + ">" + testBuddyInfo.getName() + "</" + BuddyInfo.NAME_TAG + ">"));
        assertTrue("Must contain address tag", xmlString.contains("<" + BuddyInfo.ADDRESS_TAG + ">" + testBuddyInfo.getAddress() + "</" + BuddyInfo.ADDRESS_TAG + ">"));
        assertTrue("Must contain phone tag", xmlString.contains("<" + BuddyInfo.PHONE_TAG + ">" + testBuddyInfo.getPhoneNumber() + "</" + BuddyInfo.PHONE_TAG + ">"));
    }

    @Test
    public void testEquals() {
        BuddyInfo sameBuddy = new BuddyInfo(testBuddyInfo.getName(), testBuddyInfo.getAddress(), testBuddyInfo.getPhoneNumber());
        BuddyInfo differentAddress = new BuddyInfo(testBuddyInfo.getName(), "", testBuddyInfo.getPhoneNumber());
        BuddyInfo differentName = new BuddyInfo("", testBuddyInfo.getAddress(), testBuddyInfo.getPhoneNumber());
        BuddyInfo differentPhone = new BuddyInfo(testBuddyInfo.getName(), testBuddyInfo.getAddress(), "");

        // Check equal method returns true for the same object
        assertEquals(testBuddyInfo, testBuddyInfo);
        assertEquals(testBuddyInfo, sameBuddy);
        assertNotEquals(testBuddyInfo, differentAddress);
        assertNotEquals(testBuddyInfo, differentName);
        assertNotEquals(testBuddyInfo, differentPhone);
    }

    @Test
    public void testGetName() {
        assertEquals("Buddy name must be set", testBuddyInfo.getName(), BUDDY_NAME);
    }

    @Test
    public void testSetName() {
        assertEquals("Buddy name must already be set", testBuddyInfo.getName(), BUDDY_NAME);
        String newName = "Tester";
        testBuddyInfo.setName(newName);
        assertEquals("Buddy name must be set to new name", testBuddyInfo.getName(), newName);
    }

    @Test
    public void testGetAddress() {
        assertEquals("Buddy address must be set", testBuddyInfo.getAddress(), BUDDY_ADDRESS);
    }

    @Test
    public void testSetAddress() {
        assertEquals("Buddy address must already be set", testBuddyInfo.getAddress(), BUDDY_ADDRESS);
        String newAddress = "tester_new";
        testBuddyInfo.setName(newAddress);
        assertEquals("Buddy address must be set to new address", testBuddyInfo.getName(), newAddress);
    }

    @Test
    public void testGetPhone() {
        assertEquals("Buddy phone must be set", testBuddyInfo.getPhoneNumber(), BUDDY_PHONE);
    }

    @Test
    public void testSetPhone() {
        assertEquals("Buddy phone must already be set", testBuddyInfo.getAddress(), BUDDY_ADDRESS);
        String newPhone = "Tester";
        testBuddyInfo.setName(newPhone);
        assertEquals("Buddy Phone must be set to new phone number", testBuddyInfo.getName(), newPhone);
    }
}