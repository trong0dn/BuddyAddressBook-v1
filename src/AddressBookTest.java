import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

import static org.junit.Assert.*;

public class AddressBookTest {

    private AddressBook emptyAddressBook = null;
    private AddressBook oneBuddyAddressBook = null;
    private AddressBook twoBuddyAddressBook = null;

    private BuddyInfo firstBuddy = new BuddyInfo("Buddy1", "", "");
    private BuddyInfo secondBuddy = new BuddyInfo("Buddy2", "", "");


    @Before
    public void setUp() {
        emptyAddressBook = new AddressBook();

        oneBuddyAddressBook = new AddressBook();
        oneBuddyAddressBook.addBuddy(firstBuddy);

        twoBuddyAddressBook = new AddressBook();
        twoBuddyAddressBook.addBuddy(firstBuddy);
        twoBuddyAddressBook.addBuddy(secondBuddy);


        // Remove any existing serializations
        File file = new File(AddressBook.ADDRESS_BOOK_TXT_FILE);
        file.delete();

        file = new File(AddressBook.ADDRESS_BOOK_XML_FILE);
        file.delete();
    }

    @Test
    public void testSize()
    {
        assertEquals(emptyAddressBook.size(), 0);
        assertEquals(oneBuddyAddressBook.size(), 1);
        assertEquals(twoBuddyAddressBook.size(), 2);
    }

    @Test
    public void testXMLExport() throws IOException, ClassNotFoundException {
        testXMLExport(emptyAddressBook);
        testXMLExport(oneBuddyAddressBook);
        testXMLExport(twoBuddyAddressBook);
    }
/*
    @Test
    public void testEmptyXMLImport() throws IOException, ParserConfigurationException, SAXException {
        emptyAddressBook.exportToXMLFile();
        testXMLImport(emptyAddressBook);
    }

    @Test
    public void testOneXMLImport() throws IOException, ParserConfigurationException, SAXException {
        oneBuddyAddressBook.exportToXMLFile();
        testXMLImport(oneBuddyAddressBook);
    }

    @Test
    public void testTwoXMLImport() throws IOException, ParserConfigurationException, SAXException {
        twoBuddyAddressBook.exportToXMLFile();
        testXMLImport(twoBuddyAddressBook);
    }

    public void testXMLImport() throws IOException, ParserConfigurationException, SAXException {
        AddressBook importedBook = AddressBook.importFromXMLFile();
        assertEquals(addressBook, importedBook);
    }
*/
  /*  @Test
    public void testToXML()
    {
        testToXML(emptyAddressBook);
        testToXML(oneBuddyAddressBook);
        testToXML(twoBuddyAddressBook);
    }*/
/*
    @Test
    public void testToXML()
    {
        String toXML = addressBook.toXML();
        assertTrue("Needs opening book tag", toXML.contains("<" + AddressBook.ADDRESS_BOOK_TAG + ">"));
        assertTrue("Needs closing book tag", toXML.contains("</" + AddressBook.ADDRESS_BOOK_TAG + ">"));

        for (int i = 0; i < addressBook.size(); i++) {
            assertTrue("Needs Buddy", toXML.contains(addressBook.get(i).toXML()));
        }
    }*/

    private void testXMLExport(AddressBook addressBook) throws IOException, ClassNotFoundException {
        addressBook.exportToXMLFile();
        File xmlFile = new File(AddressBook.ADDRESS_BOOK_XML_FILE);

        // Just read in the whole file and compare to toXML
        FileInputStream fis = new FileInputStream(xmlFile);

        byte[] data = new byte[(int) xmlFile.length()];

        if(fis.read(data) == -1)
        {
            Assert.fail("Failed to read XML file");
        }

        fis.close();

        String str = new String(data);
        assertEquals(addressBook.toXML(), str);
    }


    @Test
    public void testEmptyExportAndImport() throws IOException, ClassNotFoundException {
        emptyAddressBook.export();
        testImportAddressBook(emptyAddressBook);
    }

    @Test
    public void testOneExportAndImport() throws IOException, ClassNotFoundException {
        oneBuddyAddressBook.export();
        testImportAddressBook(oneBuddyAddressBook);
    }

    @Test
    public void testTwoExportAndImport() throws IOException, ClassNotFoundException {
        twoBuddyAddressBook.export();
        testImportAddressBook(twoBuddyAddressBook);
    }

    private void testImportAddressBook(AddressBook addressBook) throws IOException, ClassNotFoundException {
        AddressBook importedBook = AddressBook.importAddressBook();
        assertEquals(addressBook, importedBook);
    }


    @Test
    public void testClear()
    {
        emptyAddressBook.clear();
        oneBuddyAddressBook.clear();
        twoBuddyAddressBook.clear();

        assertEquals(emptyAddressBook.size(), 0);
        assertEquals(oneBuddyAddressBook.size(), 0);
        assertEquals(twoBuddyAddressBook.size(), 0);
    }


}