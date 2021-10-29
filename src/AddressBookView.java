// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class displays a view of the AddressBook.
 * @author Trong Nguyen
 * @version 3.0
 */
public class AddressBookView extends JFrame {
    private JList buddyInfoList;
    private JFrame mainFrame;
    private AddressBook addressBook;

    /**
     * Constructor for AddressBookView.
     */
    public AddressBookView() {
        super();
        this.mainFrame = new JFrame("AddressBook");
        this.addressBook = new AddressBook();
    }

    /**
     * Display the graphical user interface for AddressBook.
     * @return      boolean
     */
    public boolean displayGUI() {
        this.mainFrame = new JFrame("AddressBook");
        this.mainFrame.setPreferredSize(new Dimension(500, 600));

        JMenuBar menuBar = new JMenuBar();
        JMenu addressBookMenu = new JMenu("AddressBook");
        JMenu buddyInfoMenu = new JMenu("BuddyInfo");
        JMenuItem addMenuItem = new JMenuItem("Add");
        JMenuItem removeMenuItem = new JMenuItem("Remove");
        JMenuItem createMenuItem = new JMenuItem("Create");
        JMenuItem saveMenuItem = new JMenuItem("Save");

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
            JOptionPane.showMessageDialog(mainFrame, panel);
            BuddyInfo newBuddy = new BuddyInfo(fieldName.getText(), fieldAddress.getText(), fieldPhoneNumber.getText());
            boolean exist = false;
            for (BuddyInfo oldBuddy : addressBook.getMyBuddies()) {
                if (oldBuddy.equals(newBuddy)) {
                    JOptionPane.showMessageDialog(mainFrame, "Buddy already exist in AddressBook");
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

        removeMenuItem.addActionListener(e -> {
            int index = buddyInfoList.getSelectedIndex();
            if (index != -1) {
                addressBook.removeBuddy(index);
                addressBook.getListModel().remove(index);
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Select a Buddy to remove");
            }
        });

        createMenuItem.addActionListener(e -> addressBook.getListModel().clear());

        addressBookMenu.add(createMenuItem);
        addressBookMenu.add(saveMenuItem);
        buddyInfoMenu.add(addMenuItem);
        buddyInfoMenu.add(removeMenuItem);

        menuBar.add(addressBookMenu);
        menuBar.add(buddyInfoMenu);

        buddyInfoList = new JList<>(addressBook.getListModel());
        this.mainFrame.add(buddyInfoList);
        this.mainFrame.setJMenuBar(menuBar);
        this.mainFrame.pack();

        this.mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    mainFrame.setVisible(false);
                    mainFrame.dispose();
                }
            }
        });
        this.mainFrame.setVisible(true);
        return true;
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