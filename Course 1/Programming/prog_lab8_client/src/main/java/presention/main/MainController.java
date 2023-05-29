package presention.main;

import presention.Model;
import presention.remove.RemoveController;
import presention.remove.RemoveView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainController {
    private MainView view;
    private Model model;

    public MainController(MainView view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void presentView() {
        SwingUtilities.invokeLater(() -> view.init(this::menuItemDidSelect, model));
    }

    public void menuItemDidSelect(ActionEvent e) {
        if ("Remove".equals(e.getActionCommand())) {
            RemoveView removeView = new RemoveView();
            RemoveController controller = new RemoveController(removeView, model);
            controller.presentView();
        }
    }
}
