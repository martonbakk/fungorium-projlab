package hu.bme.iit.projlab.bmekings.GUIElements.Views;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import hu.bme.iit.projlab.bmekings.GUIElements.Controller.Controller;

public class SaveView extends AbstractGameView {
    private JTextField nameField;
    private JButton saveButton;
    private JButton backButton;

    public SaveView(Controller controller) {
        super(controller);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Címke
        JLabel label = new JLabel("Játék mentése", SwingConstants.CENTER);
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
        backButton = new JButton("Kilépés mentés nélkül");
        backButton.addActionListener(e -> {
            // JDialog elérése
            Window dialog = SwingUtilities.getWindowAncestor(this);
            if (dialog != null) {
                dialog.dispose();
            }
            // Szülő JFrame bezárása és program leállítása
            controller.getFrame().dispose();
            System.exit(0);
        });

        saveButton = new JButton("Kilépés és Mentés");
        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Kérlek, adj meg egy mentési nevet!", "Hiba", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                controller.saveGameOnExit(name); // Játék mentése
                JOptionPane.showMessageDialog(this, "Játék sikeresen mentve!", "Mentés", JOptionPane.INFORMATION_MESSAGE);
                // JDialog elérése
                Window dialog = SwingUtilities.getWindowAncestor(this);
                if (dialog != null) {
                    dialog.dispose();
                }
                // Szülő JFrame bezárása és program leállítása
                controller.getFrame().dispose();
                System.exit(0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Hiba a mentés során: " + ex.getMessage(), "Mentési hiba", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(saveButton);
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