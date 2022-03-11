package GUI;

import Alex.SlideDeck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PresentationWindow extends JPanel implements ActionListener {
    MainWindow mw;

    JButton newSlide, fontSelect, fontSize, createBullet, createSubBullet, insertLink,
            present, save, saveAs, backgroundColor, draw, nextSlide, previousSlide;
    DrawingPanel drawingPanel;
    SlideDeck slideDeck;

    public PresentationWindow(MainWindow mw, SlideDeck slideDeck) {
        this.mw = mw;
        this.slideDeck = slideDeck;

        System.out.println(slideDeck.getNumSlides());

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        /*
         * Once again, I am doing a bad thing.
         * https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
         */
        GridBagConstraints constraints = new GridBagConstraints();

        // New Alex.Slide Button --------------------------------------------------------------------
        newSlide = new JButton("New Slide");
        newSlide.addActionListener(this);
        newSlide.setToolTipText("Placeholder Text");

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 3;
        constraints.gridheight = 3;
        constraints.ipady = 60;
        constraints.ipadx = 50;

        constraints.gridx = 0;
        constraints.gridy = 0;

        add(newSlide, constraints);
        // Present Button --------------------------------------------------------------------------
        present = new JButton("Present");
        present.addActionListener(this);
        present.setToolTipText("Placeholder Text");

        constraints.insets = new Insets(0, 10, 0, 10);

        constraints.gridx = 3;

        add(present, constraints);

        // Save Button

        saveAs = new JButton("Save As...");
        saveAs.addActionListener(this);
        saveAs.setToolTipText("Placeholder Text");

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 0, 10, 10);

        constraints.gridx = 6;
        constraints.gridy = 0;

        add(saveAs, constraints);

        // Save As Button --------------------------------------------------------------------------

        save = new JButton("Save");
        save.addActionListener(this);
        save.setToolTipText("Placeholder Text");

        constraints.gridy = 1;

        add(save, constraints);

        // Background Color ------------------------------------------------------------------------

        backgroundColor = new JButton("Background Color");
        backgroundColor.addActionListener(this);
        backgroundColor.setToolTipText("Placeholder Text");

        constraints.gridx = 7;
        constraints.gridy = 0;

        add(backgroundColor, constraints);

        // Drawing Button --------------------------------------------------------------------------

        draw = new JButton("Draw");
        draw.addActionListener(this);
        draw.setToolTipText("Placeholder Text");

        constraints.gridy = 1;

        add(draw, constraints);

        // Font Select Button ----------------------------------------------------------------------
        fontSelect = new JButton("Font");
        fontSelect.addActionListener(this);
        fontSelect.setToolTipText("Placeholder Text");

        constraints.gridx = 8;
        constraints.gridy = 0;

        add(fontSelect, constraints);

        // FontSize Button --------------------------------------------------------------------------
        fontSize = new JButton("Font Size");
        fontSize.addActionListener(this);
        fontSize.setToolTipText("Placeholder Text");

        constraints.gridy = 1;

        add(fontSize, constraints);

        // CreateBullet Button ------------------------------------------------------
        createBullet = new JButton("Create Bullet");
        createBullet.addActionListener(this);
        createBullet.setToolTipText("Placeholder Text");

        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 9;
        constraints.gridy = 0;

        add(createBullet, constraints);

        // Create Sub-Bullet Button ------------------------------------------------------
        createSubBullet = new JButton("Create Sub-Bullet");
        createSubBullet.addActionListener(this);
        createSubBullet.setToolTipText("Placeholder Text");

        constraints.gridy = 1;

        add(createSubBullet, constraints);

        // Next Slide Button ------------------------------------------------------------------
        nextSlide = new JButton("Next Slide");
        nextSlide.addActionListener(this);
        nextSlide.setToolTipText("Placeholder Text");

        constraints.gridx = 10;
        constraints.gridy = 0;

        add(nextSlide, constraints);

        // Previous Slide Button ---------------------------------------------------------------
        previousSlide = new JButton("Previous Slide");
        previousSlide.addActionListener(this);
        previousSlide.setToolTipText("Placeholder Text");

        constraints.gridy = 1;

        add(previousSlide, constraints);

        previousSlide.setEnabled(false);

        // Trying something here! --------------------------------------------------------------
        drawingPanel = new DrawingPanel();

        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridheight = GridBagConstraints.REMAINDER;
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.ipadx = 1000;
        constraints.ipady = 750;

        add(drawingPanel, constraints);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == newSlide) {
            System.out.println("newSlide!");
        } else if (event.getSource() == present) {
            System.out.println("present!");
        } else if (event.getSource() == saveAs) {
            final JFileChooser fileChooser = new JFileChooser();
            int file = fileChooser.showSaveDialog(this);
        } else if (event.getSource() == save) {
            boolean operationComplete = slideDeck.save();
            if (!operationComplete) {

            }
            return;
        } else if (event.getSource() == fontSelect) {
            System.out.println("fontSelect!");
        }
    }
}
