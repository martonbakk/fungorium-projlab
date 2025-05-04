package hu.bme.iit.projlab.bmekings.GUIElements.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import hu.bme.iit.projlab.bmekings.Logic.GameLogic.GameLogic;
import hu.bme.iit.projlab.bmekings.Player.Entomologist.Entomologist;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.Mycologist;
import hu.bme.iit.projlab.bmekings.Player.Player;

public class GameView extends AbstractGameView implements Listener {
    private JComboBox<Player> playerComboBox;
    private JLabel scoreLabel;
    private JLabel selectedPlayerLabel;
    private PentagonPanel pentagonPanel;
    private ArrayList<Player> players;

    public GameView(Controller controller) {
        super(controller);
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Felső panel: pontszámok és kiválasztott játékos
        JPanel topPanel = new JPanel(new BorderLayout());
        scoreLabel = new JLabel("", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        topPanel.add(scoreLabel, BorderLayout.NORTH);

        selectedPlayerLabel = new JLabel("", SwingConstants.CENTER);
        selectedPlayerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        topPanel.add(selectedPlayerLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Pentagon panel a tektonokhoz
        pentagonPanel = new PentagonPanel();
        add(pentagonPanel, BorderLayout.CENTER);

        // Alsó panel: ComboBox és akció gomb
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        playerComboBox = new JComboBox<>();
        playerComboBox.addActionListener(e -> updateStatusLabels());
        bottomPanel.add(playerComboBox, BorderLayout.CENTER);

        JButton actionButton = new JButton("Action select");
        actionButton.addActionListener(e -> {
            Player selectedPlayer = (Player) playerComboBox.getSelectedItem();
            if (selectedPlayer != null) {
                JOptionPane.showMessageDialog(this, selectedPlayer.getUserName() + " akciót hajtott végre!");
                // TODO: Konkrét akció logika implementálása
            }
        });
        bottomPanel.add(actionButton, BorderLayout.EAST);

        // Játék leállítása gomb
        JButton backButton = new JButton("Simulate Steps");
        backButton.addActionListener(e -> {
            
        });
        bottomPanel.add(backButton, BorderLayout.WEST);

        add(bottomPanel, BorderLayout.SOUTH);

        // Regisztrálás a GameLogic Ticker-jére
        controller.getGameLogic().addListener(this);

        update();
    }

    private void updateStatusLabels() {
        // Pontszámok egy sorban
        StringBuilder scoreText = new StringBuilder();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            scoreText.append(player.getUserName()).append(": ").append(player.getScore());
            if (i < players.size() - 1) {
                scoreText.append(" | ");
            }
        }
        scoreLabel.setText(scoreText.toString());

        // Kiválasztott játékos
        Player selectedPlayer = (Player) playerComboBox.getSelectedItem();
        if (selectedPlayer != null) {
            selectedPlayerLabel.setText("Kiválasztott játékos: " + selectedPlayer.getUserName());
        } else {
            selectedPlayerLabel.setText("Kiválasztott játékos: Nincs");
        }
    }

    @Override
    public void update() {
        GameLogic gameLogic = controller.getGameLogic();
        ArrayList<Mycologist> mycologists = gameLogic.getMycologists();
        ArrayList<Entomologist> entomologists = gameLogic.getEntomologists();

        // Játékosok listájának frissítése
        players = new ArrayList<>();
        players.addAll(mycologists);
        players.addAll(entomologists);

        // ComboBox frissítése
        playerComboBox.removeAllItems();
        for (Player player : players) {
            playerComboBox.addItem(player);
        }

        // Pentagon panel frissítése
        pentagonPanel.setTectonCount(gameLogic.map.getTectons().size());
        pentagonPanel.repaint();

        // Státusz címkék frissítése
        updateStatusLabels();
    }

    // Egyedi panel az ötszögek rajzolásához
    private class PentagonPanel extends JPanel {
        private int tectonCount;

        public PentagonPanel() {
            setPreferredSize(new Dimension(0, 100)); // Fix magasság a pentagonokhoz
            setBackground(Color.WHITE);
        }

        public void setTectonCount(int count) {
            this.tectonCount = count;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.BLUE);

            int pentagonSize = 30;
            int spacing = 10;
            int y = 10;

            for (int i = 0; i < tectonCount; i++) {
                int x = i * (pentagonSize + spacing) + spacing;
                int[] xPoints = new int[5];
                int[] yPoints = new int[5];
                for (int j = 0; j < 5; j++) {
                    double angle = Math.toRadians(72 * j - 90);
                    xPoints[j] = x + (int) (pentagonSize / 2 * Math.cos(angle));
                    yPoints[j] = y + (int) (pentagonSize / 2 * Math.sin(angle));
                }
                g2d.fillPolygon(xPoints, yPoints, 5);
            }
        }
    }
}