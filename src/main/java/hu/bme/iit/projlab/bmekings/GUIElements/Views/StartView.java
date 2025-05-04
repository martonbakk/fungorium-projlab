package hu.bme.iit.projlab.bmekings.GUIElements.Views;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import hu.bme.iit.projlab.bmekings.GUIElements.Controller.Controller;

public class StartView extends AbstractGameView {
    public StartView(Controller controller) {
        super(controller);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Üdvözöl a Fungorium Játék!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> controller.switchView("GameView"));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void update() {
        // A StartView nem igényel dinamikus frissítést
    }
}