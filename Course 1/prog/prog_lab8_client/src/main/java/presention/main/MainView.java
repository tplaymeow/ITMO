package presention.main;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private JTabbedPane tabbedPane;
    private JMenuBar menuBar;

    public void init() {
        setTitle("Storage of Study Groups");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);

        // Tabs configuration
        tabbedPane = new JTabbedPane();
        JComponent panel1 = new JPanel() {{
            JLabel table = new JLabel("Table", SwingConstants.CENTER);
            add(table);
        }};
        tabbedPane.addTab("Табличное представление", panel1);

        JComponent panel2 = new JPanel() {{
            JLabel table = new JLabel("Interactive", SwingConstants.CENTER);
            setBackground(Color.CYAN);
            add(table);
        }};
        tabbedPane.addTab("Интерактивное представление", panel2);
        add(tabbedPane, BorderLayout.CENTER);

        //Menu configuration
        menuBar = new JMenuBar();

        JMenu commandsMenu = new JMenu("Commands");
        commandsMenu.add(new JMenuItem("add"){{
            addActionListener(e -> JOptionPane.showMessageDialog(null, "add"));
        }});
        commandsMenu.add(new JMenuItem("add_if_min"){{
            addActionListener(e -> JOptionPane.showMessageDialog(null, "add_if_min"));
        }});
        commandsMenu.addSeparator();
        commandsMenu.add(new JMenuItem("update"){{
            addActionListener(e -> JOptionPane.showMessageDialog(null, "update"));
        }});

        menuBar.add(commandsMenu);

        add(menuBar, BorderLayout.NORTH);

        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }
}
