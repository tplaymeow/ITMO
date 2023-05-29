package presention.remove;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class RemoveView extends JFrame {
    public static int REMOVE_AT = 0;
    public static int REMOVE_BY_ID = 1;
    private int type;
    private JTextField indexTextField;
    private JButton button;
    private JLabel label;

    public RemoveView() {
        super();
        this.type = REMOVE_AT;
    }

    public void init(ActionListener listener) {
        setTitle("Remove");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(10, 10, 5, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(new JLabel("Mode"), constraints);

        constraints.insets = new Insets(5, 10, 5, 10);
        constraints.gridx = 1;
        constraints.gridy = 0;
        JComboBox<String> comp = new JComboBox<>(new String[]{"Remove at...", "Remove by ID"});
        comp.addItemListener(e -> {
            if (e.getItem().equals("Remove at...")) {
                type = REMOVE_AT;
            } else {
                type = REMOVE_BY_ID;
            }
            setTextByType();
        });
        add(comp, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        label = new JLabel("At:");
        add(label, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        indexTextField = new JTextField(10);
        add(indexTextField, constraints);

        constraints.insets = new Insets(5, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        button = new JButton("Apply");
        button.addActionListener(listener);
        add(button, constraints);

        setMinimumSize(layout.minimumLayoutSize(getContentPane()));

        pack();
        setVisible(true);
    }

    public String getText() {
        return indexTextField.getText();
    }

    public void setTextByType() {
        label.setText(type == REMOVE_AT ? "At:" : "Id:");
    }
}
