// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.parsers.*;
import java.io.*;

/**
 * This class maintains the collection of the BuddyInfo object.
 * @author Trong Nguyen
 */
public class AddressBook extends DefaultListModel<BuddyInfo> implements Serializable {
    public static final String ADDRESS_BOOK_TXT_FILE = "AddressBook.txt";
    public static final String ADDRESS_BOOK_XML_FILE = "AddressBook.xml";
    public static final String ADDRESS_BOOK_TAG = "addressbook";

    /**
     * Add BuddyInfo object to AddressBook.
     * @param aBuddy     BuddyInfo object
     */
    public void addBuddy(BuddyInfo aBuddy) {
        if (aBuddy == null || super.contains(aBuddy))
            return;
        super.addElement(aBuddy);
    }

    /**
     * Remove BuddyInfo object from AddressBook.
     * @ @param aBuddy     BuddyInfo object
     */
    public void removeBuddy(BuddyInfo aBuddy) {
        if (aBuddy == null || !super.contains(aBuddy))
            return;
        super.removeElement(aBuddy);
    }

    /**
     * Get the size of the AddressBook.
     * @return  int
     */
    public int size() {
        return super.size();
    }

    /**
     * Clear the current AddressBook.
     */
    public void clear() {
        super.clear();
    }

    /**
     * Save the AddressBook contents to a pre-defined .txt file.
     */
    public void export() {
        try {
            this.exportToXMLFile();
            FileOutputStream fileOutputStream = new FileOutputStream(ADDRESS_BOOK_TXT_FILE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Import the AddressBook contents of a .txt file.
     * @return  AddressBook
     */
    public static AddressBook importAddressBook() {
        try {
            FileInputStream fileInputStream = new FileInputStream(ADDRESS_BOOK_TXT_FILE);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (AddressBook) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Converts the contents of the AddressBook into XML format.
     * @return  String
     */
    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<" + ADDRESS_BOOK_TAG + ">");
        for (int i = 0; i < this.size(); i++) {
            stringBuilder.append(this.get(i).toXML());
        }
        stringBuilder.append("</" + ADDRESS_BOOK_TAG + ">");
        return stringBuilder.toString();
    }

    /**
     * Export the AddressBook XML file.
     */
    public void exportToXMLFile() {
        try {
            FileWriter fileOutputStream = new FileWriter(ADDRESS_BOOK_XML_FILE);
            fileOutputStream.write(toXML());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Import an AddressBook from an XML file.
     * @return  AddressBook
     */
    public static AddressBook importFromXMLFile() {
        AddressBook currentAddressBook;
        try {
            File file = new File(ADDRESS_BOOK_XML_FILE);
            currentAddressBook = readSAX(file);
            return currentAddressBook;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Defined SAX reader to handle the parsing of XML.
     * @param f File
     * @return  AddressBook
     */
    public static AddressBook readSAX(File f) {
        AddressBook addressBook = new AddressBook();
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser s = spf.newSAXParser();

            DefaultHandler dh = new DefaultHandler() {
                BuddyInfo buddy;
                String elementValue;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    if (qName.equalsIgnoreCase(BuddyInfo.BUDDY_TAG)) {
                        buddy = new BuddyInfo();
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) {
                    if (qName.equalsIgnoreCase(BuddyInfo.BUDDY_TAG)) {
                        addressBook.addBuddy(buddy);
                    }
                    if (qName.equalsIgnoreCase(BuddyInfo.NAME_TAG)) {
                        buddy.setName(elementValue);
                    }
                    if (qName.equalsIgnoreCase(BuddyInfo.ADDRESS_TAG)) {
                        buddy.setAddress(elementValue);
                    }
                    if (qName.equalsIgnoreCase(BuddyInfo.PHONE_TAG)) {
                        buddy.setPhoneNumber(elementValue);
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) {
                    elementValue = new String(ch, start, length);
                }
            };
            s.parse(f, dh);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addressBook;
    }

    /**
     * Check for AddressBook object equality.
     * @param obj   Objects
     * @return      boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof AddressBook addressBook))
            return false;
        if (this.size() != addressBook.size())
            return false;
        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).equals(addressBook.get(i)))
                return false;
        }
        return true;
    }

    /**
     * Return the toString method of AddressBook.
     * @return  String
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
