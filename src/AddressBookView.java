// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class displays a view of the AddressBook.
 * @author Trong Nguyen
 */
public class AddressBookView extends JFrame {
    private JList<String> buddyInfoList;
    private final AddressBook addressBook;

    /**
     * Constructor for AddressBookView.
     */
    public AddressBookView() {
        super();
        this.addressBook = new AddressBook();
    }

    /**
     * Display the graphical user interface for AddressBook.
     */
    public void displayGUI() {
        this.setTitle("MyAddressBookApp");
        this.setPreferredSize(new Dimension(500, 600));

        JMenuBar menuBar = new JMenuBar();
        JMenu addressBookMenu = new JMenu("AddressBook");
        JMenu buddyInfoMenu = new JMenu("BuddyInfo");

        addressBookMenu.add(createMenuItem());
        addressBookMenu.add(saveTxtMenuItem());
        addressBookMenu.add(importTxtMenuItem());
        addressBookMenu.add(saveXMLMenuItem());
        addressBookMenu.add(importXMLMenuItem());
        buddyInfoMenu.add(addMenuItem());
        buddyInfoMenu.add(removeMenuItem());
        buddyInfoMenu.add(editMenuItem());

        menuBar.add(addressBookMenu);
        menuBar.add(buddyInfoMenu);

        buddyInfoList = new JList<>(addressBook.getListModel());
        this.add(buddyInfoList);
        this.setJMenuBar(menuBar);
        this.pack();

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    setVisible(false);
                    dispose();
                }
            }
        });
        this.setVisible(true);
    }

    /**
     * Create the menu item for Add a buddy.
     * @return  JMenuItem
     */
    private JMenuItem addMenuItem() {
        JMenuItem addMenuItem = new JMenuItem("Add");
        addMenuItem.addActionListener(e -> {
            JPanel panel = new JPanel(new GridLayout(3, 2));
            JLabel labelName = new JLabel("Enter new Buddy Name:");
            JLabel labelAddress = new JLabel("Enter new Buddy Address:");
            JLabel labelPhoneNumber = new JLabel("Enter new Buddy Phone Number:");
            JTextField fieldName = new JTextField();
            JTextField fieldAddress = new JTextField();
            JTextField fieldPhoneNumber = new JTextField();
            panel.add(labelName); panel.add(fieldName);
            panel.add(labelAddress); panel.add(fieldAddress);
            panel.add(labelPhoneNumber); panel.add(fieldPhoneNumber);
            JOptionPane.showMessageDialog(this, panel, "Add a new Buddy", JOptionPane.INFORMATION_MESSAGE);
            BuddyInfo newBuddy = new BuddyInfo(fieldName.getText(), fieldAddress.getText(), fieldPhoneNumber.getText());
            boolean exist = false;
            for (BuddyInfo oldBuddy : addressBook.getMyBuddies()) {
                if (oldBuddy.equals(newBuddy)) {
                    JOptionPane.showMessageDialog(this, "Buddy already exist in AddressBook");
                    exist = false;
                    break;
                } else {
                    exist = true;
                }
            }
            if (exist) {
                addressBook.addBuddy(newBuddy);
                addressBook.getListModel().addElement(newBuddy.toString());
            }
        });
        return addMenuItem;
    }

    /**
     * Create the menu item for Remove a buddy.
     * @return  JMenuItem
     */
    private JMenuItem removeMenuItem() {
        JMenuItem removeMenuItem = new JMenuItem("Remove");
        removeMenuItem.addActionListener(e -> {
            int index = buddyInfoList.getSelectedIndex();
            if (index != -1) {
                addressBook.removeBuddy(index);
                addressBook.getListModel().remove(index);
            } else {
                JOptionPane.showMessageDialog(this, "Select a Buddy to remove");
            }
        });
        return removeMenuItem;
    }

    /**
     * Create the menu item for Edit a buddy.
     * @return  JMenuItem
     */
    private JMenuItem editMenuItem() {
        JMenuItem editMenuItem = new JMenuItem("Edit");
        editMenuItem.addActionListener(e -> {
            int index = buddyInfoList.getSelectedIndex();
            BuddyInfo selectedBuddy = addressBook.getBuddy(index);
            BuddyInfo editedBuddy = updateBuddyInfo(selectedBuddy.getName(), selectedBuddy.getAddress(), selectedBuddy.getPhoneNumber());
            System.out.println(editedBuddy.getName());
            selectedBuddy.setName(editedBuddy.getName());
            selectedBuddy.setAddress(editedBuddy.getAddress());
            selectedBuddy.setPhoneNumber(editedBuddy.getPhoneNumber());
            addressBook.getListModel().set(index, selectedBuddy.toString());
        });
        return editMenuItem;
    }

    /**
     * Helper method to update the edit buddy information.
     * @param oldName           String
     * @param oldAddress        String
     * @param oldPhoneNumber    String
     * @return  BuddyInfo
     */
    private BuddyInfo updateBuddyInfo(String oldName, String oldAddress, String oldPhoneNumber) {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel labelName = new JLabel("Enter new Buddy Name:");
        JLabel labelAddress = new JLabel("Enter new Buddy Address:");
        JLabel labelPhoneNumber = new JLabel("Enter new Buddy Phone Number:");
        JTextField fieldName = new JTextField(oldName);
        JTextField fieldAddress = new JTextField(oldAddress);
        JTextField fieldPhoneNumber = new JTextField(oldPhoneNumber);
        panel.add(labelName); panel.add(fieldName);
        panel.add(labelAddress); panel.add(fieldAddress);
        panel.add(labelPhoneNumber); panel.add(fieldPhoneNumber);
        JOptionPane.showMessageDialog(this, panel, "Edit BuddyInfo", JOptionPane.INFORMATION_MESSAGE);
        return new BuddyInfo(fieldName.getText(), fieldAddress.getText(), fieldPhoneNumber.getText());
    }

    /**
     * Create the menu item for Create a new address book.
     * @return  JMenuItem
     */
    private JMenuItem createMenuItem() {
        JMenuItem createMenuItem = new JMenuItem("Create");
        createMenuItem.addActionListener(e -> {
            addressBook.getListModel().clear();
            buddyInfoList.removeAll();
        });
        return createMenuItem;
    }

    /**
     * Create the menu item for Save address book.
     * Saves a txt file.
     * @return  JMenuItem
     */
    private JMenuItem saveTxtMenuItem() {
        JMenuItem saveMenuItem = new JMenuItem("Save TXT");
        saveMenuItem.addActionListener(e -> {
            String filename = JOptionPane.showInputDialog(this,"Enter name of .txt file to save:");
            if (addressBook.save(filename + ".txt")) {
                JOptionPane.showMessageDialog(this, "Save Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Saving Failed", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        return saveMenuItem;
    }

    /**
     * Create the import menu item for Import address book.
     * Import txt file.
     * @return  JMenuItem
     */
    private JMenuItem importTxtMenuItem() {
        JMenuItem importMenuItem = new JMenuItem("Import TXT");
        importMenuItem.addActionListener(e -> {
            String filename = JOptionPane.showInputDialog(this,"Enter name of .txt file to import:");
            if (addressBook.readTxtImport(filename + ".txt")) {
                JOptionPane.showMessageDialog(this, "Import Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Import Failed", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        return importMenuItem;
    }

    /**
     * Create the menu item for Save address book.
     * Saves an XML file.
     * @return  JMenuItem
     */
    private JMenuItem saveXMLMenuItem() {
        JMenuItem saveMenuItem = new JMenuItem("Save XML");
        saveMenuItem.addActionListener(e -> {
            String filename = JOptionPane.showInputDialog(this,"Enter name of .xml file to save:");
            if (addressBook.exportToXML(filename + ".xml")) {
                JOptionPane.showMessageDialog(this, "Save Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Saving Failed", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        return saveMenuItem;
    }

    /**
     * Create the import menu item for Import address book.
     * Import XML file.
     * @return  JMenuItem
     */
    private JMenuItem importXMLMenuItem() {
        JMenuItem importMenuItem = new JMenuItem("Import XML");
        importMenuItem.addActionListener(e -> {
            String filename = JOptionPane.showInputDialog(this,"Enter name of .xml file to import:");
            if (addressBook.readXMLImport(filename + ".xml")) {
                JOptionPane.showMessageDialog(this, "Import Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Import Failed", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        return importMenuItem;
    }

    /**
     * Main function.
     * @param args     String[]
     */
    public static void main(String[] args) {
        AddressBookView view = new AddressBookView();
        view.displayGUI();
    }
}
