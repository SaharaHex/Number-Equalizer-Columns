package ui;

import model.GameController;
import model.SequenceController;
import model.SequenceProvider;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

import static ui.DisplayHelper.*;

class MenuUI extends JPanel {
    private GameController game;
    private int menuSelection = 0; // Keeps track of the current selection (0: Random, 1: Sequence)
    public MenuUI(MainFrame frame) {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 1));

        JLabel menuLabel = new JLabel("Choose Type of Game", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Arial", Font.BOLD, 24));
        menuPanel.add(menuLabel);

        JButton randomButton = new JButton("Random Numbers");
        JButton sequenceButton = new JButton("Sequence of Numbers");

        styleButton(randomButton);
        styleButton(sequenceButton);

        menuPanel.add(randomButton);
        menuPanel.add(sequenceButton);

        add(menuPanel, BorderLayout.CENTER);
        add(createInstructionsPanel(), BorderLayout.CENTER);

        // Initial selection highlighting
        updateMenuHighlight(menuSelection, randomButton, sequenceButton);

        KeyListener menuKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP, KeyEvent.VK_W ->
                            menuSelection = (menuSelection == 0) ? 1 : 0; // Move up in the menu
                    case KeyEvent.VK_DOWN, KeyEvent.VK_S ->
                            menuSelection = (menuSelection == 1) ? 0 : 1; // Move down in the menu
                    case KeyEvent.VK_ENTER, KeyEvent.VK_SPACE -> {
                        removeKeyListener(this); // Remove menu listener on "Enter"
                        if (menuSelection == 0) {
                            game = new GameController(); // Random numbers mode
                        } else {
                            Map.Entry<String, int[]> customSequence = getCustomSequence();
                            game = new GameController(customSequence.getValue(), customSequence.getKey()); // Sequence mode
                        }
                        remove(menuPanel);
                        frame.switchPanel(new ColumnGameUI(frame, game)); //Load game
                    }
                }
                updateMenuHighlight(menuSelection, randomButton, sequenceButton);
            }
        };

        addKeyListener(menuKeyListener); //Add listener for keyboard actions

        // Action listeners for buttons
        randomButton.addActionListener(e -> {
            removeKeyListener(menuKeyListener); // Remove menu listener for button interaction
            game = new GameController(); // Random numbers mode
            frame.switchPanel(new ColumnGameUI(frame, game)); //test
        });

        sequenceButton.addActionListener(e -> {
            removeKeyListener(menuKeyListener); // Remove menu listener for button interaction
            Map.Entry<String, int[]> customSequence = getCustomSequence();
            game = new GameController(customSequence.getValue(), customSequence.getKey()); // Sequence mode
            frame.switchPanel(new ColumnGameUI(frame, game)); //test
        });

        setFocusable(true);
        SwingUtilities.invokeLater(this::requestFocusInWindow); // Asynchronously request focus
    }

    private static Map.Entry<String, int[]> getCustomSequence() {
        // Create an instance of Sequence through the SequenceProvider interface
        SequenceProvider sequenceProvider = new SequenceController();
        return sequenceProvider.getRandomSeed();
    }
}
