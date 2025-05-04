package hu.bme.iit.projlab.bmekings.GUIElements.Views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hu.bme.iit.projlab.bmekings.GUIElements.Controller.Controller;
import hu.bme.iit.projlab.bmekings.Player.Player;

// Konkrét GameView osztály
public class GameView extends AbstractGameView {
    private JTextField nameField;
    private JComboBox<String> typeComboBox;
    private JTextArea playerListArea;

    public GameView(Controller controller) {
        super(controller);
        setLayout(new BorderLayout(10, 10));

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Név:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Típus:"));
        String[] types = {"Mycologist", "Entomologist"};
        typeComboBox = new JComboBox<>(types);
        inputPanel.add(typeComboBox);

        JButton addButton = new JButton("Játékos hozzáadása");
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String type = (String) typeComboBox.getSelectedItem();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Kérlek, add meg a játékos nevét!", "Hiba", JOptionPane.ERROR_MESSAGE);
            } else {
                controller.addPlayer(name, type);
                nameField.setText("");
                update();
            }
        });
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        // Játékos lista
        playerListArea = new JTextArea();
        playerListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(playerListArea);
        add(scrollPane, BorderLayout.CENTER);

        // Vissza gomb
        JButton backButton = new JButton("Vissza");
        backButton.addActionListener(e -> controller.switchView("StartView"));
        JPanel backPanel = new JPanel();
        backPanel.add(backButton);
        add(backPanel, BorderLayout.SOUTH);

        update();
    }

    @Override
    public void update() {
        playerListArea.setText("");
        ArrayList<Player> players = controller.getPlayers();
        if (players.isEmpty()) {
            playerListArea.setText("Nincsenek játékosok a listában.");
        } else {
            for (int i = 0; i < players.size(); i++) {
                playerListArea.append((i + 1) + ". " + players.get(i).toString() + "\n");
            }
        }
    }
}