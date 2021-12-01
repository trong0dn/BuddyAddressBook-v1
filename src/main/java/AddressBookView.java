// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class displays a view of the AddressBook.
 * @author Trong Nguyen
 */
public class AddressBookView extends JFrame implements ListSelectionListener {
    private AddressBook currentAddressBook;
    private JMenuItem saveAddressBookMenuItem;
    private JMenuItem addBuddyInfoMenuItem;
    private JMenuItem editBuddyInfoMenuItem;
    private JMenuItem removeBuddyInfoMenuItem;
    private JList<BuddyInfo> currentBuddyInfoList;

    /**
     * Constructor for AddressBookView.
     */
    public AddressBookView() {
        displayGUI();
    }

    /**
     * Display the graphical user interface for AddressBook.
     */
    private void displayGUI() {
        this.setTitle("MyAddressBookApp");
        this.setPreferredSize(new Dimension(300, 300));

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create AddressBook menu
        JMenu addressBookMenu = new JMenu("AddressBook");
        JMenuItem createAddressBookMenuItem = new JMenuItem("Create");
        saveAddressBookMenuItem = new JMenuItem("Save");
        JMenuItem importAddressBookMenuItem = new JMenuItem("Import");

        // Create BuddyInfo menu
        JMenu buddyInfoMenu = new JMenu("BuddyInfo");
        addBuddyInfoMenuItem = new JMenuItem("Add");
        editBuddyInfoMenuItem = new JMenuItem("Edit");
        removeBuddyInfoMenuItem = new JMenuItem("Remove");

        // ActionListeners for menu items
        createAddressBookMenuItem.addActionListener(this::createAddressBook);
        saveAddressBookMenuItem.addActionListener(this::saveAddressBook);
        importAddressBookMenuItem.addActionListener(this::importAddressBook);
        addBuddyInfoMenuItem.addActionListener(this::addBuddyInfo);
        editBuddyInfoMenuItem.addActionListener(this::editBuddyInfo);
        removeBuddyInfoMenuItem.addActionListener(this::removeBuddyInfo);

        // Add AddressBook menu items
        addressBookMenu.add(createAddressBookMenuItem);
        addressBookMenu.add(saveAddressBookMenuItem);
        addressBookMenu.add(importAddressBookMenuItem);

        // Add BuddyInfo menu items
        buddyInfoMenu.add(addBuddyInfoMenuItem);
        buddyInfoMenu.add(editBuddyInfoMenuItem);
        buddyInfoMenu.add(removeBuddyInfoMenuItem);

        // Add menus to the menu bar
        menuBar.add(addressBookMenu);
        menuBar.add(buddyInfoMenu);

        // Disable some menu items
        saveAddressBookMenuItem.setEnabled(false);
        addBuddyInfoMenuItem.setEnabled(false);
        editBuddyInfoMenuItem.setEnabled(false);
        removeBuddyInfoMenuItem.setEnabled(false);

        // Set up JList
        currentBuddyInfoList = new JList<>();
        currentBuddyInfoList.addListSelectionListener(this);
        currentBuddyInfoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add to main frame
        this.setJMenuBar(menuBar);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.getContentPane().add(currentBuddyInfoList);

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
     * Set the current AddressBook upon creating new AddressBook.
     * @param addressBook   AddressBook
     */
    private void setCurrentAddressBook(AddressBook addressBook) {
        this.currentAddressBook = addressBook;
        currentBuddyInfoList.setModel(currentAddressBook);
        saveAddressBookMenuItem.setEnabled(true);
        addBuddyInfoMenuItem.setEnabled(true);
    }

    /**
     * Add a new BuddyInfo.
     * @param actionEvent ActionEvent
     */
    private void addBuddyInfo(ActionEvent actionEvent) {
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
        currentAddressBook.addBuddy(newBuddy);
    }

    /**
     * Remove the selected BuddyInfo.
     * @param actionEvent ActionEvent
     */
    private void removeBuddyInfo(ActionEvent actionEvent) {
        int selectedBuddyIndex = currentBuddyInfoList.getSelectedIndex();
        BuddyInfo buddyInfo = currentAddressBook.getElementAt(selectedBuddyIndex);
        currentAddressBook.removeBuddy(buddyInfo);
    }

    /**
     * Edit the selected BuddyInfo.
     * @param actionEvent   ActionEvent
     */
    private void editBuddyInfo(ActionEvent actionEvent) {
        int selectedBuddyIndex = currentBuddyInfoList.getSelectedIndex();
        BuddyInfo selectedBuddy = currentAddressBook.get(selectedBuddyIndex);
        BuddyInfo editedBuddy = updateBuddyInfo(selectedBuddy.getName(), selectedBuddy.getAddress(), selectedBuddy.getPhoneNumber());

        selectedBuddy.setName(editedBuddy.getName());
        selectedBuddy.setAddress(editedBuddy.getAddress());
        selectedBuddy.setPhoneNumber(editedBuddy.getPhoneNumber());
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
     * Create a new AddressBook.
     * @param actionEvent   ActionEvent
     */
    private void createAddressBook(ActionEvent actionEvent) {
        setCurrentAddressBook(new AddressBook());
    }

    /**
     * Save the current AddressBook.
     * @param actionEvent   ActionEvent
     */
    private void saveAddressBook(ActionEvent actionEvent) {
        if (currentAddressBook == null) {
            return;
        }
        currentAddressBook.save();
        JOptionPane.showMessageDialog(this, "Address Book has been saved to file");
    }

    /**
     * Import a previous saved AddressBook.
     * @param actionEvent   ActionEvent
     */
    private void importAddressBook(ActionEvent actionEvent) {
        setCurrentAddressBook(AddressBook.importFromTXTFile());
    }

    /**
     * Override method inherited from ListSelectionListener
     * @param e ListSelectionEvent
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }

        int selectedIndex = currentBuddyInfoList.getSelectedIndex();

        // If a buddy info gets removed (or de-selected), then disable the edit/remove buttons.
        if (selectedIndex == -1) {
            editBuddyInfoMenuItem.setEnabled(false);
            removeBuddyInfoMenuItem.setEnabled(false);
        } else {
            editBuddyInfoMenuItem.setEnabled(true);
            removeBuddyInfoMenuItem.setEnabled(true);
        }
    }

    /**
     * Main function.
     * @param args     String[]
     */
    public static void main(String[] args) {
        new AddressBookView();
    }
}
