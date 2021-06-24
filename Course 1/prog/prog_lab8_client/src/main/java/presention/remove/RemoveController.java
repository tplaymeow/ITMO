package presention.remove;

import model.Coordinates;
import model.Flat;
import presention.Model;
import presention.main.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RemoveController {
    private RemoveView view;
    private Model model;

    public RemoveController(RemoveView view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void presentView() {
        SwingUtilities.invokeLater(() -> view.init(this::didTapApply));
    }

    public void didTapApply(ActionEvent e) {
        model.add(new Flat(1337, "Timur", new Coordinates(3, 5)));
        view.repaint();
    }
}
