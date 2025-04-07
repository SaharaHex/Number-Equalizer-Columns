package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel currentPanel;

    public MainFrame() {
        // Set up the frame
        setTitle("Number Equalizer Columns");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Start with the MenuUI
        switchPanel(new MenuUI(this));
    }

    public void switchPanel(JPanel newPanel) {
        if (currentPanel != null) {
            remove(currentPanel); // Remove the current panel
        }

        currentPanel = newPanel;
        add(currentPanel); // Add the new panel

        if (newPanel instanceof MenuUI) {
            currentPanel.setLayout(new GridLayout(3, 1));
        }

        revalidate(); // Refresh layout
        repaint(); // Repaint the frame
    }
}