// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;

/**
 * This class maintains the collection of the BuddyInfo object.
 * @author Trong Nguyen
 */
public class AddressBook extends DefaultListModel<String> implements Serializable {
    public static final String ADDRESSBOOK_TAG = "AddressBook";
    private final ArrayList<BuddyInfo> myBuddies;
    private final DefaultListModel<String> listModel = new DefaultListModel<>();

    /**
     * Constructor for AddressBook.
     */
    public AddressBook() {
        super();
        myBuddies = new ArrayList<>();
    }

    /**
     * Add buddyInfo object to addressBook.
     * @param aBuddy     BuddyInfo object
     */
    public void addBuddy(BuddyInfo aBuddy) {
        if (aBuddy != null) {
            this.myBuddies.add(aBuddy);
        }
    }

    /**
     * Remove buddyInfo object from addressBook.
     * @param index    index value of myBuddies
     */
    public void removeBuddy(int index) {
        if (index >= 0 && index < myBuddies.size()) {
            this.myBuddies.remove(index);
        }
    }

    /**
     * Clear myBuddies ArrayList.
     */
    public void clear() {
        this.myBuddies.clear();
    }

    /**
     * Get a BuddyInfo object from an index.
     * @param index int
     * @return  BuddyInfo
     */
    public BuddyInfo getBuddy(int index) {
        if (index >= 0 && index < myBuddies.size()) {
            return this.myBuddies.get(index);
        }
        return null;
    }

    /**
     * Get the DefaultListModel from AddressBook.
     * @return         DefaultListModel<String> object
     */
    public DefaultListModel<String> getListModel() {
        return this.listModel;
    }

    /**
     * Get list of buddyInfo objects.
     * @return  ArrayList<BuddyInfo>
     */
    public ArrayList<BuddyInfo> getMyBuddies() {
        return this.myBuddies;
    }

    /**
     * The save() method of AddressBook take as a parameter the file name, and save each BuddyInfo on a separate
     * line and use BuddyInfo's toString() method.
     * @param filename  String
     */
    public boolean save(String filename) {
        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            for (BuddyInfo b : myBuddies) {
                fw.write(b.toString());
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Reads a file given a file name as a parameter, and updates the address book GUI.
     * @param filename  String
     */
    public boolean readTxtImport(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            for (String addressLine = br.readLine(); addressLine != null; addressLine = br.readLine()) {
                BuddyInfo b = BuddyInfo.stringImport(addressLine);
                this.myBuddies.add(b);
                listModel.addElement(b.toString());
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Export the address book to an XML file.
     * @param filename  String
     * @return  boolean
     */
    public boolean exportToXmlFile(String filename) {
        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);

            StringBuilder sb = new StringBuilder();
            sb.append("<" + ADDRESSBOOK_TAG + ">" + "\n");
            for (BuddyInfo b : myBuddies) {
                sb.append(b.toXML());
            }
            sb.append("</" + ADDRESSBOOK_TAG + ">" + "\n");

            fw.write(sb.toString());
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Reads a file given a file name as a parameter, and updates the address book GUI.
     * @param filename  String
     */
    public boolean importFromXmlFile(String filename) {
        String name;
        String address;
        String phoneNumber;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(filename));
            doc.getDocumentElement().normalize();

            // System.out.println("Root Element: " + doc.getDocumentElement().getNodeName());
            NodeList list = doc.getElementsByTagName(BuddyInfo.BUDDY_TAG);
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    name = element.getElementsByTagName(BuddyInfo.NAME_TAG).item(0).getTextContent();
                    address = element.getElementsByTagName(BuddyInfo.ADDRESS_TAG).item(0).getTextContent();
                    phoneNumber = element.getElementsByTagName(BuddyInfo.PHONE_TAG).item(0).getTextContent();
                    BuddyInfo b = BuddyInfo.fromXML(name, address, phoneNumber);
                    // System.out.println("Current Element: " + node.getNodeName());
                    this.myBuddies.add(b);
                    listModel.addElement(b.toString());
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}

