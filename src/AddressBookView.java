// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

/**
 * This class displays a view of the AddressBook.
 * @author Trong Nguyen
 */
public class AddressBookView extends JFrame {
    private JList buddyInfoList;
    private final JFrame mainFrame;
    private final AddressBook addressBook;
    public final String FILENAME = "saveAddressBook.txt";

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
        this.mainFrame.setPreferredSize(new Dimension(500, 600));

        JMenuBar menuBar = new JMenuBar();
        JMenu addressBookMenu = new JMenu("AddressBook");
        JMenu buddyInfoMenu = new JMenu("BuddyInfo");

        addressBookMenu.add(createMenuItem());
        addressBookMenu.add(saveMenuItem());
        addressBookMenu.add(importMenuItem());
        buddyInfoMenu.add(addMenuItem());
        buddyInfoMenu.add(removeMenuItem());

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
            JOptionPane.showMessageDialog(mainFrame, panel, "Add a new Buddy", JOptionPane.INFORMATION_MESSAGE);
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
                JOptionPane.showMessageDialog(mainFrame, "Select a Buddy to remove");
            }
        });
        return removeMenuItem;
    }


    /**
     * Create the menu item for Create a new address book.
     * @return  JMenuItem
     */
    private JMenuItem createMenuItem() {
        JMenuItem createMenuItem = new JMenuItem("Create");
        createMenuItem.addActionListener(e -> addressBook.getListModel().clear());
        return createMenuItem;
    }

    /**
     * Create the menu item for Save address book.
     * @return  JMenuItem
     */
    private JMenuItem saveMenuItem() {
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(e -> {
            String filename = JOptionPane.showInputDialog(mainFrame,"Enter name of .txt file to save:");
            addressBook.save(filename + ".txt");
        });
        return saveMenuItem;
    }

    /**
     * Create the import menu item for Import address book.
     * @return  JMenuItem
     */
    private JMenuItem importMenuItem() {
        JMenuItem importMenuItem = new JMenuItem("Import");
        importMenuItem.addActionListener(e -> {
            String filename = JOptionPane.showInputDialog(mainFrame,"Enter name of .txt file to import:");
            if (!(addressBook.readImport(filename + ".txt"))) {
                JOptionPane.showMessageDialog(mainFrame, "Buddy already exist in AddressBook");
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
