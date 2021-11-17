// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

import javax.swing.*;
import java.util.ArrayList;

/**
 * This class maintains the collection of the BuddyInfo object.
 *
 * @author Trong Nguyen
 */
public class AddressBook extends DefaultListModel {
    private final ArrayList<BuddyInfo> myBuddies;
    private final DefaultListModel<String> listModel = new DefaultListModel<>();

    /**
     * Constructor for AddressBook.
     */
    public AddressBook() {
        super();
        myBuddies = new ArrayList<>();
        init();
    }

    /**
     * Initializes the AddressBook with some Buddies.
     */
    public void init() {
        this.addBuddy(new BuddyInfo("Homer", "123 Street", "555-123-4567"));
        this.addBuddy(new BuddyInfo("Marge", "321 Avenue", "555-321-7654"));
        this.addBuddy(new BuddyInfo("Bart", "456 Road", "555-654-1234"));
        this.addBuddy(new BuddyInfo("Lisa", "654 Crescent", "555-789-9876"));
        this.addBuddy(new BuddyInfo("Maggie", "789 Private", "555-987-7654"));

        for (BuddyInfo b : myBuddies) {
            listModel.addElement(b.toString());
        }
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

    /**
     * Get the DefaultListModel from AddressBook.
     * @return         DefaultListModel<String> object
     */
    public DefaultListModel<String> getListModel() {
        return this.listModel;
    }

    public ArrayList<BuddyInfo> getMyBuddies() {
        return this.myBuddies;
    }
}
