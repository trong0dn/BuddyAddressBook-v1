// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

import java.util.Objects;

/**
 * This class contains information about a buddy.
 *
 * @author Trong Nguyen
 * @version 1.0
 */
public class BuddyInfo {

    private String name;
    private String address;
    private String phoneNumber;

    public BuddyInfo(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public BuddyInfo() {
        this.name = "Homer";
        this.address = "742 Evergreen Terrace";
        this.phoneNumber = "555-5555";
    }

    /**
     * Constructor for BuddyInfo.
     * @param name      String, buddy's name
     */
    public BuddyInfo(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return String.format("%-30s %-30s %-30s", name, address, phoneNumber);
    }

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

    /**
     * Main method.
     * @param args      String[]
     */
    public static void main(String[] args) {
        BuddyInfo buddyInfo = new BuddyInfo("Homer");
        System.out.println("Hello " + buddyInfo.getName());
    }
}
