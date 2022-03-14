package GUI;

import Alex.ColorBackground;
import Alex.Slide;
import Alex.SlideDeck;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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

    JButton newSlide, fontSelect, textSize, createBullet, createSubBullet, insertLink,
            present, save, saveAs, backgroundColor, draw, nextSlide, previousSlide, text,
            savePDF, deleteSlide, slideCountButton;

    String locationText;

    JLabel positionLabel, slideCount, toolSelected;

    DrawingPanel drawingPanel;
    SlideDeck slideDeck;

    DrawingState drawingState;

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
        newSlide.setToolTipText("নতুন স্লাইড যোগ করুন");

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 3;
        constraints.gridheight = 3;
        constraints.ipady = 50;
        constraints.ipadx = 50;

        constraints.gridx = 0;
        constraints.gridy = 0;

        add(newSlide, constraints);
        // Present Button --------------------------------------------------------------------------
        present = new JButton("Present");
        present.addActionListener(this);
        present.setToolTipText("বর্তমান");

        constraints.insets = new Insets(0, 10, 0, 10);

        constraints.gridx = 3;

        add(present, constraints);

        // Save Button

        saveAs = new JButton("Save As...");
        saveAs.addActionListener(this);
        saveAs.setToolTipText("সংরক্ষণ করুন...");

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
        save.setToolTipText("উপস্থাপনা সংরক্ষণ করুন");

        constraints.gridy = 1;

        add(save, constraints);
        save.setEnabled(slideDeck.saveLocationExists());

        // SavePDF Button

        savePDF = new JButton("Save PDF");
        savePDF.addActionListener(this);
        savePDF.setToolTipText("পিডিএফ সংরক্ষণ করুন");

        constraints.gridy = 2;
        add(savePDF, constraints);

        // Delete Slide Button

        deleteSlide = new JButton("Delete Slide");
        deleteSlide.addActionListener(this);
        deleteSlide.setToolTipText("স্লাইড মুছুন");

        constraints.gridx = constraints.gridx + 1;
        constraints.gridy = 0;

        add(deleteSlide, constraints);

        // ShowSlide Button

        slideCountButton = new JButton();
        slideCountButton.addActionListener(this);

        constraints.fill = GridBagConstraints.NONE;
        constraints.gridy = constraints.gridy + 1;
        add(slideCountButton, constraints);

        // ShowSlideNumber

        slideCount = new JLabel();
        if (slideDeck.getCurrentSlide().shouldShowNumber()) {
            slideCountButton.setText("Hide Slide Number");
            slideCountButton.setToolTipText("স্লাইড কাউন্ট লুকান"); // hide
            slideCount.setText("Slide Number: Shown");
        } else {
            slideCountButton.setText("Show Slide Number");
            slideCountButton.setToolTipText("স্লাইড কাউন্ট দেখান"); // show
            slideCount.setText("Slide Number: Hidden");
        }

        constraints.gridy = constraints.gridy + 1;

        add(slideCount, constraints);

        // Background Color ------------------------------------------------------------------------

        backgroundColor = new JButton("Background Color");
        backgroundColor.addActionListener(this);
        backgroundColor.setToolTipText("পেছনের রং");

        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = constraints.gridx + 1;
        constraints.gridy = 0;

        add(backgroundColor, constraints);

        // text side button

        textSize = new JButton("Text Size");
        textSize.addActionListener(this);
        textSize.setToolTipText("অক্ষরের আকার");

        constraints.gridy = constraints.gridy + 1;

        add(textSize, constraints);

        // Drawing Button --------------------------------------------------------------------------

        draw = new JButton("Drawing Tool");
        draw.addActionListener(this);
        draw.setToolTipText("অঙ্কন টুল");

        constraints.gridx = constraints.gridx + 1;
        constraints.gridy = 0;

        add(draw, constraints);

        // Font Select Button ----------------------------------------------------------------------
        /**
        fontSelect = new JButton("Font");
        fontSelect.addActionListener(this);
        fontSelect.setToolTipText("Placeholder Text");

        constraints.gridx = constraints.gridx + 1;
        constraints.gridy = 0;

        add(fontSelect, constraints);
         **/

        // CreateBullet Button ------------------------------------------------------
        text = new JButton("Text Tool");
        text.addActionListener(this);
        text.setToolTipText("টেক্সট টুল");

        constraints.gridy = constraints.gridy + 1;

        add(text, constraints);

        // FontSize Button --------------------------------------------------------------------------


        // Information JLabel

        toolSelected = new JLabel("Tool Selected: None");
        constraints.gridy = constraints.gridy + 1;

        add(toolSelected, constraints);

        // Create Sub-Bullet Button ------------------------------------------------------
        /**
        createSubBullet = new JButton("Bulleted List");
        createSubBullet.addActionListener(this);
        createSubBullet.setToolTipText("Placeholder Text");

        constraints.gridy = 1;

        add(createSubBullet, constraints);
        **/
        // Next Slide Button ------------------------------------------------------------------
        nextSlide = new JButton("Next Slide");
        nextSlide.addActionListener(this);
        nextSlide.setToolTipText("Placeholder Text");

        constraints.gridx = constraints.gridx + 1;
        constraints.gridy = 0;

        add(nextSlide, constraints);

        // Previous Slide Button ---------------------------------------------------------------
        previousSlide = new JButton("Previous Slide");
        previousSlide.addActionListener(this);
        previousSlide.setToolTipText("Placeholder Text");

        constraints.gridy = 1;

        add(previousSlide, constraints);

        // ????
        locationText = "Slide: "+slideDeck.getCurrentIndex()+1+"/"+slideDeck.getNumSlides();
        positionLabel = new JLabel(locationText);

        constraints.gridy = 2;
        add(positionLabel, constraints);



        // Trying something here! --------------------------------------------------------------
        drawingPanel = new DrawingPanel(slideDeck);

        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridheight = GridBagConstraints.REMAINDER;
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.ipadx = 1280;
        constraints.ipady = 720;

        add(drawingPanel, constraints);

        slideDeck.setCurrentFont(new Font("Serif", Font.PLAIN, 15));
        this.setNextAndPrevious();
        this.deleteLogic();
    }

    private void updateLocationText() {
        this.locationText = "Slide: "+(slideDeck.getCurrentIndex()+1)+"/"+slideDeck.getNumSlides();
        positionLabel.setText(locationText);
    }

    /**
     * Show or Hide
     * Method used for determining whether we're showing counting this slide in presentation view.
     *
     * @param currentSlide
     */
    private void showOrHide(Slide currentSlide) {
        if (currentSlide.shouldShowNumber()) {
            currentSlide.hideNumber();
            slideCountButton.setText("Show Slide Number");
            slideCountButton.setToolTipText("স্লাইড কাউন্ট দেখান"); // show
            slideCount.setText("Slide Number: Hidden");
        } else {
            currentSlide.showNumber();
            slideCountButton.setText("Hide Slide Number");
            slideCountButton.setToolTipText("স্লাইড কাউন্ট লুকান"); // hide
            slideCount.setText("Slide Number: Shown");
        }
    }

    private void setNextAndPrevious() {
        updateLocationText();
        System.out.println("Current Index: "+slideDeck.getCurrentIndex());
        System.out.println("Number of Slides: "+slideDeck.getNumSlides());

        previousSlide.setEnabled(slideDeck.getCurrentIndex() != 0);
        nextSlide.setEnabled(slideDeck.getCurrentIndex() < slideDeck.getNumSlides() - 1);
    }

    private void deleteLogic() {
        deleteSlide.setEnabled(slideDeck.canDeleteSlide());
    }

    private void toolLogic() {
        if (drawingState == DrawingState.DRAW) {
            toolSelected.setText("Tool Selected: Drawing");
        } else if (drawingState == DrawingState.TEXT) {
            toolSelected.setText("Tool Selected: Text");
        } else {
            toolSelected.setText("Tool Selected: None");
        }
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

    private void savePDFDialog() {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(PresentationWindow.saveLocation);
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("PDF", "pdf");
        fileChooser.setFileFilter(fileFilter);
        fileChooser.setAcceptAllFileFilterUsed(false);


        int file = fileChooser.showSaveDialog(this);
        System.out.println("File Chooser output: "+file);
        if (file == 0) {
            File whereToSave = fileChooser.getSelectedFile();
            String savePath = whereToSave.getAbsolutePath();

            // Make sure it saves as a json file
            if(! savePath.substring(savePath.length() - 4).equals(".pdf")){
                whereToSave = new File (whereToSave.getAbsolutePath() + ".pdf");
            }

            System.out.println(whereToSave);
            slideDeck.savePDF(whereToSave);
        }
    }

    private void createSlider() {
        int fontMin = 5;
        int fontMax = 50;
        int fontInit = 15;

        JSlider fontSlider = new JSlider(fontMin, fontMax, fontInit);

        fontSlider.setMajorTickSpacing(5);
        fontSlider.setMinorTickSpacing(1);

        fontSlider.setPaintTicks(true);
        fontSlider.setPaintLabels(true);


        JLabel test = new JLabel("The quick brown fox...");
        test.setPreferredSize(new Dimension(500, 100));

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));

        sliderPanel.add(test);
        sliderPanel.add(fontSlider);


        fontSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int fontSize = source.getValue();
                    test.setFont(new Font("Serif", Font.PLAIN, fontSize));
                    }
                }
        });

        int answer = JOptionPane.showOptionDialog(this, sliderPanel,
                "Choose a font size!", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, null, null);

        if (JOptionPane.OK_OPTION == answer) {
            int val = fontSlider.getValue();
            slideDeck.setCurrentFont(new Font("Serif", Font.PLAIN, val));
            System.out.println("Does this work");
        }
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == newSlide) {
            System.out.println("newSlide!");
            slideDeck.addNewSlideHere();
            this.setNextAndPrevious();
            drawingPanel.updateSlide(slideDeck.getCurrentSlide());
            this.updateLocationText();
            this.deleteLogic();
            drawingState = DrawingState.NONE; // this is a bad practice
        } else if (event.getSource() == present) {
            System.out.println("present!");
            new Presentation(slideDeck.getSlideImages());
            drawingState = DrawingState.NONE; // this is a bad practice

        } else if (event.getSource() == saveAs) {
            this.saveAsDialog();
            drawingState = DrawingState.NONE; // this is a bad practice


        } else if (event.getSource() == save) {
            boolean operationComplete = slideDeck.save();
            drawingState = DrawingState.NONE; // this is a bad practice

            if (!operationComplete) {
                this.saveAsDialog();
            }
        } else if (event.getSource() == savePDF) {
            savePDFDialog();
            drawingState = DrawingState.NONE; // this is a bad practice

        } else if (event.getSource() == slideCountButton) {
            showOrHide(slideDeck.getCurrentSlide());
            drawingState = DrawingState.NONE; // this is a bad practice

        }  else if (event.getSource() == draw) {
            drawingState = DrawingState.DRAW;
            drawingPanel.updateState(drawingState);


        }  else if (event.getSource() == text) {
            drawingState = DrawingState.TEXT;
            drawingPanel.updateState(drawingState);
            System.out.println("text mode");

        }  else if (event.getSource() == deleteSlide) {
            if (slideDeck.canDeleteSlide()) {
                slideDeck.removeCurrentSlide();
                drawingPanel.updateSlide(slideDeck.getCurrentSlide());
            }
            this.setNextAndPrevious();
            this.deleteLogic();
            drawingState = DrawingState.NONE; // this is a bad practice


        }  else if (event.getSource() == fontSelect) {

            JFontChooser fontChooser = new JFontChooser();
            int result = fontChooser.showDialog(this);
            drawingState = DrawingState.NONE; // this is a bad practice


        } else if (event.getSource() == textSize) {
            System.out.println("fontSelect!");
            createSlider();
            drawingState = DrawingState.NONE; // this is a bad practice


        } else if (event.getSource() == backgroundColor) {
                Color newColor = JColorChooser.showDialog(this, "Choose Background Color", Color.WHITE);
                slideDeck.getCurrentSlide().setBackground(new ColorBackground(newColor));
                drawingPanel.updateSlide(slideDeck.getCurrentSlide());
                // drawingPanel.setBackgroundColor(newColor);
        } else if (event.getSource() == nextSlide) {
            slideDeck.nextSlide();
            this.setNextAndPrevious();
            drawingPanel.updateSlide(slideDeck.getCurrentSlide());
            drawingState = DrawingState.NONE; // this is a bad practice


        } else if (event.getSource() == previousSlide) {
            slideDeck.previousSlide();
            this.setNextAndPrevious();
            drawingPanel.updateSlide(slideDeck.getCurrentSlide());
            drawingState = DrawingState.NONE; // this is a bad practice
        }
        toolLogic();
    }
}
