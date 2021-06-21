import presention.main.MainView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainView view = new MainView();
        SwingUtilities.invokeLater(view::init);
    }
}
