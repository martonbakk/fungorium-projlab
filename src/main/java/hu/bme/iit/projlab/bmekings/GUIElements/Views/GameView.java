package hu.bme.iit.projlab.bmekings.GUIElements.Views;

import java.awt.BasicStroke;
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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.FungalBody;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
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
    private final List<String> insectSubTypes;
    private final List<String> fungalSubTypes;

    public GameView(Controller controller, List<List<String>> textures, List<String> fungalSubTypes, List<String> insectSubTypes) {
        super(controller);
        this.insectSubTypes = insectSubTypes;
        this.fungalSubTypes = fungalSubTypes;
        setLayout(new BorderLayout(10, 10));
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
        List<String> insectImagePaths = textures.get(0); // Insect images
        List<String> fungalImagePaths = textures.get(1); // Fungal images
        pentagonPanel = new PentagonPanel(insectImagePaths, fungalImagePaths, fungalSubTypes, insectSubTypes);
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
        private final Map<String, BufferedImage> mycologistSubTypeImages = new HashMap<>();
        private final Map<String, BufferedImage> entomologistSubTypeImages = new HashMap<>();
        private List<String> mycologistSubTypes = new ArrayList<>();
        private List<String> entomologistSubTypes = new ArrayList<>();
        private Tecton selectedTecton;

        public void setTectonCount(int count) {
            this.tectonCount = count;
        }


        public PentagonPanel(List<String> insectImagePaths, List<String> fungalImagePaths, List<String> mycologistSubTypes, List<String> entomologistSubTypes) {
            setPreferredSize(new Dimension(0, 400));
            setBackground(new Color(128, 195, 255));
            this.mycologistSubTypes = mycologistSubTypes;
            this.entomologistSubTypes = entomologistSubTypes;

            // Képek betöltése és társítása altípusokhoz
            String img = null;
            try {
                // Mycologist képek társítása
                for (int i = 0; i < Math.min(fungalImagePaths.size(), mycologistSubTypes.size()); i++) {
                    img = fungalImagePaths.get(i);
                    System.out.println("Mycologist kép betöltése: " + img + ", Erőforrás: " + getClass().getResource(img));
                    if (getClass().getResource(img) == null) {
                        throw new IOException("Nem található erőforrás: " + img);
                    }
                    BufferedImage image = ImageIO.read(getClass().getResource(img));
                    mycologistSubTypeImages.put(mycologistSubTypes.get(i), image);
                }
                // Entomologist képek társítása
                for (int i = 0; i < Math.min(insectImagePaths.size(), entomologistSubTypes.size()); i++) {
                    img = insectImagePaths.get(i);
                    System.out.println("Entomologist kép betöltése: " + img + ", Erőforrás: " + getClass().getResource(img));
                    if (getClass().getResource(img) == null) {
                        throw new IOException("Nem található erőforrás: " + img);
                    }
                    BufferedImage image = ImageIO.read(getClass().getResource(img));
                    entomologistSubTypeImages.put(entomologistSubTypes.get(i), image);
                }
            } catch (IOException e) {
                System.err.println("Nem sikerült betölteni az " + img + " képet: " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Hiba a kép betöltésekor: " + img + "\nFallback körök lesznek használva.", "Hiba", JOptionPane.ERROR_MESSAGE);
            }

            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();
                    Tecton clickedTecton = null;
                    for (int i = 0; i < tectonPolygons.size(); i++) {
                        if (tectonPolygons.get(i).contains(x, y)) {
                            clickedTecton = tectons.get(i);
                            break;
                        }
                    }
                    if (clickedTecton != null) {
                        selectedTecton = (selectedTecton == clickedTecton) ? null : clickedTecton;
                        StringBuilder message = new StringBuilder("Tecton ID: " + clickedTecton.getId() + "\nSzomszédok:\n");
                        for (Tecton neighbor : clickedTecton.getNeighbors()) {
                            message.append("- ").append(neighbor.getId()).append("\n");
                        }
                        JOptionPane.showMessageDialog(PentagonPanel.this, message.toString());
                    } else {
                        selectedTecton = null;
                    }
                    repaint();
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

            ArrayList<Tecton> currentTectons = controller.getGameLogic().map.getTectons();
            if (currentTectons == null || currentTectons.isEmpty()) return;
            tectons.addAll(currentTectons);

            int radius = 20;
            int padding = 20;
            int diameter = radius * 2;
            int spacing = 10;
            int totalWidth = getWidth();

            // Calculate number of columns
            int cols = Math.max(1, (totalWidth - padding * 2 + spacing) / (diameter + spacing));

            // Map tectons to their center coordinates
            Map<Tecton, int[]> tectonCenters = new HashMap<>();
            for (int i = 0; i < tectons.size(); i++) {
                int row = i / cols;
                int col = i % cols;

                int x = padding + col * (diameter + spacing);
                int y = padding + row * (diameter + spacing);
                int centerX = x + radius;
                int centerY = y + radius;
                tectonCenters.put(tectons.get(i), new int[]{centerX, centerY});
            }

            // Draw Hyphal connections
            g2d.setColor(Color.BLACK);
            ArrayList<Mycologist> mycologists = controller.getGameLogic().getMycologists();
            for (Mycologist mycologist : mycologists) {
                for (Hyphal hyphal : mycologist.getHyphalList()) {
                    Tecton base = hyphal.getBase();
                    Tecton connected = hyphal.getConnectedTecton();
                    if (base != null && connected != null) {
                        int[] baseCenter = tectonCenters.get(base);
                        int[] connectedCenter = tectonCenters.get(connected);
                        if (baseCenter != null && connectedCenter != null) {
                            g2d.drawLine(baseCenter[0], baseCenter[1], connectedCenter[0], connectedCenter[1]);
                        }
                    }
                }
            }

            // Draw tectons, fungal bodies, insects, and selection outline
            for (int i = 0; i < tectons.size(); i++) {
                int row = i / cols;
                int col = i % cols;

                int x = padding + col * (diameter + spacing);
                int y = padding + row * (diameter + spacing);

                // Draw tecton (green circle)
                g2d.setColor(new Color(128, 254, 57));
                g2d.fillOval(x, y, diameter, diameter);

                // Draw yellow outline if this is the selected tecton
                if (tectons.get(i) == selectedTecton) {
                    g2d.setColor(Color.YELLOW);
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawOval(x, y, diameter, diameter);
                    g2d.setStroke(new BasicStroke(1));
                }

                // Create polygon for mouse click detection
                Polygon polygon = new Polygon();
                int sides = 20;
                for (int j = 0; j < sides; j++) {
                    double angle = 2 * Math.PI * j / sides;
                    int px = (int) (x + radius + radius * Math.cos(angle));
                    int py = (int) (y + radius + radius * Math.sin(angle));
                    polygon.addPoint(px, py);
                }
                tectonPolygons.add(polygon);

                // Draw FungalBody if present
                FungalBody fungalBody = tectons.get(i).getFungalBody();
                if (fungalBody != null) {
                    Mycologist owner = fungalBody.getOwner();
                    System.out.println("FungalBody owner: " + owner);
                    String subType = owner != null ? owner.getType() : null;
                    System.out.println("FungalBody subtype: " + subType);
                    BufferedImage fungalImage = subType != null ? mycologistSubTypeImages.get(subType) : null;
                    System.out.println("FungalBody image: " + fungalImage);
                    if (fungalImage != null) {
                        int fungalRadius = radius / 2;
                        int fungalDiameter = fungalRadius * 2;
                        int fungalX = x + (radius - fungalRadius);
                        int fungalY = y + (radius - fungalRadius);
                        g2d.drawImage(fungalImage, fungalX, fungalY, fungalDiameter, fungalDiameter, null);
                    } else {
                        // Fallback to red circle
                        g2d.setColor(Color.RED);
                        int fungalRadius = radius / 2;
                        int fungalDiameter = fungalRadius * 2;
                        int fungalX = x + (radius - fungalRadius);
                        int fungalY = y + (radius - fungalRadius);
                        g2d.fillOval(fungalX, fungalY, fungalDiameter, fungalDiameter);
                    }
                }

                // Draw Insect image if present
                if (tectons.get(i).isOccupiedByInsect()) {
                    List<Entomologist> insectOwners = new ArrayList<>();
                    ArrayList<Insect> insects = tectons.get(i).getInsects();
                    for (Insect insect : insects) {
                        if (insect.getOwner() != null) {
                            insectOwners.add(insect.getOwner());
                        }
                    }

                    for (Entomologist owner : insectOwners) {
                        String subType = owner != null ? owner.getType() : null;
                        System.out.println("Insect owner: " + owner);
                        BufferedImage insectImage = subType != null ? entomologistSubTypeImages.get(subType) : null;
                        System.out.println("Insect subtype: " + subType);
                        System.out.println("Insect image: " + insectImage);
                        if (insectImage != null) {
                            int insectRadius = radius / 3;
                            int insectDiameter = insectRadius * 2;
                            int insectX = x + (radius - insectRadius) - insectRadius;
                            int insectY = y + (radius - insectRadius) - insectRadius;
                            g2d.drawImage(insectImage, insectX, insectY, insectDiameter, insectDiameter, null);
                        } else {
                            // Fallback to yellow circle
                            g2d.setColor(Color.YELLOW);
                            int insectRadius = radius / 3;
                            int insectDiameter = insectRadius * 2;
                            int insectX = x + (radius - insectRadius) - insectRadius;
                            int insectY = y + (radius - insectRadius) - insectRadius;
                            g2d.fillOval(insectX, insectY, insectDiameter, insectDiameter);
                        }
                    }
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(0, 400);
        }
    }
}