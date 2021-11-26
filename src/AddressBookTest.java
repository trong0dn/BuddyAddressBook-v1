import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class AddressBookTest {
    private AddressBook emptyAddressBook;
    private AddressBook oneBuddyAddressBook;
    private AddressBook twoBuddyAddressBook;
    private AddressBook threeBuddyAddressBook;

    private final BuddyInfo firstBuddy = new BuddyInfo("Buddy1", "Address1", "Phone1");
    private final BuddyInfo secondBuddy = new BuddyInfo("Buddy2", "Address2", "Phone2");
    private final BuddyInfo thirdBuddy = new BuddyInfo("Buddy3", "Address3", "Phone3");

    @Before
    public void setUp() {
        emptyAddressBook = new AddressBook();

        oneBuddyAddressBook = new AddressBook();
        oneBuddyAddressBook.addBuddy(firstBuddy);

        twoBuddyAddressBook = new AddressBook();
        twoBuddyAddressBook.addBuddy(firstBuddy);
        twoBuddyAddressBook.addBuddy(secondBuddy);

        threeBuddyAddressBook = new AddressBook();
        threeBuddyAddressBook.addBuddy(firstBuddy);
        threeBuddyAddressBook.addBuddy(secondBuddy);
        threeBuddyAddressBook.addBuddy(thirdBuddy);
    }

    @Test
    public void testSize() {
        assertEquals(emptyAddressBook.size(), 0);
        assertEquals(oneBuddyAddressBook.size(), 1);
        assertEquals(twoBuddyAddressBook.size(), 2);
        assertEquals(threeBuddyAddressBook.size(), 3);
    }

    @Test
    public void testEmptyXMLImport() {
        emptyAddressBook.exportToXMLFile();
        AddressBook importedBook = AddressBook.importFromXMLFile();
        assertEquals(emptyAddressBook, importedBook);
    }

    @Test
    public void testOneXMLImport() {
        oneBuddyAddressBook.exportToXMLFile();
        AddressBook importedBook = AddressBook.importFromXMLFile();
        assertEquals(oneBuddyAddressBook, importedBook);
    }

    @Test
    public void testTwoXMLImport() {
        twoBuddyAddressBook.exportToXMLFile();
        AddressBook importedBook = AddressBook.importFromXMLFile();
        assertEquals(twoBuddyAddressBook, importedBook);
    }

    @Test
    public void testThreeXMLImport() {
        threeBuddyAddressBook.exportToXMLFile();
        AddressBook importedBook = AddressBook.importFromXMLFile();
        assertEquals(threeBuddyAddressBook, importedBook);
    }

    @Test
    public void testToXML() {
        String toXML = threeBuddyAddressBook.toXML();
        assertTrue("Needs opening book tag", toXML.contains("<" + AddressBook.ADDRESS_BOOK_TAG + ">"));
        assertTrue("Needs closing book tag", toXML.contains("</" + AddressBook.ADDRESS_BOOK_TAG + ">"));

        for (int i = 0; i < threeBuddyAddressBook.size(); i++) {
            assertTrue("Needs Buddy", toXML.contains(threeBuddyAddressBook.get(i).toXML()));
        }
    }

    @Test
    public void testXMLImportSerializable() throws IOException {
        threeBuddyAddressBook.exportToXMLFile();
        File xmlFile = new File(AddressBook.ADDRESS_BOOK_XML_FILE);

        // Compare input stream to XML
        FileInputStream fis = new FileInputStream(xmlFile);

        // Comparing the bytecode in the XML file
        byte[] data = new byte[(int) xmlFile.length()];
        if (fis.read(data) == -1) {
            Assert.fail("Failed to read XML file");
        }
        fis.close();
        String str = new String(data);
        assertEquals(threeBuddyAddressBook.toXML(), str);
    }

    @Test
    public void testEmptyExportAndImport() {
        emptyAddressBook.save();
        AddressBook importedBook = AddressBook.importAddressBook();
        assertEquals(emptyAddressBook, importedBook);
    }

    @Test
    public void testOneExportAndImport() {
        oneBuddyAddressBook.save();
        AddressBook importedBook = AddressBook.importAddressBook();
        assertEquals(oneBuddyAddressBook, importedBook);
    }

    @Test
    public void testTwoExportAndImport() {
        twoBuddyAddressBook.save();
        AddressBook importedBook = AddressBook.importAddressBook();
        assertEquals(twoBuddyAddressBook, importedBook);
    }

    @Test
    public void testThreeExportAndImport() {
        threeBuddyAddressBook.save();
        AddressBook importedBook = AddressBook.importAddressBook();
        assertEquals(threeBuddyAddressBook, importedBook);
    }

    @Test
    public void testClear() {
        emptyAddressBook.clear();
        oneBuddyAddressBook.clear();
        twoBuddyAddressBook.clear();
        threeBuddyAddressBook.clear();

        assertEquals(emptyAddressBook.size(), 0);
        assertEquals(oneBuddyAddressBook.size(), 0);
        assertEquals(twoBuddyAddressBook.size(), 0);
        assertEquals(threeBuddyAddressBook.size(), 0);
    }

    @After
    public void tearDown() {
        // Clear existing serializations
        File txtfile = new File(AddressBook.ADDRESS_BOOK_TXT_FILE);
        if (!txtfile.delete()) {
            txtfile.deleteOnExit();
        }

        File xmLfile = new File(AddressBook.ADDRESS_BOOK_XML_FILE);
        if (!xmLfile.delete()) {
            xmLfile.deleteOnExit();
        }
    }
}