package presention.add;

import javax.swing.*;
import java.awt.*;

public class AddView extends JFrame {

    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;

    public void init() {
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();

//        textField1.setMaximumSize(new Dimension(120,20));
//        textField2.setMaximumSize(new Dimension(120,20));


        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
//        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        panel.add(textField1);
        panel.add(textField2);
        add(panel, BorderLayout.CENTER);


        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
