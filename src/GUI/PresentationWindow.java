package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PresentationWindow extends JPanel implements ActionListener {
    MainWindow mw;

    JButton newSlide, fontSelect, fontSize, createBullet, createSubBullet, insertLink, present;
    DrawingPanel drawingPanel;

    public PresentationWindow(MainWindow mw) {
        this.mw = mw;
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        /*
         * Once again, I am doing a bad thing.
         * https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
         */
        GridBagConstraints constraints = new GridBagConstraints();

        // New Slide Button --------------------------------------------------------------------
        newSlide = new JButton("New Slide");
        newSlide.addActionListener(this);
        newSlide.setToolTipText("Placeholder Text");

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 3;
        constraints.gridheight = 3;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipady = 60;
        constraints.ipadx = 50;

        add(newSlide, constraints);
        // Present Button --------------------------------------------------------------------------
        present = new JButton("Present");
        present.addActionListener(this);
        present.setToolTipText("Placeholder Text");

        constraints.gridx = 3;
        constraints.insets = new Insets(0, 10, 0, 10);

        add(present, constraints);

        // Font Select Button ----------------------------------------------------------------------
        fontSelect = new JButton("Font");
        fontSelect.addActionListener(this);
        fontSelect.setToolTipText("Placeholder Text");

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 0, 10, 10);

        constraints.gridx = 6;
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

        constraints.gridx = 7;
        constraints.gridy = 0;

        add(createBullet, constraints);

        // Create Sub-Bullet Button ------------------------------------------------------
        createSubBullet = new JButton("Create Sub-Bullet");
        createSubBullet.addActionListener(this);
        createSubBullet.setToolTipText("Placeholder Text");

        constraints.gridx = 7;
        constraints.gridy = 1;

        add(createSubBullet, constraints);

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
        } else if (event.getSource() == fontSelect) {
            System.out.println("fontSelect!");
        }
    }
}
