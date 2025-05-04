package hu.bme.iit.projlab.bmekings.GUIElements.Views;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import hu.bme.iit.projlab.bmekings.GUIElements.Controller.Controller;

public class LoadView extends AbstractGameView {
    public LoadView(Controller controller) {
        super(controller);
        setLayout(new BorderLayout());

        // Címke
        JLabel label = new JLabel("Játék betöltése", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.NORTH);

        // Üzenet
        JLabel message = new JLabel("Betöltési funkcionalitás implementáció alatt", SwingConstants.CENTER);
        message.setFont(new Font("Arial", Font.PLAIN, 16));
        add(message, BorderLayout.CENTER);

        // Vissza gomb
        JButton backButton = new JButton("Vissza");
        backButton.addActionListener(e -> controller.switchView("StartView"));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void update() {
        // Jelenleg nincs dinamikus frissítés, később implementálható
    }
}