/*
 * For fullscreen, and getting window size.
 * Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
 * double height = screenSize.getHeight();
 * double width = screenSize.getWidth();
 * frame.setSize((int) (width / 1.5), (int) (height / 1.5));
 */

package GUI;

import javax.swing.*;
import Alex.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainWindow {
    JFrame frame;
    JPanel currentPanel;
    /**
     * hi
     * https://stackoverflow.com/questions/3680221/how-can-i-get-screen-resolution-in-java
     */
    private void createGUI() {
        frame = new JFrame("LearningMyFriend");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null); // centers the frame in the middle of the user's screen
        openStartWindow();
        frame.setVisible(true);
    }

    /*
    public void setSlideDeck(SlideDeck slideDeck) {
        this.slideDeck = slideDeck;
        System.out.println("Loaded slideDeck");
    }
    */

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
