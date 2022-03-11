package GUI;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {

    Graphics2D graphics;

    public DrawingPanel() {
        setSize(500, 750);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = (Graphics2D) g;
    }
}
