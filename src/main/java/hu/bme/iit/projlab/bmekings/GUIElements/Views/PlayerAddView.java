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
import hu.bme.iit.projlab.bmekings.Player.Entomologist.Entomologist;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.Mycologist;
import hu.bme.iit.projlab.bmekings.Player.Player;

public class PlayerAddView extends AbstractGameView {
    private JTextField nameField;
    private JComboBox<String> typeComboBox;
    private JTextArea mycologistListArea;
    private JTextArea entomologistListArea;
    private JButton startGameButton;

    public PlayerAddView(Controller controller) {
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

        // Játékos listák (két külön táblázat)
        JPanel listPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Mycologist lista
        JPanel mycologistPanel = new JPanel(new BorderLayout());
        mycologistPanel.add(new JLabel("Mycologistok"), BorderLayout.NORTH);
        mycologistListArea = new JTextArea();
        mycologistListArea.setEditable(false);
        JScrollPane mycologistScrollPane = new JScrollPane(mycologistListArea);
        mycologistPanel.add(mycologistScrollPane, BorderLayout.CENTER);
        listPanel.add(mycologistPanel);

        // Entomologist lista
        JPanel entomologistPanel = new JPanel(new BorderLayout());
        entomologistPanel.add(new JLabel("Entomologistok"), BorderLayout.NORTH);
        entomologistListArea = new JTextArea();
        entomologistListArea.setEditable(false);
        JScrollPane entomologistScrollPane = new JScrollPane(entomologistListArea);
        entomologistPanel.add(entomologistScrollPane, BorderLayout.CENTER);
        listPanel.add(entomologistPanel);

        add(listPanel, BorderLayout.CENTER);

        // Gombok panel
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Vissza");
        backButton.addActionListener(e -> controller.switchView("StartView"));
        buttonPanel.add(backButton);

        startGameButton = new JButton("Start Game");
        startGameButton.setEnabled(false);
        startGameButton.addActionListener(e -> {
            controller.switchView("GameView"); 
            controller.getGameLogic().map.generateMap();
            }
        );
            
        buttonPanel.add(startGameButton);

        add(buttonPanel, BorderLayout.SOUTH);

        update();
    }

    @Override
    public void update() {
        mycologistListArea.setText("");
        entomologistListArea.setText("");
        ArrayList<Mycologist> mycologists = controller.getGameLogic().getMycologists();
        ArrayList<Entomologist> entomologists = controller.getGameLogic().getEntomologists();

        int mycologistCount = mycologists.size();
        int entomologistCount = entomologists.size();

        if (mycologistCount == 0) {
            mycologistListArea.setText("Nincsenek Mycologist játékosok.");
        } else {
            int index = 1;
            for (Mycologist player : mycologists) {
                mycologistListArea.append(index + ". " + player.toString() + "\n");
                index++;
            }
        }

        if (entomologistCount == 0) {
            entomologistListArea.setText("Nincsenek Entomologist játékosok.");
        } else {
            int index = 1;
            for (Player player : entomologists) {
                entomologistListArea.append(index + ". " + player.toString() + "\n");
                index++;
            }
        }

        // Start Game gomb engedélyezése, ha van játékos és a számuk egyenlő
        startGameButton.setEnabled(mycologistCount > 0 && entomologistCount > 0 && mycologistCount == entomologistCount);
    }
}