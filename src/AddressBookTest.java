import org.junit.Before;
import org.junit.Test;

public class AddressBookTest {
    private AddressBook addressbook;
    private BuddyInfo firstBuddy;
    private BuddyInfo secondBuddy;

    @Before
    public void setup() {
        addressbook = new AddressBook();
        firstBuddy = new BuddyInfo("b1", "a1", "p1");
        secondBuddy = new BuddyInfo("b2", "a2", "p2");
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