package ui;

import javax.swing.*;
import java.awt.*;

public class DisplayHelper{
    public static Color lightYellow () {
        return new Color(255, 253, 85);
    }
    public static Color lightGreen () { return new Color(34, 139, 34);
    }
    public static void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }

    public static void updateMenuHighlight(int menuSelection, JButton buttonOne, JButton buttonTwo) {
        if (menuSelection == 0) {
            buttonOne.setBackground(lightYellow()); // Set colour);
            buttonOne.setForeground(Color.BLACK);
            buttonTwo.setBackground(Color.DARK_GRAY);
            buttonTwo.setForeground(Color.WHITE);
        } else {
            buttonTwo.setBackground(lightYellow()); // Set colour);
            buttonTwo.setForeground(Color.BLACK);
            buttonOne.setBackground(Color.DARK_GRAY);
            buttonOne.setForeground(Color.WHITE);
        }
    }

    public static JPanel createInstructionsPanel() {
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(3, 1));
        JLabel ruleLabel = new JLabel("<< Match all four columns, with the same value >>", SwingConstants.CENTER);
        ruleLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        controlsPanel.add(ruleLabel);
        JLabel controlsLabel = new JLabel("Controls", SwingConstants.CENTER);
        controlsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        controlsPanel.add(controlsLabel);
        JLabel keysLabel = new JLabel("<html>Use the Arrow Keys or WASD<br>Space Bar or Enter as Action</html>", SwingConstants.CENTER);
        keysLabel.setFont(new Font("Arial", Font.BOLD, 18));
        controlsPanel.add(keysLabel);
        return controlsPanel;
    }

    public static JPanel createTopButtonPanel(JButton buttonWest, JButton buttonEast) {
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(buttonWest, BorderLayout.WEST); // Position button on the left
        buttonPanel.add(buttonEast, BorderLayout.EAST); // Position button on the right
        return buttonPanel;
    }
    public static JPanel createStatsPanel(JLabel imageLabel, JLabel statsMessage) {
        JPanel statsPanel = new JPanel(new BorderLayout(0, 0));
        statsPanel.add(statsMessage, BorderLayout.WEST); // Position statistics on the left
        statsPanel.add(imageLabel, BorderLayout.EAST); // Position image on the right
        return statsPanel;
    }

    public static void restartGame(MainFrame frame) {
        frame.switchPanel(new MenuUI(frame));
    }

    public static void exitGame() {
        System.exit(0);
    }
}