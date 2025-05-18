package hu.bme.iit.projlab.bmekings.GUIElements.Views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

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
    private JComboBox<String> subTypeComboBox;
    private JTextArea mycologistListArea;
    private JTextArea entomologistListArea;
    private JButton startGameButton;
    private JButton addButton;
    private List<String> mycologistTypes = new ArrayList<>();
    private List<String> entomologistTypes = new ArrayList<>();

    public PlayerAddView(Controller controller, List<String> insectTypes, List<String> fungalTypes) {
        super(controller);

        this.mycologistTypes = insectTypes;
        this.entomologistTypes = fungalTypes;
        setLayout(new BorderLayout(10, 10));

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Név:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Típus:"));
        String[] types = {"Mycologist", "Entomologist"};
        typeComboBox = new JComboBox<>(types);
        typeComboBox.setSelectedIndex(0);
        inputPanel.add(typeComboBox);

        inputPanel.add(new JLabel("Altípus:"));
        subTypeComboBox = new JComboBox<>();
        inputPanel.add(subTypeComboBox);

        addButton = new JButton("Játékos hozzáadása");

        // Altípusok inicializálása a kezdeti típus alapján
        updateSubTypeComboBox();

        // Típusváltás kezelése
        typeComboBox.addActionListener(e -> updateSubTypeComboBox());

        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String type = (String) typeComboBox.getSelectedItem();
            String subType = (String) subTypeComboBox.getSelectedItem();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Kérlek, add meg a játékos nevét!", "Hiba", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (subType == null || "Nincs elérhető altípus".equals(subType)) {
                JOptionPane.showMessageDialog(this, "Nincs elérhető altípus, válassz másik típust!", "Hiba", JOptionPane.ERROR_MESSAGE);
                return;
            }
            controller.addPlayer(name, type, subType);
            nameField.setText("");
            // Altípus eltávolítása a megfelelő listáról
            if ("Mycologist".equals(type)) {
                entomologistTypes.remove(subType);
            } else {
                mycologistTypes.remove(subType);
            }
            // Altípus legördülő menü frissítése
            updateSubTypeComboBox();
            update();
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
        backButton.addActionListener(e -> {
            controller.switchView("StartView");
        });
        buttonPanel.add(backButton);

        startGameButton = new JButton("Start Game");
        startGameButton.setEnabled(false);
        startGameButton.addActionListener(e -> {
            controller.getFrame().setSize(1220, 800);
            controller.switchView("GameView");
            controller.getFrame().setLocationRelativeTo(null);
            controller.getGameLogic().map.generateMap();
        });
        buttonPanel.add(startGameButton);

        add(buttonPanel, BorderLayout.SOUTH);

        update();
    }

    private void updateSubTypeComboBox() {
        subTypeComboBox.removeAllItems();
        String selectedType = (String) typeComboBox.getSelectedItem();
        List<String> availableSubTypes = "Mycologist".equals(selectedType) ? entomologistTypes : mycologistTypes;
        if (availableSubTypes.isEmpty()) {
            subTypeComboBox.addItem("Nincs elérhető altípus");
            subTypeComboBox.setEnabled(false);
            addButton.setEnabled(false);
        } else {
            for (String subType : availableSubTypes) {
                subTypeComboBox.addItem(subType);
            }
            subTypeComboBox.setEnabled(true);
            addButton.setEnabled(true);
        }
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