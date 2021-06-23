package presention.main;

import model.Coordinates;
import model.Flat;
import presention.Model;

import javax.swing.*;
import java.awt.*;
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
        model.add(new Flat(1337, "Timur", new Coordinates(3, 5)));
        view.repaint();
    }
}
