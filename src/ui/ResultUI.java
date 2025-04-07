package ui;

import model.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static ui.DisplayHelper.*;

public class ResultUI extends JPanel{

    private int menuSelection = 0; // Keeps track of the current selection (0: Restart, 1: Exit)

    public ResultUI(MainFrame frame, GameController game) {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 1));

        // Update the UI to display "Play Again" button and statistics
        JLabel winMessage = new JLabel("You've balanced all the columns!", SwingConstants.CENTER);
        winMessage.setFont(new Font("Arial", Font.BOLD, 24));
        winMessage.setForeground(lightGreen()); // Set colour
        winMessage.setPreferredSize(new Dimension(600, 50));

        JLabel statsMessage = new JLabel("<html>Number of Moves: " + game.getTotalTurns() + "<br></html>", SwingConstants.CENTER);
        statsMessage.setFont(new Font("Arial", Font.BOLD, 20));
        statsMessage.setForeground(Color.BLACK);
        statsMessage.setPreferredSize(new Dimension(300, 200));

        JButton playAgainButton = new JButton("Play Again");
        styleButton(playAgainButton);
        playAgainButton.addActionListener(playAgainEvent -> restartGame(frame));

        JButton exitButton = new JButton("Exit");
        styleButton(exitButton);
        exitButton.addActionListener(playAgainEvent -> exitGame());

        ImageIcon resultIcon = new ImageIcon("src/assets/resultImage.gif"); // Load the animated GIF using ImageIcon
        JLabel gifLabel = new JLabel(resultIcon); // Display the GIF in a JLabel
        gifLabel.setPreferredSize(new Dimension(300, 200));

        menuPanel.add(winMessage);
        menuPanel.add(playAgainButton);
        menuPanel.add(exitButton);

        add(menuPanel, BorderLayout.NORTH);
        add(createStatsPanel(gifLabel,statsMessage),BorderLayout.SOUTH);

        // Initial selection highlighting
        updateMenuHighlight(menuSelection, playAgainButton, exitButton);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        menuSelection = (menuSelection == 0) ? 1 : 0; // Move up in the menu
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        menuSelection = (menuSelection == 1) ? 0 : 1; // Move down in the menu
                        break;
                    case KeyEvent.VK_ENTER:
                    case KeyEvent.VK_SPACE:
                        removeKeyListener(this); // Remove menu listener on "Enter"
                        if (menuSelection == 0) {
                            restartGame(frame);
                        } else {
                            System.exit(0);
                        }
                        break;
                }
                updateMenuHighlight(menuSelection, playAgainButton, exitButton);
            }
        });

        SwingUtilities.invokeLater(this::requestFocusInWindow); // Asynchronously request focus
    }
}
