package GUI;

import Alex.*;
import Alex.TextComponent;
import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.io.File;
import java.security.Key;

public class DrawingPanel extends JPanel {

    Graphics2D graphics;
    SlideDeck slideDeck;
    Slide currentSlide;
    DrawingState drawingState;

    String pencilCursorFile = new File("images/PencilCursor.png").getAbsolutePath();
    Image pencilIcon = new ImageIcon(pencilCursorFile).getImage();

    Cursor pencilCursor = Toolkit.getDefaultToolkit().createCustomCursor(pencilIcon, new Point(0, 0), "Drawing Pencil");


    int clickX1, clickX2, clickY1, clickY2;

    public DrawingPanel(Slide currentSlide) {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
        this.currentSlide = currentSlide;
        this.drawingState = DrawingState.NONE;
        this.addMouseListener(new MouseHandler());
        setFocusable(true);
    }

    /**
     * Because we had to have two :)
     * @param deck
     */
    public DrawingPanel(SlideDeck deck) {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
        this.slideDeck = deck;
        this.currentSlide = slideDeck.getCurrentSlide(); // WHY
        this.drawingState = DrawingState.NONE;
        this.addMouseListener(new MouseHandler());
        setFocusable(true);
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
        } else if (drawingState == DrawingState.TEXT){
            this.setCursor(new Cursor( Cursor.TEXT_CURSOR));
        } else {
            this.setCursor(new Cursor( Cursor.DEFAULT_CURSOR ));
        }
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.setBackground(backgroundColor);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = (Graphics2D) g;

        // Paint the background first
        if(currentSlide.getBackground().getType() == ComponentType.Color){
            Color bg = ((ColorBackground)currentSlide.getBackground()).getColor();
            g.setColor(bg);
            g.fillRect(0, 0, 1300, 740);
            g.setColor(Color.BLACK);
        }

        for (SlideComponent component : currentSlide) {
            if (component.getType() == ComponentType.Line) {
                LineComponent lineComponent = (LineComponent) component;
                graphics.setColor(lineComponent.getColor());
                graphics.setStroke(new BasicStroke(lineComponent.getWidth()));
                int[] start = lineComponent.getStartPoint();
                int[] end = lineComponent.getEndPoint();

                Line2D.Double t = new Line2D.Double();
                graphics.draw(new Line2D.Double(start[0], start[1], end[0], end[1]));
            } else if (component.getType() == ComponentType.Text) {
                TextComponent textComponent = (TextComponent) component;
                int[] coords = textComponent.getTopLeftCoord();
                graphics.setFont(textComponent.getFont());
                graphics.drawString(textComponent.getText(), coords[0], coords[1] - 10);
            }
        }
    }

    /**
     * Paint this panel onto a Buffered Image
     * @param output the BufferedImage to paint to
     */
    public BufferedImage paintBufferedImage(){
        BufferedImage output = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);

        paintComponent(output.createGraphics());

        return output;
    }

    public void removeText(InvisibleTextField textField) {
        TextComponent textComponent = new PureText(textField.getText());

        int x1 = textField.getX();
        int y1 = textField.getY();

        int x2 = x1 + textField.getWidth();
        int y2 = y1 + textField.getHeight();

        textComponent.setTopLeftCoord(textField.getX(), textField.getY());
        textComponent.setBottomRightCoord(x2, y2);
        textComponent.setFont(slideDeck.getCurrentFont());

        currentSlide.addComponent(textComponent);
        remove(textField);
        repaint();
    }

    private class MouseHandler extends MouseAdapter {

        public void mousePressed(MouseEvent event) {
            clickX1 = event.getX();
            clickY1 = event.getY();
            if (drawingState == DrawingState.DRAW) {
                addMouseMotionListener(this);
            }
            if (drawingState == DrawingState.TEXT) {

                InvisibleTextField tf = new InvisibleTextField(DrawingPanel.this);
                tf.setFont(slideDeck.getFont());
                tf.setLocation(event.getPoint());
                add(tf);
                tf.requestFocusInWindow();
            }
        }

        /**
         * mouseDragged might seem unimportant when we have mouseReleased,
         * however if we're trying to freeform draw or have some sort of
         * shape to visualize what size shape / item you're building, we
         * want to keep track of the X and Y constantly.
         **/

        public void mouseDragged(MouseEvent event) {
            if (drawingState == DrawingState.DRAW) {
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
