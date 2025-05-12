package hu.bme.iit.projlab.bmekings.GUIElements.Views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import hu.bme.iit.projlab.bmekings.GUIElements.Controller.Controller;

public class StartView extends AbstractGameView{
    public StartView(Controller controller) {
        super(controller);
        setLayout(new BorderLayout());

        // Fő panel a címkéhez és gombokhoz
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Címke
        JLabel titleLabel = new JLabel("Üdvözöl a Fungorium Játék!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(15));

        // Gombok
        JButton startButton = new JButton("Play");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> controller.switchView("PlayerAddView"));

        JButton loadButton = new JButton("Load");
        loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadButton.addActionListener(e -> controller.switchView("LoadView"));

        JButton saveButton = new JButton("Save");
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.addActionListener(e -> controller.switchView("SaveView"));

        // Gombok hozzáadása a panelhez, távolságokkal
        mainPanel.add(startButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(loadButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(saveButton);

        // Fő panel középre helyezése
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(mainPanel);
        add(centerPanel, BorderLayout.CENTER);
    }

    @Override
    public void update() {
        // A StartView nem igényel dinamikus frissítést
    }
}