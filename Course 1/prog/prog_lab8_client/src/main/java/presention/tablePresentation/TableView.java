package presention.tablePresentation;

import presention.Model;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class TableView extends JPanel {
    private JTable table;

    public TableView() {
        super(new GridLayout());
    }

    public void init(TableModel model) {
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
}
