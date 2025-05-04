package hu.bme.iit.projlab.bmekings.GUIElements.Views;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import hu.bme.iit.projlab.bmekings.GUIElements.Controller.Controller;

public class LoadView extends AbstractGameView {
    private JTextField pathField;

    public LoadView(Controller controller) {
        super(controller);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margók az elemek között
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Címke (Title)
        JLabel label = new JLabel("Játék betöltése", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(label, gbc);

        // Elérési útvonal (Path label)
        JLabel pathLabel = new JLabel("Elérési útvonal", SwingConstants.LEFT);
        pathLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(pathLabel, gbc);

        // Elérési útvonal szövegmező (Path text field)
        pathField = new JTextField(20);
        pathField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(pathField, gbc);

        // Gombok (Buttons: Back and Load)
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Vissza");
        backButton.addActionListener(e -> controller.switchView("StartView"));
        buttonPanel.add(backButton);

        JButton loadButton = new JButton("Load");
        // Add action listener for the Load button if needed in the future
        buttonPanel.add(loadButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);
    }

    @Override
    public void update() {
        // Jelenleg nincs dinamikus frissítés, később implementálható
    }

    // Getter metódus a szövegmező tartalmának lekérdezéséhez
    public String getPath() {
        return pathField.getText();
    }
}