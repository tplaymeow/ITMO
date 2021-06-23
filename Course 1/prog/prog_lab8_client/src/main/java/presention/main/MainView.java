package presention.main;

import presention.tablePresentation.TableView;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class MainView extends JFrame {
    private JTabbedPane tabbedPane;
    private JMenuBar menuBar;
    private JLabel usernameLabel;
    private JButton loginButton;
    private JButton registerButton;

    private boolean isLogin = false;

    public void init() {
        setTitle("Storage of Study Groups");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        configureTabs();
        configureMenu();
        configureLowerPane();

        pack();
        setVisible(true);
    }

    private void configureTabs() {
        // init TabbedPane
        tabbedPane = new JTabbedPane();

        // Add table tab
        TableView tableView = new TableView();
        tableView.init();
        tabbedPane.add("Table presentation", tableView);

        // Add TabbedPane
        add(tabbedPane, BorderLayout.CENTER);
    }

    private void configureMenu() {
        // Init MenuBar
        menuBar = new JMenuBar();

        // Add edit menu
        JMenu editMenu = new JMenu("Edit Collection");
        JMenuItem addMenuItem = new JMenuItem("Add");
        JMenuItem addIfMinMenuItem = new JMenuItem("Add if min");
        JMenuItem updateMenuItem = new JMenuItem("Update");
        JMenuItem removeByIdMenuItem = new JMenuItem("Remove by ID");
        JMenuItem removeAtMenuItem = new JMenuItem("Remove at...");
        JMenuItem clearMenuItem = new JMenuItem("Clear");

        editMenu.add(addMenuItem);
        editMenu.add(addIfMinMenuItem);
        editMenu.addSeparator();
        editMenu.add(updateMenuItem);
        editMenu.addSeparator();
        editMenu.add(removeByIdMenuItem);
        editMenu.add(removeAtMenuItem);
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
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);

        add(panel, BorderLayout.SOUTH);

        loginButton = new JButton("Log in");
        registerButton = new JButton("Register");
        usernameLabel = new JLabel("1234");

        panel.add(usernameLabel);

        layout.putConstraint(SpringLayout.WEST, usernameLabel,6,SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, usernameLabel, 6, SpringLayout.WEST, panel);
    }
}
