package GUI;

import Alex.LineComponent;
import Alex.Slide;
import Alex.SlideComponent;
import Alex.SlideDeck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.io.File;

public class DrawingPanel extends JPanel {

    Graphics2D graphics;
    Slide currentSlide;
    DrawingState drawingState;

    String pencilCursorFile = new File("images/PencilCursor.png").getAbsolutePath();
    Cursor pencilCursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(pencilCursorFile).getImage(), new Point(0, 30), "Drawing Pencil");

    int clickX1, clickX2, clickY1, clickY2;

    public DrawingPanel(Slide currentSlide) {
        setSize(500, 250);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
        this.currentSlide = currentSlide;
        this.drawingState = DrawingState.NONE;
        addMouseListener(new MouseHandler());
    }

    public void updateSlide(Slide currentSlide) {
        this.currentSlide = currentSlide;
        repaint();
    }

    public void updateState(DrawingState drawingState) {
        this.drawingState = drawingState;
        
        // Change the cursor
        if(drawingState == DrawingState.DRAW){
            this.setCursor( pencilCursor );
        } else {
            this.setCursor(new Cursor( Cursor.DEFAULT_CURSOR ));
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = (Graphics2D) g;

        for (LineComponent component : currentSlide.getLineComponents()) {
            graphics.setColor(component.getColor());
            graphics.setStroke(new BasicStroke(component.getWidth()));
            int[] start = component.getStartPoint();
            int[] end = component.getEndPoint();
            graphics.draw(new Line2D.Double(start[0], start[1], end[0], end[1]));
        }
    }

    private class MouseHandler extends MouseAdapter {

        public void mousePressed(MouseEvent event) {
            clickX1 = event.getX();
            clickY1 = event.getY();
            System.out.println(drawingState);
            if (drawingState == DrawingState.DRAW) {
                addMouseMotionListener(this);
            }
        }

        /**
         * mouseDragged might seem unimportant when we have mouseReleased,
         * however if we're trying to freeform draw or have some sort of
         * shape to visualize what size shape / item you're building, we
         * want to keep track of the X and Y constantly.
         **/

        public void mouseDragged(MouseEvent event) {
            System.out.println(drawingState);
            if (drawingState == DrawingState.DRAW) {
                System.out.println("drawing?");
                clickX1 = event.getX();
                clickY1 = event.getY();
                clickX2 = clickX1;
                clickY2 = clickY1;

                currentSlide.addComponent(new LineComponent(clickX1, clickY1, clickX2, clickY2));
                repaint();
            }
        }

        public void mouseReleased(MouseEvent event) {
            removeMouseMotionListener(this);
        }

    } // MouseHandler
}
