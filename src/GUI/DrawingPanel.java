package GUI;

import Alex.Slide;
import Alex.SlideDeck;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {

    Graphics2D graphics;
    Slide currentSlide;


    public DrawingPanel(Slide currentSlide) {
        setSize(500, 250);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
        this.currentSlide = currentSlide;
    }

    public void updateSlide(Slide currentSlide) {
        this.currentSlide = currentSlide;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = (Graphics2D) g;
    }
}
