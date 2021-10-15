// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

import java.util.Objects;

/**
 * This class contains information about a buddy.
 *
 * @author Trong Nguyen
 * @version 3.0
 */
public class BuddyInfo {
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
     * Constructor for BuddyInfo.
     * @param name      String, buddy's name
     */
    public BuddyInfo(String name) {
        this(name,null,null);
    }

    /**
     * Default constructor for BuddyInfo.
     */
    public BuddyInfo() {
        this("Homer","742 Evergreen Terrace", "555-5555");
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
     * Return the toString method of BuddyInfo.
     * @return      String
     */
    @Override
    public String toString() {
        return String.format("Name: %-30s Address: %-30s Phone: %-30s\n", name, address, phoneNumber);
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
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        return true;
    }
}
