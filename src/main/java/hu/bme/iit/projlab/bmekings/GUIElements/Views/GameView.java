package hu.bme.iit.projlab.bmekings.GUIElements.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import hu.bme.iit.projlab.bmekings.GUIElements.Controller.Controller;
import hu.bme.iit.projlab.bmekings.Interface.Listener.Listener;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Entomologist.Entomologist;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.Mycologist;
import hu.bme.iit.projlab.bmekings.Player.Player;

public class GameView extends AbstractGameView implements Listener {
    private final JComboBox<Player> playerComboBox;
    private final JComboBox<String> actionComboBox;
    private final JLabel scoreLabel;
    private final JLabel selectedPlayerLabel;
    private final PentagonPanel pentagonPanel;

    public GameView(Controller controller) {
        super(controller);
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Felső panel
        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        menuPanel.setOpaque(false);

        

        scoreLabel = new JLabel("", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        topPanel.add(scoreLabel, BorderLayout.NORTH);

        selectedPlayerLabel = new JLabel("Kiválasztott játékos: Nincs", SwingConstants.CENTER);
        selectedPlayerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        topPanel.add(selectedPlayerLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Pentagon panel
        pentagonPanel = new PentagonPanel();
        add(pentagonPanel, BorderLayout.CENTER);

        // Alsó panel
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        playerComboBox = new JComboBox<>();
        actionComboBox = new JComboBox<>();

        playerComboBox.addActionListener(e -> {
            updateStatusLabels();
            Player selectedPlayer = (Player) playerComboBox.getSelectedItem();
            actionComboBox.removeAllItems();
            if (selectedPlayer != null && selectedPlayer.getAvailableActions() != null) {
                for (String action : selectedPlayer.getAvailableActions()) {
                    actionComboBox.addItem(action);
                }
            }
        });

        JPanel comboPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        comboPanel.add(playerComboBox);
        comboPanel.add(actionComboBox);
        bottomPanel.add(comboPanel, BorderLayout.CENTER);

        JButton actionButton = new JButton("Akció végrehajtása");
        actionButton.addActionListener(e -> {
            Player selectedPlayer = (Player) playerComboBox.getSelectedItem();
            String selectedAction = (String) actionComboBox.getSelectedItem();
            if (selectedPlayer != null && selectedAction != null) {
                JOptionPane.showMessageDialog(this, selectedPlayer.getUserName() + " végrehajtja: " + selectedAction);
                // TODO: Konkrét akció logika implementálása
            }
        });
        bottomPanel.add(actionButton, BorderLayout.EAST);

        JButton backButton = new JButton("Simulate Steps");
        backButton.addActionListener(e -> {
            controller.getGameLogic().tick();
            update();
        });
        bottomPanel.add(backButton, BorderLayout.WEST);

        add(bottomPanel, BorderLayout.SOUTH);

        controller.getGameLogic().addListener(this);
        update();
    }

    private void updateStatusLabels() {
        StringBuilder scoreText = new StringBuilder();
        ArrayList<Mycologist> mycologists = controller.getGameLogic().getMycologists();
        ArrayList<Entomologist> entomologists = controller.getGameLogic().getEntomologists();
        int totalPlayers = mycologists.size() + entomologists.size();
        int currentIndex = 0;

        for (Mycologist mycologist : mycologists) {
            scoreText.append(mycologist.getUserName()).append(": ").append(mycologist.getScore());
            if (currentIndex < totalPlayers - 1) scoreText.append(" | ");
            currentIndex++;
        }

        for (Entomologist entomologist : entomologists) {
            scoreText.append(entomologist.getUserName()).append(": ").append(entomologist.getScore());
            if (currentIndex < totalPlayers - 1) scoreText.append(" | ");
            currentIndex++;
        }

        scoreLabel.setText(scoreText.toString());

        Player selectedPlayer = (Player) playerComboBox.getSelectedItem();
        if (selectedPlayer != null) {
            selectedPlayerLabel.setText("Kiválasztott játékos: " + selectedPlayer.getUserName());
        } else {
            selectedPlayerLabel.setText("Kiválasztott játékos: Nincs");
        }
    }

    @Override
    public void update() {
        if (playerComboBox.getItemCount() == 0) {
            ArrayList<Mycologist> mycologists = controller.getGameLogic().getMycologists();
            ArrayList<Entomologist> entomologists = controller.getGameLogic().getEntomologists();
            for (Mycologist mycologist : mycologists) playerComboBox.addItem(mycologist);
            for (Entomologist entomologist : entomologists) playerComboBox.addItem(entomologist);
        }

        int tectonCount;
        try {
            tectonCount = controller.getGameLogic().map.getTectons().size();
        } catch (Exception e) {
            tectonCount = 0;
            System.err.println("Hiba a tektonok számának lekérdezésekor: " + e.getMessage());
        }
        pentagonPanel.setTectonCount(tectonCount);
        pentagonPanel.repaint();
        updateStatusLabels();
    }

    private class PentagonPanel extends JPanel {
        private int tectonCount;
        private final ArrayList<Polygon> tectonPolygons = new ArrayList<>();
        private final ArrayList<Tecton> tectons = new ArrayList<>();

        public void setTectonCount(int count) {
            this.tectonCount = count;
        }

        public PentagonPanel() {
            setPreferredSize(new Dimension(0, 400));
            setBackground(new Color(173, 216, 230));

            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();
                    for (int i = 0; i < tectonPolygons.size(); i++) {
                        if (tectonPolygons.get(i).contains(x, y)) {
                            Tecton clickedTecton = tectons.get(i);
                            StringBuilder message = new StringBuilder("Tecton ID: " + clickedTecton.getId() + "\nSzomszédok:\n");
                            for (Tecton neighbor : clickedTecton.getNeighbors()) {
                                message.append("- ").append(neighbor.getId()).append("\n");
                            }
                            JOptionPane.showMessageDialog(PentagonPanel.this, message.toString());
                            break;
                        }
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            tectonPolygons.clear();
            tectons.clear();
        
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(new Color(139, 69, 19));
        
            ArrayList<Tecton> currentTectons = controller.getGameLogic().map.getTectons();
            if (currentTectons == null || currentTectons.isEmpty()) return;
            tectons.addAll(currentTectons);
        
            int radius = 20;
            int padding = 20;
            int diameter = radius * 2;
            int spacing = 10;
            int totalWidth = getWidth();
        
            // Megállapítjuk, hány oszlop fér ki
            int cols = Math.max(1, (totalWidth - padding * 2 + spacing) / (diameter + spacing));
        
            for (int i = 0; i < tectons.size(); i++) {
                int row = i / cols;
                int col = i % cols;
        
                int x = padding + col * (diameter + spacing);
                int y = padding + row * (diameter + spacing);
        
                g2d.fillOval(x, y, diameter, diameter);
            }
        }


        @Override
        public Dimension getPreferredSize() {
            return new Dimension(0, 400);
        }
    }
}
