package presention.main;

import presention.tablePresentation.TableView;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JTabbedPane tabbedPane;
    private JMenuBar menuBar;
    private JLabel usernameLabel;
    private JButton loginButton;
    private JButton registerButton;

    private boolean isLogin = false;

    public void init(ActionListener listener, TableModel model) {
        setTitle("Storage of Study Groups");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        configureTabs(model);
        configureMenu(listener);
        configureLowerPane();

        pack();
        setVisible(true);
    }

    private void configureTabs(TableModel model) {
        // init TabbedPane
        tabbedPane = new JTabbedPane();

        // Add table tab
        TableView tableView = new TableView();
        tableView.init(model);
        tabbedPane.add("Table presentation", tableView);

        // Add TabbedPane
        add(tabbedPane, BorderLayout.CENTER);
    }

    private void configureMenu(ActionListener listener) {
        // Init MenuBar
        menuBar = new JMenuBar();

        // Add edit menu
        JMenu editMenu = new JMenu("Edit Collection");
        JMenuItem addMenuItem = new JMenuItem("Add");
        addMenuItem.addActionListener(listener);
        JMenuItem addIfMinMenuItem = new JMenuItem("Add if min");
        addIfMinMenuItem.addActionListener(listener);
        JMenuItem updateMenuItem = new JMenuItem("Update");
        updateMenuItem.addActionListener(listener);
        JMenuItem remove = new JMenuItem("Remove");
        remove.addActionListener(listener);
        JMenuItem clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.addActionListener(listener);

        editMenu.add(addMenuItem);
        editMenu.add(addIfMinMenuItem);
        editMenu.addSeparator();
        editMenu.add(updateMenuItem);
        editMenu.addSeparator();
        editMenu.add(remove);
        editMenu.addSeparator();
        editMenu.add(clearMenuItem);

        menuBar.add(editMenu);

        // Add statistic
        JMenu statsMenu = new JMenu("Statistic");
        JMenuItem countLessThenMenuItem = new JMenuItem("Count less than should be expelled");
        JMenuItem countGreaterThenMenuItem = new JMenuItem("Count greater than students count");
        JMenuItem filterBySemesterMenuItem = new JMenuItem("Filter by semester");

        statsMenu.add(countLessThenMenuItem);
        statsMenu.add(countGreaterThenMenuItem);
        statsMenu.addSeparator();
        statsMenu.add(filterBySemesterMenuItem);

        menuBar.add(statsMenu);

        // Add other
        JMenu otherMenu = new JMenu("Other");
        JMenuItem infoMenuItem = new JMenuItem("Information");
        JMenuItem helpMenuItem = new JMenuItem("Help");
        JMenuItem executeScriptMenuItem = new JMenuItem("Execute script");

        otherMenu.add(infoMenuItem);
        otherMenu.add(helpMenuItem);
        otherMenu.addSeparator();
        otherMenu.add(executeScriptMenuItem);

        menuBar.add(otherMenu);

        add(menuBar, BorderLayout.NORTH);
    }

    private void configureLowerPane() {
        Container panel = new JPanel();
        FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
        panel.setLayout(layout);

        add(panel, BorderLayout.SOUTH);

        loginButton = new JButton("Log in");
        registerButton = new JButton("Register");
        usernameLabel = new JLabel("1234");

        panel.add(usernameLabel);
        panel.add(loginButton);
        panel.add(registerButton);
    }
}
