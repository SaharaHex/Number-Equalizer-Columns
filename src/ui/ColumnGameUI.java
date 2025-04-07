package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.File;
import java.io.IOException;

import model.GameController;
import static ui.DisplayHelper.*;

class ColumnGameUI extends JPanel {
    public ColumnGameUI(MainFrame frame, GameController game) {
        JPanel columnPanel = new JPanel();
        columnPanel.setLayout(new GridLayout(1, 4));
        JLabel[] columnLabels = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            columnLabels[i] = new JLabel("", SwingConstants.CENTER);
            columnLabels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            columnLabels[i].setOpaque(true);
            columnLabels[i].setBackground(Color.LIGHT_GRAY);
            columnLabels[i].setFont(new Font("Arial", Font.BOLD, 20));
            columnPanel.add(columnLabels[i]);
        }

        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel numberLabel = new JLabel("Current Number: " + game.getNumber(), SwingConstants.CENTER);
        numberLabel.setFont(new Font("Arial", Font.BOLD, 24));
        numberLabel.setForeground(lightGreen()); // Set colour

        JButton menuButton = new JButton("Menu");
        JButton exitButton = new JButton("Exit");

        styleButton(menuButton);
        styleButton(exitButton);

        // Add button panel to the top center
        topPanel.add(createTopButtonPanel(menuButton, exitButton), BorderLayout.NORTH);
        topPanel.add(numberLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton leftButton = new JButton("Left");
        JButton dropButton = new JButton("Drop");
        JButton rightButton = new JButton("Right");

        styleButton(leftButton);
        styleButton(dropButton);
        styleButton(rightButton);

        buttonPanel.add(leftButton);
        buttonPanel.add(dropButton);
        buttonPanel.add(rightButton);

        menuButton.addActionListener(playAgainEvent -> restartGame(frame));
        exitButton.addActionListener(playAgainEvent -> exitGame());

        leftButton.addActionListener(e -> {
            game.moveLeft();
            updateColumns(columnLabels, numberLabel, game);
            requestFocusInWindow(); // Regain focus on the main frame
        });

        rightButton.addActionListener(e -> {
            game.moveRight();
            updateColumns(columnLabels, numberLabel, game);
            requestFocusInWindow(); // Regain focus on the main frame
        });

        dropButton.addActionListener(e -> {
            game.dropNumber();
            game.turnNumber();
            if (game.checkWin()) {
                // Update the UI to display "Play Again" button and reset the game
                frame.switchPanel(new ResultUI(frame, game));
            }
            updateColumns(columnLabels, numberLabel, game);
            requestFocusInWindow(); // Regain focus on the main frame
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT, KeyEvent.VK_A -> {
                        game.moveLeft();
                    }
                    case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> {
                        game.moveRight();
                    }
                    case KeyEvent.VK_ENTER, KeyEvent.VK_SPACE -> {
                        game.dropNumber();
                        game.turnNumber();
                        if (game.checkWin()) {
                            // Update the UI to display "Play Again" button and reset the game
                            frame.switchPanel(new ResultUI(frame, game));
                        }
                    }
                }
                updateColumns(columnLabels, numberLabel, game);
            }
        });

        setLayout(new BorderLayout()); // Ensures the panel can stretch
        add(topPanel, BorderLayout.NORTH);
        add(columnPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        updateColumns(columnLabels, numberLabel, game);
        setFocusable(true); // Ensure the panel is focusable

        SwingUtilities.invokeLater(this::requestFocusInWindow); // Asynchronously request focus
    }

    private void updateColumns(JLabel[] columnLabels, JLabel numberLabel, GameController game) {
        Font customFont;
        try {
            // Attempt to load the custom font from the file
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/komika-axis.regular.ttf")).deriveFont(50f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont); // Register the font for use in the application
        } catch (FontFormatException | IOException e) {
            e.printStackTrace(); // Handle errors if the font file is invalid or missing
            // Fall back to the default Arial font with size 50
            customFont = new Font("Arial", Font.BOLD, 50);
            System.out.println("Custom font not found; falling back to default Arial font.");
        }

        int[] totals = game.getColumnTotals();
        for (int i = 0; i < totals.length; i++) {
            columnLabels[i].setText("<html>" + totals[i]
                    + (i == game.getCurrentColumn() ? "<br><b>*</b>" : "") + "</html>");
            columnLabels[i].setBackground(i == game.getCurrentColumn() ? (lightYellow()) : Color.LIGHT_GRAY);

            // Apply the selected font to each column label
            columnLabels[i].setFont(customFont);
            columnLabels[i].setForeground(lightGreen()); // Set colour
        }

        // Update the number label
        numberLabel.setText("Current Number: " + game.getNumber());
    }
}