// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

import java.util.ArrayList;

/**
 * This class maintains the collection of the BuddyInfo object.
 *
 * @author Trong Nguyen
 * @version 1.0
 */
public class AddressBook {
    /**
     * Create a collection of BuddyInfo objects.
     */
    private ArrayList<BuddyInfo> addressBook = new ArrayList<>();

    /**
     * Add buddyInfo object to addressBook.
     * @param buddyInfo     BuddyInfo object
     */
    public void addBuddy(BuddyInfo buddyInfo) {
        this.addressBook.add(buddyInfo);
    }

    /**
     * Remove buddyInfo object from addressBook.
     * @param buddyInfo     BuddyInfo object
     */
    public void removeBuddy(BuddyInfo buddyInfo) {
        this.addressBook.remove(buddyInfo);
    }

    public static void main(String[] args) {
        BuddyInfo buddy = new BuddyInfo("Tom", "Carleton", "613");
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy);
        addressBook.removeBuddy(buddy);
    }
}
