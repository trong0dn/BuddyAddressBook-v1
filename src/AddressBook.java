// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * This class maintains the collection of the BuddyInfo object.
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
    public void save(String filename) {
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
        }
    }

    /**
     * Reads a file given a file name as a parameter, and updates the address book GUI.
     * @param filename  String
     */
    public void readImport(String filename) {
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
        }
    }
}
