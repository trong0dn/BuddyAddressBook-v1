// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

/**
 * This class contains information about a buddy.
 * @author Trong Nguyen
 */
public class BuddyInfo implements Serializable {
    public static final String BUDDY_TAG = "buddyInfo";
    public static final String NAME_TAG = "name";
    public static final String ADDRESS_TAG = "address";
    public static final String PHONE_TAG = "phoneNumber";

    private String name;
    private String address;
    private String phoneNumber;

    /**
     * Constructor for BuddyInfo.
     * @param name          String, name of Buddy
     * @param address       String, address of Buddy
     * @param phoneNumber   String, phone number of Buddy
     */
    public BuddyInfo(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the buddy's name.
     * @return      String, buddy's name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the buddy's address.
     * @return      String, buddy's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get the buddy's phone number.
     * @return      String, buddy's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the buddy's name.
     * @param name  String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the buddy's address.
     * @param address   String
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Set the buddy's phone number.
     * @param phoneNumber   String
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Return the toString method of BuddyInfo.
     * BuddyInfo is displayed on a single line, with each attribute of the BuddyInfo separated from one another using
     * a special character.
     * Example output:
     * "Mr. Buddy,111 Fake Street,613-555-5555"
     *
     * @return      String
     */
    @Override
    public String toString() {
        return String.format("%s,%s,%s\n", name, address, phoneNumber);
    }

    /**
     * Check for BuddyInfo object equality.
     * @param obj   BuddyInfo object
     * @return      boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final BuddyInfo other = (BuddyInfo) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return Objects.equals(this.phoneNumber, other.phoneNumber);
    }

    /**
     * BuddyInfo that takes a string parameter of the form: "Mr. Buddy,111 Fake Street,613-555-5555"
     * @param addressLine   String
     * @return  BuddyInfo object
     */
    static BuddyInfo stringImport(String addressLine) {
        String[] info = addressLine.split(",");
        return new BuddyInfo(info[0], info[1], info[2]);
    }

    /**
     * Create an XML tag for a simple element.
     * @param tag       String
     * @param value     String
     * @return          String
     */
    private String createXMLElement(String tag, String value) {
        return "\t" + "<" + tag + ">" + value + "</" + tag + ">";
    }

    /**
     * Convert address book elements to XML format.
     * @return  String
     */
    public String toXML() {
        return "\t" + "<" + BUDDY_TAG + ">" + "\n" +
                "\t" + createXMLElement(NAME_TAG, name) + "\n" +
                "\t" + createXMLElement(ADDRESS_TAG, address) + "\n" +
                "\t" + createXMLElement(PHONE_TAG, phoneNumber) + "\n" +
                "\t" + "</" + BUDDY_TAG + ">" + "\n";
    }

    static BuddyInfo fromXML(String name, String address, String phoneNumber) {
        return new BuddyInfo(name, address, phoneNumber);
    }
}
