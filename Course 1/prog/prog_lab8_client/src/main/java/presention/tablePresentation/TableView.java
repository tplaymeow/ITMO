package presention.tablePresentation;

import presention.Model;

import javax.swing.*;
import java.awt.*;

public class TableView extends JPanel {
    private JTable table;

    public TableView() {
        super(new GridLayout());
    }

    public void init() {
        table = new JTable(new Model());
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
}
