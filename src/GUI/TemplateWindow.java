/**
 * TODO: Bengali Text
 *
 */
package GUI;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        templateOne = new JButton("Template 1", icon);
        templateOne.setVerticalTextPosition(AbstractButton.BOTTOM);
        templateOne.setHorizontalTextPosition(AbstractButton.CENTER);
        templateOne.addActionListener(this);
        templateOne.setToolTipText("টেমপ্লেট এক");

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(templateOne, constraints);

        // Template 2 Button -----------------------------------------------------------------------
        templateTwo = new JButton("Template 2", icon);
        templateTwo.setVerticalTextPosition(AbstractButton.BOTTOM);
        templateTwo.setHorizontalTextPosition(AbstractButton.CENTER);
        templateTwo.addActionListener(this);
        templateTwo.setToolTipText("টেমপ্লেট দুই");

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 1;
        add(templateTwo, constraints);

        // Template 3 Button -----------------------------------------------------------------------
        templateThree = new JButton("Template 3", icon);
        templateThree.setVerticalTextPosition(AbstractButton.BOTTOM);
        templateThree.setHorizontalTextPosition(AbstractButton.CENTER);
        templateThree.addActionListener(this);
        templateThree.setToolTipText("টেমপ্লেট তিন");

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(templateThree, constraints);

        // Template 4 Button -----------------------------------------------------------------------
        templateFour = new JButton("Template 4", icon);
        templateFour.setVerticalTextPosition(AbstractButton.BOTTOM);
        templateFour.setHorizontalTextPosition(AbstractButton.CENTER);
        templateFour.addActionListener(this);
        templateFour.setToolTipText("টেমপ্লেট চার");

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(templateFour, constraints);

        // Template 5 Button -----------------------------------------------------------------------
        templateFive = new JButton("Template 5", icon);
        templateFive.setVerticalTextPosition(AbstractButton.BOTTOM);
        templateFive.setHorizontalTextPosition(AbstractButton.CENTER);
        templateFive.addActionListener(this);
        templateFive.setToolTipText("টটেমপ্লেট পাঁচ");

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 2;
        add(templateFive, constraints);

    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == blankPresentation) {
            System.out.println("blankPresentation!");
            mw.openPresentationWindow();
        } else if (event.getSource() == back) {
            System.out.println("back!");
            mw.openStartWindow();
        } else if (event.getSource() == templateOne) {
            System.out.println("templateOne!");
        } else if (event.getSource() == templateTwo) {
            System.out.println("templateTwo!");
        } else if (event.getSource() == templateThree) {
            System.out.println("templateThree!");
        } else if (event.getSource() == templateFour) {
            System.out.println("templateFour!");
        } else if (event.getSource() == templateFive) {
            System.out.println("templateFive!");
        }
    }
}
