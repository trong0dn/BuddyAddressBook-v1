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
    private ArrayList<BuddyInfo> myBuddies;

    public AddressBook() {
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
    public BuddyInfo removeBuddy(int index) {
        if (index >= 0 && index < myBuddies.size()) {
            this.myBuddies.remove(index);
        }
        return null;
    }

    public static void main(String[] args) {
        BuddyInfo buddy = new BuddyInfo("Tom", "Carleton", "613");
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy);
        addressBook.removeBuddy(0);
    }
}
