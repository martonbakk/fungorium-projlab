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



public class SaveView extends AbstractGameView {
    private JTextField pathField;
    private JTextField nameField;

    public SaveView(Controller controller) {
        super(controller);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margók az elemek között
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Címke
        JLabel label = new JLabel("Játék mentése", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(label, gbc);

        // Fájl elérési út mező
        JLabel pathLabel = new JLabel("hova", SwingConstants.LEFT);
        pathLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(pathLabel, gbc);

        pathField = new JTextField(20);
        pathField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(pathField, gbc);

        // Mentés neve mező
        JLabel nameLabel = new JLabel("neve", SwingConstants.LEFT);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(nameLabel, gbc);

        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(nameField, gbc);


        // Vissza gomb
        JButton backButton = new JButton("Vissza");
        backButton.addActionListener(e -> controller.switchView("StartView"));
        JButton saveButton = new JButton("Mentés");
        saveButton.addActionListener(e -> {
            String path = pathField.getText();
            String name = nameField.getText();
            // Itt implementálható a mentési logika a Controller segítségével
            System.out.println("Mentés: " + path + "/" + name);
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(saveButton);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);
    }

    @Override
    public void update() {
        // Jelenleg nincs dinamikus frissítés, később implementálható
    }

    // Getter metódusok a szövegmezők tartalmának lekérdezéséhez
    public String getPath() {
        return pathField.getText();
    }

    public String getName() {
        return nameField.getText();
    }
}


/*
public class SaveView extends AbstractGameView {
    public SaveView(Controller controller) {
        super(controller);
        setLayout(new BorderLayout());

        // Címke
        JLabel label = new JLabel("Játék mentése", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.NORTH);

        // Üzenet
        JLabel message = new JLabel("Mentési funkcionalitás implementáció alatt", SwingConstants.CENTER);
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
*/