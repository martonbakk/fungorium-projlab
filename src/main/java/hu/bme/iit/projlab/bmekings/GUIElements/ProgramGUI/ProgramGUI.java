package hu.bme.iit.projlab.bmekings.GUIElements.ProgramGUI;

import java.util.ArrayList;
import java.util.List;

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
            frame.setResizable(false);
            GameLogic gameLogic = new GameLogic(1000, 0); // TickInterval=1000ms, playerNum=0
            List<String> insectTypes = new ArrayList<>(List.of("Insect1", "Insect2", "Insect3"));
            List<String> fungalTypes = new ArrayList<>(List.of("ms1", "ms2", "ms3"));

            List<String> insectImageList = new ArrayList<>(List.of(
                "/Data/insect1.png",
                "/Data/insect2.png",
                "/Data/insect3.png"
            ));

            List<String> fungalImageList = new ArrayList<>(List.of(
                "/Data/ms1.png",
                "/Data/ms2.png",
                "/Data/ms3.png"
            ));

            List<List<String>> textures = new ArrayList<>(List.of(insectImageList, fungalImageList));
            Controller controller = new Controller(gameLogic, frame, textures, insectTypes, fungalTypes);
            controller.switchView("StartView");
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        });
    }
}