package GUI;

import javax.swing.*;
import Alex.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Main Window is the foundation of everything!
 * It's a JFrame that can open all the other windows.
 */
public class MainWindow {
    JFrame frame;
    JPanel currentPanel;

    private void createGUI() {
        frame = new JFrame("LearningMyFriend");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null); // centers the frame in the middle of the user's screen
        openStartWindow();
        frame.setVisible(true);
    }

    public void openStartWindow() {
        currentPanel = new StartWindow(this);
        frame.setContentPane(currentPanel);
        frame.pack();
    }

    public void openTemplateWindow() {
        currentPanel = new TemplateWindow(this);
        frame.setContentPane(currentPanel);
        frame.pack();
    }

    public void openPresentationWindow(SlideDeck slideDeck) {
        currentPanel = new PresentationWindow(this, slideDeck);
        frame.setContentPane(currentPanel);
        frame.pack();
    }

    public static void main(String[] args) {
        
        // Support Bengali tooltips
        UIManager.put("ToolTip.font", BengaliFont.getBengaliFont());

        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.createGUI();
        }
        );
    }
}
