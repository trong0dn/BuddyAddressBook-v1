import org.junit.Before;
import org.junit.Test;

public class AddressBookTest {
    private AddressBook addressbook;
    private BuddyInfo firstBuddy;
    private BuddyInfo secondBuddy;
    private BuddyInfo thirdBuddy;

    @Before
    public void setup() {
        addressbook = new AddressBook();
        firstBuddy = new BuddyInfo("b1", "a1", "p1");
        secondBuddy = new BuddyInfo("b2", "a2", "p2");
        thirdBuddy = new BuddyInfo("b3", "a3", "p3");
        addressbook.addBuddy(firstBuddy);
        addressbook.addBuddy(secondBuddy);
        addressbook.addBuddy(thirdBuddy);
    }

    @Test
    public void testSave() {
        addressbook.save("test.txt");
    }

    @Test
    public void testReadTxtImport() {
    }

    @Test
    public void testExportToXmlFile() {
    }

    @Test
    public void testImportFromXmlFile() {
    }
}