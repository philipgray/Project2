/**
 * TODO: Bengali Text
 *
 */
package GUI;

import Alex.SlideDeck;
import Alex.BengaliFont;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class TemplateWindow extends JPanel implements ActionListener {

    // Should we just make a list of JComponents...?
    MainWindow mw;
    JLabel instructions;
    JButton back, blankPresentation, templateOne, templateTwo, templateThree, templateFour, templateFive;

    public TemplateWindow(MainWindow mw) {
        this.mw = mw;

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        /*
         * It's apparently a bad practice to reuse this, so in the future don't do that.
         * https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
         */
        GridBagConstraints constraints = new GridBagConstraints();


        // Instructions Button --------------------------------------------------------------------
        instructions = new JLabel("Please select one of the following options:");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(instructions, constraints);


        // Back Button --------------------------------------------------------------------------------
        back = new JButton("Back");
        back.setFocusPainted(false);
        back.addActionListener(this);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 1;
        constraints.gridx = 2;
        constraints.gridy = 0;
        add(back, constraints);

        // New Presentation Button -----------------------------------------------------------------
        ImageIcon icon = new ImageIcon("images/placeholder.png");
        blankPresentation = new JButton("Blank Presentation", icon);
        blankPresentation.setVerticalTextPosition(AbstractButton.BOTTOM);
        blankPresentation.setHorizontalTextPosition(AbstractButton.CENTER);
        blankPresentation.addActionListener(this);
        blankPresentation.setToolTipText("ফাঁকা উপস্থাপনা");

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5); // padding
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(blankPresentation, constraints);

        // Template 1 Button -----------------------------------------------------------------------
        templateOne = new JButton("Stars Template", new ImageIcon("images/starsTemplate.png"));
        templateOne.setVerticalTextPosition(AbstractButton.BOTTOM);
        templateOne.setHorizontalTextPosition(AbstractButton.CENTER);
        templateOne.addActionListener(this);
        templateOne.setToolTipText("তারা টেমপ্লেট");

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(templateOne, constraints);

        // Template 2 Button -----------------------------------------------------------------------
        templateTwo = new JButton("Venn Diagrams Template", new ImageIcon("images/vennTemplate.png"));
        templateTwo.setVerticalTextPosition(AbstractButton.BOTTOM);
        templateTwo.setHorizontalTextPosition(AbstractButton.CENTER);
        templateTwo.addActionListener(this);
        templateTwo.setToolTipText("ভেন টেমপ্লেট");

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 1;
        add(templateTwo, constraints);

        // Template 3 Button -----------------------------------------------------------------------
        templateThree = new JButton("Naval Template", new ImageIcon("images/navalTemplate.png"));
        templateThree.setVerticalTextPosition(AbstractButton.BOTTOM);
        templateThree.setHorizontalTextPosition(AbstractButton.CENTER);
        templateThree.addActionListener(this);
        templateThree.setToolTipText("নৌ টেমপ্লেট");

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(templateThree, constraints);

        // Template 4 Button -----------------------------------------------------------------------
        templateFour = new JButton("Science Template", new ImageIcon("images/scienceTemplate.png"));
        templateFour.setVerticalTextPosition(AbstractButton.BOTTOM);
        templateFour.setHorizontalTextPosition(AbstractButton.CENTER);
        templateFour.addActionListener(this);
        templateFour.setToolTipText("বিজ্ঞান টেমপ্লেট");

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(templateFour, constraints);

        // Template 5 Button -----------------------------------------------------------------------
        templateFive = new JButton("Minimal Template", new ImageIcon("images/minimalTemplate.png"));
        templateFive.setVerticalTextPosition(AbstractButton.BOTTOM);
        templateFive.setHorizontalTextPosition(AbstractButton.CENTER);
        templateFive.addActionListener(this);
        templateFive.setToolTipText("ন্যূনতম টেমপ্লেট");

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 2;
        add(templateFive, constraints);

    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == blankPresentation) {
            System.out.println("blankPresentation!");
            mw.openPresentationWindow(new SlideDeck());
        } else if (event.getSource() == back) {
            System.out.println("back!");
            mw.openStartWindow();
        } else if (event.getSource() == templateOne) {
            System.out.println("templateOne!");
            mw.openPresentationWindow( SlideDeck.openSlideFile( new File("saved_slides/SlideTemplates/Stars.json").getAbsoluteFile()));
        } else if (event.getSource() == templateTwo) {
            System.out.println("templateTwo!");
            mw.openPresentationWindow(SlideDeck.openSlideFile(new File("saved_slides/SlideTemplates/V3nn.json")));
        } else if (event.getSource() == templateThree) {
            System.out.println("templateThree!");
            mw.openPresentationWindow(SlideDeck.openSlideFile(new File("saved_slides/SlideTemplates/Naval.json")));
        } else if (event.getSource() == templateFour) {
            System.out.println("templateFour!");
            mw.openPresentationWindow(SlideDeck.openSlideFile(new File("saved_slides/SlideTemplates/Science.json")));
        } else if (event.getSource() == templateFive) {
            System.out.println("templateFive!");
            mw.openPresentationWindow(SlideDeck.openSlideFile(new File("saved_slides/SlideTemplates/defaultSlide.json")));
        }
    }
}
