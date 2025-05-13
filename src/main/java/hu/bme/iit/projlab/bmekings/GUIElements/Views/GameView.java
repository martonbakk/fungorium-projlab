package hu.bme.iit.projlab.bmekings.GUIElements.Views;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import hu.bme.iit.projlab.bmekings.Entities.Fungal.FungalBody;
import hu.bme.iit.projlab.bmekings.Entities.Fungal.Hyphal;
import hu.bme.iit.projlab.bmekings.Entities.Insect.Insect;
import hu.bme.iit.projlab.bmekings.GUIElements.Controller.Controller;
import hu.bme.iit.projlab.bmekings.Interface.Listener.Listener;
import hu.bme.iit.projlab.bmekings.Logic.GameLogic.GameLogic;
import hu.bme.iit.projlab.bmekings.Map.Tecton.Tecton;
import hu.bme.iit.projlab.bmekings.Player.Entomologist.Entomologist;
import hu.bme.iit.projlab.bmekings.Player.Mycologist.Mycologist;
import hu.bme.iit.projlab.bmekings.Player.Player;

public class GameView extends AbstractGameView implements Listener {
    private final JComboBox<Player> playerComboBox;
    private final JComboBox<String> actionComboBox;
    private final JLabel scoreLabel;
    private final JLabel selectedPlayerLabel;
    private final JTextArea selectedFungusLabel;
    private final JTextArea selectedHyphalLabel;
    private final JTextArea sporesOnTectonLabel;
    private final JTextArea selectedInsectLabel;
    private final PentagonPanel pentagonPanel;
    private final List<String> insectSubTypes;
    private final List<String> fungalSubTypes;

    public GameView(Controller controller, List<List<String>> textures, List<String> insectSubTypes, List<String> fungalSubTypes) {
        super(controller);
        this.insectSubTypes = insectSubTypes;
        this.fungalSubTypes = fungalSubTypes;
        setLayout(new BorderLayout(10, 10));
        // Felső panel
        JPanel topPanel = new JPanel(new BorderLayout());
        scoreLabel = new JLabel("", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        topPanel.add(scoreLabel, BorderLayout.NORTH);

        selectedPlayerLabel = new JLabel("Kiválasztott játékos: Nincs", SwingConstants.CENTER);
        selectedPlayerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        topPanel.add(selectedPlayerLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        JPanel gamePanel = new JPanel(new BorderLayout());

        JPanel westJPanel = new JPanel();
        westJPanel.setLayout(new BoxLayout(westJPanel, BoxLayout.PAGE_AXIS));
        
        selectedFungusLabel = new JTextArea("Kiválasztott\nGombatest: Nincs");
        selectedFungusLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        selectedFungusLabel.setSize(100, Short.MAX_VALUE);
        selectedFungusLabel.setPreferredSize(selectedFungusLabel.getPreferredSize());

        selectedFungusLabel.setEditable(false);
        selectedFungusLabel.setLineWrap(true);
        selectedFungusLabel.setFocusable(false);
        selectedFungusLabel.setOpaque(false);
        
        westJPanel.add(selectedFungusLabel);
        westJPanel.add(Box.createRigidArea(new Dimension(0,10)));

        selectedHyphalLabel = new JTextArea("Kiválasztott\nFonál: Nincs");
        selectedHyphalLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        selectedHyphalLabel.setSize(100, Short.MAX_VALUE);
        selectedHyphalLabel.setPreferredSize(selectedHyphalLabel.getPreferredSize());

        selectedHyphalLabel.setEditable(false);
        selectedHyphalLabel.setLineWrap(true);
        selectedHyphalLabel.setFocusable(false);
        selectedHyphalLabel.setOpaque(false);
        
        westJPanel.add(selectedHyphalLabel);

        gamePanel.add(westJPanel, BorderLayout.WEST);

        // Pentagon panel
        List<String> insectImagePaths = textures.get(0); // Insect images
        List<String> fungalImagePaths = textures.get(1); // Fungal images
        pentagonPanel = new PentagonPanel(insectImagePaths, fungalImagePaths, fungalSubTypes, insectSubTypes);
        gamePanel.add(pentagonPanel, BorderLayout.CENTER);

        JPanel eastJPanel = new JPanel();
        eastJPanel.setLayout(new BoxLayout(eastJPanel, BoxLayout.PAGE_AXIS));

        selectedInsectLabel = new JTextArea("Kiválasztott\nRovar: Nincs");
        selectedInsectLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        selectedInsectLabel.setSize(100, Short.MAX_VALUE);
        selectedInsectLabel.setPreferredSize(selectedInsectLabel.getPreferredSize());

        selectedInsectLabel.setEditable(false);
        selectedInsectLabel.setLineWrap(true);
        selectedInsectLabel.setFocusable(false);
        selectedInsectLabel.setOpaque(false);

        eastJPanel.add(selectedInsectLabel);
        eastJPanel.add(Box.createRigidArea(new Dimension(0,10)));

        sporesOnTectonLabel = new JTextArea("A Tektonon lévő\nSpórák száma: -");
        sporesOnTectonLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        sporesOnTectonLabel.setSize(100, Short.MAX_VALUE);
        sporesOnTectonLabel.setPreferredSize(sporesOnTectonLabel.getPreferredSize());

        sporesOnTectonLabel.setEditable(false);
        sporesOnTectonLabel.setLineWrap(true);
        sporesOnTectonLabel.setFocusable(false);
        sporesOnTectonLabel.setOpaque(false);
        
        eastJPanel.add(sporesOnTectonLabel);
        eastJPanel.add(Box.createRigidArea(new Dimension(0,10)));

        gamePanel.add(eastJPanel, BorderLayout.EAST);

        add(gamePanel, BorderLayout.CENTER);

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
                doAction(selectedPlayer, selectedAction);
                selectedFungusLabel.setText("Kiválasztott\nGombatest: Nincs");
                selectedHyphalLabel.setText("Kiválasztott\nFonál: Nincs");
                sporesOnTectonLabel.setText("A Tektonon lévő\nSpórák száma: -");
                selectedInsectLabel.setText("Kiválaszott\nRovar: Nincs");
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

    private void doAction(Player selectedPlayer, String selectedAction) {
        if (selectedPlayer instanceof Mycologist) {
            selectedPlayer = (Mycologist) selectedPlayer;
            // ("Grow Fungal Body", "Speed Up Development", "Shoot Spore", "Eat Insect", "Grow Hyphal", "Level Up")
            // ("Eat Spore", "Move", "Cut Hyphal")
            switch (selectedAction) {
                case "Select Fungus":
                    selectedPlayer.SelectAction(1, GameLogic.getParams());
                    break;
                case "Select Hyphal":
                    selectedPlayer.SelectAction(2, GameLogic.getParams());
                    break;
                case "Grow Fungal Body":
                    selectedPlayer.SelectAction(3, GameLogic.getParams());
                    break;
                case "Grow Fungal Body From Spore":
                    selectedPlayer.SelectAction(4, GameLogic.getParams());
                    break;
                case "Grow Hyphal":
                    selectedPlayer.SelectAction(5, GameLogic.getParams());
                    break;
                case "Destroy Fungus":
                    selectedPlayer.SelectAction(6, GameLogic.getParams());
                    break;
                case "Shoot Spore":
                    selectedPlayer.SelectAction(7, GameLogic.getParams());
                    break;
                case "Speed Up Development":
                    selectedPlayer.SelectAction(8, GameLogic.getParams());
                    break;
                case "Grow Hyphal From Hyphal":
                    selectedPlayer.SelectAction(9, GameLogic.getParams());
                    break;
                case "Eat Insect":
                    selectedPlayer.SelectAction(10, GameLogic.getParams());
                    break;
                case "Level Up":
                    selectedPlayer.SelectAction(11, GameLogic.getParams());
                    break;
                case "Grow Hyphal Barni":
                    selectedPlayer.SelectAction(12, GameLogic.getParams());
                    break;
                default:
                    System.out.println("Invalid action type");
            }
        }
        else if (selectedPlayer instanceof Entomologist) {
            selectedPlayer = (Entomologist) selectedPlayer;
            
            //  ("Eat Spore", "Move", "Cut Hyphal"); // Teszt adatok
            switch (selectedAction) {
                case "Select Insect":
                    selectedPlayer.SelectAction(1, GameLogic.getParams());
                    break;
                case "Select Tecton":
                    selectedPlayer.SelectAction(2, GameLogic.getParams());
                    break;
                case "Move":
                    selectedPlayer.SelectAction(3, GameLogic.getParams());
                    break;
                case "Eat Spore":
                    selectedPlayer.SelectAction(4, GameLogic.getParams());
                    break;
                case "Cut Hyphal":
                    selectedPlayer.SelectAction(5, GameLogic.getParams());
                    break;
                default:
                    System.out.println("Invalid action type");
            }
        }

        controller.getGameLogic();
       
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
                        sporesOnTectonLabel.setText("A Tektonon lévő Spórák száma: " + selectedTecton.getSpores().size());
                        TectonPanel tectonPanel = new TectonPanel(selectedTecton);
                        JFrame newFrame = new JFrame("Új ablak");
                        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // csak az új ablak záródjon be
                        newFrame.add(tectonPanel);
                        newFrame.pack();  // automatikus méret a panelhez
                        newFrame.setLocationRelativeTo(null);  // képernyő közepére
                        newFrame.setVisible(true);
                    } else {
                        selectedTecton = null;
                    }
                    GameLogic.getParams().selectedTecton = selectedTecton;
                    System.out.println(GameLogic.getParams().selectedTecton.getId());
                    Player selectedPlayer = (Player) playerComboBox.getSelectedItem();
                    
                    if (selectedPlayer instanceof Mycologist && selectedTecton.isOccupiedByFungus()) {
                            Mycologist selectedMyc = (Mycologist) selectedPlayer;
                            int answer = JOptionPane.showConfirmDialog(null, "Do you want to Select [" + selectedTecton.getFungalBody().getId() + "]", "Fungal Body Chooser", JOptionPane.YES_NO_OPTION);
                            if (answer == 0) {
                                selectedMyc.selectFungus(selectedTecton.getFungalBody());
                                GameLogic.getParams().selectedFungus = selectedTecton.getFungalBody();
                                System.out.println(GameLogic.getParams().selectedFungus.getId());
                                selectedFungusLabel.setText("Kiválasztott\nGombatest: " + GameLogic.getParams().selectedFungus.getId());
                            }
                        }
                    else if (selectedPlayer instanceof Entomologist && selectedTecton.isOccupiedByInsect()) {
                            selectedTecton.getInsects().forEach(i -> {
                                    Entomologist selectedEnt = (Entomologist) selectedPlayer;
                                    selectedEnt.selectInsect(i);
                                    if (selectedEnt.getSelectedInsect() != null)
                                        selectedInsectLabel.setText("Kiválaszott\nRovar: " + selectedEnt.getSelectedInsect().getId());
                            });
                        }
                        
                    // TODO
                    else if (selectedPlayer instanceof Mycologist){
                        for(Tecton neighbor : selectedTecton.getNeighbors()){
                            //for(Hyphal h : selectedP)
                        }

                    }    
                         
                    repaint();
                }
            });
        }

        class TectonPanel extends JPanel {
    private final Tecton centralTecton;
    private static final Color CENTRAL_COLOR = new Color(0, 100, 0);
    private static final Color NEIGHBOR_COLOR = new Color(144, 238, 144);
    private static final int CENTRAL_SIZE = 75;
    private static final int NEIGHBOR_SIZE = 60;
    private static final int FUNGUS_SIZE = 25;
    private static final int INSECT_SIZE = 15;

    public TectonPanel(Tecton centralTecton) {
        this.centralTecton = centralTecton;
        setPreferredSize(new Dimension(800, 800));
        setBackground(new Color(240, 255, 240));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        Point2D center = new Point2D.Double(width/2.0, height/2.0);
        int radius = Math.min(width, height) / 3;

        Map<Tecton, Point2D> positions = new HashMap<>();
        positions.put(centralTecton, center);

        List<Tecton> neighbors = centralTecton.getNeighbors();
        int maxNeighbors = Math.min(neighbors.size(), 12);
        double angleStep = 2 * Math.PI / maxNeighbors;
        
        for (int i = 0; i < maxNeighbors; i++) {
            Tecton neighbor = neighbors.get(i);
            double angle = angleStep * i;
            double x = center.getX() + radius * Math.cos(angle);
            double y = center.getY() + radius * Math.sin(angle);
            positions.put(neighbor, new Point2D.Double(x, y));
        }

        // Draw connections
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(4));
        Point2D centralPos = positions.get(centralTecton);
        
        for (Tecton connected : centralTecton.getConnectedNeighbors().keySet()) {
            if (positions.containsKey(connected)) {
                Point2D targetPos = positions.get(connected);
                drawConnection(g2d, centralPos, targetPos, CENTRAL_SIZE, NEIGHBOR_SIZE);
            }
        }

        // Draw tectons
        drawTecton(g2d, centralTecton, center, CENTRAL_SIZE);
        for (Tecton neighbor : neighbors) {
            if (positions.containsKey(neighbor)) {
                drawTecton(g2d, neighbor, positions.get(neighbor), NEIGHBOR_SIZE);
            }
        }
    }

    private void drawConnection(Graphics2D g2d, Point2D start, Point2D end, int startSize, int endSize) {
        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getY();
        double distance = Math.hypot(dx, dy);

        if (distance > 0) {
            double unitX = dx / distance;
            double unitY = dy / distance;
            
            double startX = start.getX() + unitX * (startSize/2.0);
            double startY = start.getY() + unitY * (startSize/2.0);
            double endX = end.getX() - unitX * (endSize/2.0);
            double endY = end.getY() - unitY * (endSize/2.0);
            
            g2d.drawLine((int)startX, (int)startY, (int)endX, (int)endY);
        }
    }

    private void drawTecton(Graphics2D g2d, Tecton tecton, Point2D position, int size) {
        // Draw main body
        g2d.setColor(tecton == centralTecton ? CENTRAL_COLOR : NEIGHBOR_COLOR);
        int halfSize = size/2;
        g2d.fillOval((int)(position.getX()-halfSize), (int)(position.getY()-halfSize), size, size);

        // Draw white border
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.WHITE);
        g2d.drawOval((int)(position.getX()-halfSize), (int)(position.getY()-halfSize), size, size);

        // Draw fungus
        if (tecton.isOccupiedByFungus()) {
            FungalBody fungalBody = tecton.getFungalBody();
            Mycologist owner = fungalBody.getOwner();
                    // System.out.println("FungalBody owner: " + owner);
                    String subType = owner != null ? owner.getType() : null;
                    // System.out.println("FungalBody subtype: " + subType);
                    BufferedImage fungalImage = subType != null ? mycologistSubTypeImages.get(subType) : null;
                    // System.out.println("FungalBody image: " + fungalImage);
                    if (fungalImage != null) {
                        int fungalRadius = FUNGUS_SIZE / 2;
                        int fungalDiameter = fungalRadius * 2;
                        int fungalX = (int)position.getX() - fungalRadius;
                        int fungalY = (int)position.getY() - fungalRadius;
                        g2d.drawImage(fungalImage, fungalX, fungalY, fungalDiameter, fungalDiameter, null);

                    } else {
                        // Fallback to red circle
                        g2d.setColor(Color.RED);
                        int fungalRadius = FUNGUS_SIZE / 2;
                        int fungalDiameter = fungalRadius * 2;
                        int fungalX = (int)position.getX() - fungalRadius;
                        int fungalY = (int)position.getY() - fungalRadius;
                        g2d.fillOval(fungalX, fungalY, fungalDiameter, fungalDiameter);
                    }
        }

        // Draw insects
        if (tecton.isOccupiedByInsect()) {
            List<Insect> insects = tecton.getInsects();
            int count = Math.min(insects.size(), 4);
            double distance = size/2.0 + INSECT_SIZE/2.0;
            
            for (int i = 0; i < count; i++) {
                double angle = Math.toRadians(90 - (i * 360.0 / count));
                double x = position.getX() + distance * Math.cos(angle);
                double y = position.getY() - distance * Math.sin(angle);
                drawInsect(g2d, insects.get(i), new Point2D.Double(x, y));
            }
        }
    }

    private void drawInsect(Graphics2D g2d, Insect insect, Point2D position) {
                        String subType = insect.getOwner() != null ? insect.getOwner().getType() : null;
        BufferedImage insectImage = subType != null ? entomologistSubTypeImages.get(subType) : null;
                        // System.out.println("Insect subtype: " + subType);
                        // System.out.println("Insect image: " + insectImage);
                        if (insectImage != null) {
                            int insectRadius = INSECT_SIZE;
                            int insectDiameter = insectRadius * 2;
                            int insectX = (int) position.getX() + (INSECT_SIZE - insectRadius) - insectRadius;
                            int insectY = (int) position.getY() + (INSECT_SIZE - insectRadius) - insectRadius;
                            g2d.drawImage(insectImage, insectX, insectY, insectDiameter, insectDiameter, null);
                        } else {
                            // Fallback to yellow circle
                            g2d.setColor(Color.YELLOW);
                            int insectRadius = INSECT_SIZE / 3;
                            int insectDiameter = insectRadius * 2;
                            int insectX = (int) position.getX() + (INSECT_SIZE - insectRadius) - insectRadius;
                            int insectY = (int)position.getY() + (INSECT_SIZE - insectRadius) - insectRadius;
                            g2d.fillOval(insectX, insectY, insectDiameter, insectDiameter);
                        }
    }
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
                    // System.out.println("FungalBody owner: " + owner);
                    String subType = owner != null ? owner.getType() : null;
                    // System.out.println("FungalBody subtype: " + subType);
                    BufferedImage fungalImage = subType != null ? mycologistSubTypeImages.get(subType) : null;
                    // System.out.println("FungalBody image: " + fungalImage);
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
                        // System.out.println("Insect owner: " + owner);
                        BufferedImage insectImage = subType != null ? entomologistSubTypeImages.get(subType) : null;
                        // System.out.println("Insect subtype: " + subType);
                        // System.out.println("Insect image: " + insectImage);
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