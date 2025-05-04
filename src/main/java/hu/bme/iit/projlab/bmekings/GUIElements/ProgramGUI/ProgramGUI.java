package hu.bme.iit.projlab.bmekings.GUIElements.ProgramGUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import hu.bme.iit.projlab.bmekings.GUIElements.Controller.Controller;
import hu.bme.iit.projlab.bmekings.GUIElements.Models.PlayerModel;

public class ProgramGUI extends JFrame {
    public ProgramGUI() {
        setTitle("Fungorium Game");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        PlayerModel model = new PlayerModel();
        Controller controller = new Controller(model, this);
        controller.switchView("StartView");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProgramGUI().setVisible(true);
        });
    }
}