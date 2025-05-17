package hu.bme.iit.projlab.bmekings.GUIElements.Views;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import hu.bme.iit.projlab.bmekings.GUIElements.Controller.Controller;

public class LoadView extends AbstractGameView {
    private JTextField pathField;
    private JTextField nameField;

    public LoadView(Controller controller) {
super(controller);
setLayout(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();
gbc.insets = new Insets(10, 10, 10, 10);
gbc.fill = GridBagConstraints.HORIZONTAL;

// Címke
JLabel label = new JLabel("Játék betöltése", SwingConstants.CENTER);
label.setFont(new Font("Arial", Font.BOLD, 24));
gbc.gridx = 0;
gbc.gridy = 0;
gbc.gridwidth = 2;
add(label, gbc);

// Mentés neve mező
JLabel nameLabel = new JLabel("Mentés neve:", SwingConstants.LEFT);
nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
gbc.gridx = 0;
gbc.gridy = 1;
gbc.gridwidth = 1;
add(nameLabel, gbc);

    
    nameField = new JTextField(20);
    nameField.setFont(new Font("Arial", Font.PLAIN, 16));
    gbc.gridx = 1;
    gbc.gridy = 1;
    add(nameField, gbc);

// Gombok
JPanel buttonPanel = new JPanel();
JButton backButton = new JButton("Vissza");
backButton.addActionListener(e -> controller.switchView("StartView"));
buttonPanel.add(backButton);

JButton loadButton = new JButton("Betöltés");
loadButton.addActionListener(e -> {
String name = nameField.getText();
if (name == null || name.trim().isEmpty()) {
JOptionPane.showMessageDialog(this, "Kérlek, adj meg egy mentési nevet!",
"Hiba", JOptionPane.ERROR_MESSAGE);
return;
}
controller.loadGame(name);
controller.switchView("GameView");
});
buttonPanel.add(loadButton);

gbc.gridx = 0;
gbc.gridy = 2;
gbc.gridwidth = 2;
add(buttonPanel, gbc);
}

@Override
public void update() {
// Jelenleg nincs dinamikus frissítés
}

    public String getName() {
    return nameField.getText();
    }
}
