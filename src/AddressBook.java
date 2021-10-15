// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * This class maintains the collection of the BuddyInfo object.
 *
 * @author Trong Nguyen
 * @version 3.0
 */
public class AddressBook extends JFrame {
    private ArrayList<BuddyInfo> myBuddies;
    private JList<String> buddyInfoList;
    private JFrame mainFrame;
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    public AddressBook() {
        myBuddies = new ArrayList<>();
        this.mainFrame = new JFrame("AddressBook");
        init();
    }

    public void init() {
        addBuddy(new BuddyInfo("Homer", "123 Street", "555-123-4567"));
        addBuddy(new BuddyInfo("Marge", "321 Avenue", "555-321-7654"));
        addBuddy(new BuddyInfo("Bart", "456 Road", "555-654-1234"));
        addBuddy(new BuddyInfo("Lisa", "654 Crescent", "555-789-9876"));
        addBuddy(new BuddyInfo("Maggie", "789 Private", "555-987-7654"));

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

    public boolean displayGUI() {
        this.mainFrame.setPreferredSize(new Dimension(400, 600));

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem addMenuItem = new JMenuItem("Add");
        JMenuItem removeMenuItem = new JMenuItem("Remove");
        JMenuItem saveMenuItem = new JMenuItem("Save");

        addMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                listModel.addElement(new BuddyInfo(fieldName.getText(), fieldAddress.getText(), fieldPhoneNumber.getText()).toString());
            }
        });

        removeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel(new GridLayout(1, 2));
                JLabel labelName = new JLabel("Buddy Name to Remove:");
                JTextField fieldName = new JTextField();
                panel.add(labelName); panel.add(fieldName);
                JOptionPane.showMessageDialog(mainFrame, panel);
                for (BuddyInfo b : myBuddies) {
                    if (b.equals(b.getBuddyInfo(fieldName.getText()))) {
                        int index = listModel.lastIndexOf(b);
                        removeBuddy(index);
                        listModel.removeElement(b.toString());
                    }
                }
            }
        });

        menu.add(addMenuItem);
        menu.add(removeMenuItem);
        menu.add(saveMenuItem);

        menuBar.add(menu);
        buddyInfoList = new JList<>(listModel);
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

    public static void main(String[] args) {
        //BuddyInfo buddy = new BuddyInfo("Tom", "Carleton", "613");
        AddressBook addressBook = new AddressBook();
        //addressBook.addBuddy(buddy);
        //addressBook.removeBuddy(0);
        addressBook.displayGUI();
    }
}
