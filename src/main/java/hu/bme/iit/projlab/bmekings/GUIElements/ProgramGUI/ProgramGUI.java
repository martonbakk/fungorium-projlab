package hu.bme.iit.projlab.bmekings.GUIElements.ProgramGUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import hu.bme.iit.projlab.bmekings.GUIElements.Controller.Controller;
import hu.bme.iit.projlab.bmekings.Logic.GameLogic.GameLogic;

public class ProgramGUI extends JFrame {
   public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Fungorium Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            GameLogic gameLogic = new GameLogic(1000, 0); // TickInterval=1000ms, playerNum=0
            Controller controller = new Controller(gameLogic, frame);
            controller.switchView("StartView");
            frame.setVisible(true);
        });
    }
}