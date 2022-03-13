package GUI;

import Alex.SlideDeck;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class PresentationWindow extends JPanel implements ActionListener {
    MainWindow mw;

    JButton newSlide, fontSelect, fontSize, createBullet, createSubBullet, insertLink,
            present, save, saveAs, backgroundColor, draw, nextSlide, previousSlide, text;
    DrawingPanel drawingPanel;
    SlideDeck slideDeck;

    private static final File saveLocation = new File("saved_slides").getAbsoluteFile();

    public PresentationWindow(MainWindow mw, SlideDeck slideDeck) {
        this.mw = mw;
        this.slideDeck = slideDeck;

        System.out.println("There are " +slideDeck.getNumSlides()+" slides loaded.");

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        /*
         * Once again, I am doing a bad thing.
         * https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
         */
        GridBagConstraints constraints = new GridBagConstraints();

        // New Alex.Slide Button --------------------------------------------------------------------
        newSlide = new JButton("Add New Slide");
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
        save.setEnabled(slideDeck.saveLocationExists());

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
        text = new JButton("Text");
        text.addActionListener(this);
        text.setToolTipText("Placeholder Text");

        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 9;
        constraints.gridy = 0;

        add(text, constraints);

        // Create Sub-Bullet Button ------------------------------------------------------
        createSubBullet = new JButton("Bulleted List");
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


        // Trying something here! --------------------------------------------------------------
        drawingPanel = new DrawingPanel(slideDeck.getCurrentSlide());

        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridheight = GridBagConstraints.REMAINDER;
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.ipadx = 1000;
        constraints.ipady = 250;

        add(drawingPanel, constraints);

        this.setNextAndPrevious();


    }

    private void setNextAndPrevious() {
        System.out.println("Current Index: "+slideDeck.getCurrentIndex());
        System.out.println("Number of Slides: "+slideDeck.getNumSlides());

        previousSlide.setEnabled(slideDeck.getCurrentIndex() != 0);
        nextSlide.setEnabled(slideDeck.getCurrentIndex() < slideDeck.getNumSlides() - 1);
    }

    private void saveAsDialog() {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(PresentationWindow.saveLocation);
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Slide Deck Files", "json");
        fileChooser.setFileFilter(fileFilter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        

        int file = fileChooser.showSaveDialog(this);
        System.out.println("File Chooser output: "+file);
        if (file == 0) {
            File whereToSave = fileChooser.getSelectedFile();
            String savePath = whereToSave.getAbsolutePath();

            // Make sure it saves as a json file
            if(! savePath.substring(savePath.length() - 5).equals(".json")){
                whereToSave = new File (whereToSave.getAbsolutePath() + ".json");
            }

            System.out.println(whereToSave);
            slideDeck.saveAs(whereToSave);
            save.setEnabled(true);
        }
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == newSlide) {
            System.out.println("newSlide!");
            slideDeck.addNewSlideHere();
            this.setNextAndPrevious();
            drawingPanel.updateSlide(slideDeck.getCurrentSlide());

        } else if (event.getSource() == present) {
            System.out.println("present!");
        } else if (event.getSource() == saveAs) {
            this.saveAsDialog();
        } else if (event.getSource() == save) {
            boolean operationComplete = slideDeck.save();
            if (!operationComplete) {
                this.saveAsDialog();
            }
        }  else if (event.getSource() == draw) {
            drawingPanel.updateState(DrawingState.DRAW);
            System.out.println("drawing mode");
        }  else if (event.getSource() == text) {
            drawingPanel.updateState(DrawingState.TEXT);
            // enableKeyListener();
            System.out.println("text mode");
        } else if (event.getSource() == fontSelect) {
            System.out.println("fontSelect!");
        } else if (event.getSource() == nextSlide) {
            slideDeck.nextSlide();
            this.setNextAndPrevious();
            drawingPanel.updateSlide(slideDeck.getCurrentSlide());

        } else if (event.getSource() == previousSlide) {
            slideDeck.previousSlide();
            this.setNextAndPrevious();
            drawingPanel.updateSlide(slideDeck.getCurrentSlide());
        }

    }
}
