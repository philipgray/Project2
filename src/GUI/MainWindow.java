package GUI;

import javax.swing.*;

public class MainWindow {
    JFrame frame;
    /**
     *
     * https://stackoverflow.com/questions/3680221/how-can-i-get-screen-resolution-in-java
     */
    private void createGUI() {
        frame = new JFrame("LearningMyFriend");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        /**
         * For fullscreen, and getting window size.
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
         double height = screenSize.getHeight();
         double width = screenSize.getWidth();
         frame.setSize((int) (width / 1.5), (int) (height / 1.5));
         **/
        frame.setLocationRelativeTo(null); // centers the frame in the middle of the user's screen

        StartWindow startWindow = new StartWindow();
        frame.setContentPane(startWindow);
        // frame.pack();

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.createGUI();
        }
        );
    }
}
