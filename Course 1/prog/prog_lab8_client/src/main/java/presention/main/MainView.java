package presention.main;

import presention.tablePresentation.TableView;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private JTabbedPane tabbedPane;
    private JMenuBar menuBar;

    public void init() {
        setTitle("Storage of Study Groups");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        configureTabs();

        setVisible(true);
    }

    private void configureTabs() {
        tabbedPane = new JTabbedPane();
        TableView tableView = new TableView();
        tableView.init();
        tabbedPane.add("Table presentation", tableView);
        add(tabbedPane);
    }
}
