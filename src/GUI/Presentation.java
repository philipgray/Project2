package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Presentation {
    static GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0];

    BufferedImage[] slides;
    int presentationLength, presentationIndex;
    //
    public Presentation(BufferedImage[] slideImages) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenSizeWidth = screenSize.getWidth();
        double screenSizeHeight = screenSize.getHeight();

        double diffWidth = screenSizeWidth / 1280;
        double diffHeight = screenSizeHeight / 720;

        this.slides = slideImages;
        this.presentationLength = slides.length;
        this.presentationIndex = 0;

        JFrame presentationFrame = new JFrame("Join Climbing Club!!!!!!!!!");
        presentationFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        device.setFullScreenWindow(presentationFrame);

        JLabel filler = new JLabel(new ImageIcon(scaleBicubic(slideImages[0], diffHeight)));
        filler.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightArrow");
        filler.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftArrow");
        filler.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "escape");

        filler.getActionMap().put("rightArrow", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (canIGo("right")) {
                    presentationIndex++;
                    filler.setIcon(new ImageIcon(scaleBicubic(slideImages[presentationIndex], diffHeight)));
                }
            }
        });

        filler.getActionMap().put("leftArrow", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (canIGo("left")) {
                    presentationIndex--;
                    filler.setIcon(new ImageIcon(scaleBicubic(slideImages[presentationIndex], diffHeight)));
                }
            }
        });

        filler.getActionMap().put("escape", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                presentationFrame.dispose();
            }
        });


        presentationFrame.add(filler);

        presentationFrame.pack();
        presentationFrame.setVisible(true);


    }

    /**
     * Determines whether the user can go left or right.
     * @param direction
     * @return
     */
    private boolean canIGo(String direction) {
        if (direction.equals("right")) {
            return presentationIndex < presentationLength - 1;
        } else if (direction.equals("left")) {
            return presentationIndex > 0;
        } else return false;
    }

    /**
     * https://stackoverflow.com/questions/4216123/how-to-scale-a-bufferedimage
     * For the below two
     */
    public static BufferedImage scaleBicubic(BufferedImage before, double scale) {
        final int interpolation = AffineTransformOp.TYPE_BICUBIC;
        return scale(before, scale, interpolation);
    }

    private static BufferedImage scale(final BufferedImage before, final double scale, final int type) {
        int w = before.getWidth();
        int h = before.getHeight();
        int w2 = (int) (w * scale);
        int h2 = (int) (h * scale);
        BufferedImage after = new BufferedImage(w2, h2, before.getType());
        AffineTransform scaleInstance = AffineTransform.getScaleInstance(scale, scale);
        AffineTransformOp scaleOp = new AffineTransformOp(scaleInstance, type);
        scaleOp.filter(before, after);
        return after;
    }

}
