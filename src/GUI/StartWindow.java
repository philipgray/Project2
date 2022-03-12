package GUI;

import Alex.SlideDeckFileLoader;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static Alex.SlideDeckFileLoader.loadSlideDeck;

public class StartWindow extends JPanel implements ActionListener {
    protected JButton open, load;
    protected JLabel title;
    protected MainWindow mw;


    public StartWindow(MainWindow mw) {
        this.mw = mw;
        BorderLayout layout = new BorderLayout(25, 25);
        setLayout(layout);
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Title Button ------------------------------------------------------

        // Add in image
        ImageIcon icon = new ImageIcon("images/jcc.png");
        title = new JLabel("Join Climbing Club!\n<Insert Join Climbing Club here in Bengali>", icon, JLabel.CENTER);
        // Format the text
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.BOTTOM);

        add(title, BorderLayout.PAGE_START);

        // Open Button ----------------------------------------------------------
        open = new JButton("New Presentation");
        open.setVerticalTextPosition(AbstractButton.CENTER);
        open.setHorizontalTextPosition(AbstractButton.LEADING);
        open.setToolTipText("নতুন উপস্থাপনা");
        open.setPreferredSize(new Dimension(150, 200));
        open.setFocusPainted(false);
        open.setActionCommand("open");
        open.addActionListener(this);

        add(open, BorderLayout.LINE_START);

        // Load Button --------------------------------------------------------------------------------
        load = new JButton("Load Presentation");
        load.setVerticalTextPosition(AbstractButton.CENTER);
        load.setHorizontalTextPosition(AbstractButton.LEADING);
        load.setPreferredSize(new Dimension(150, 200));
        load.setToolTipText("উপস্থাপনা খুলুন");
        load.setFocusPainted(false);
        load.setActionCommand("load");
        load.addActionListener(this);

        add(load, BorderLayout.LINE_END);
    }

    /**
     * https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
     * @param event
     */
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("open")) {
            System.out.println("clicked open!");
            mw.openTemplateWindow();

        } else if (event.getSource() == load) {
            System.out.println("clicked load");
            final JFileChooser fileChooser = new JFileChooser();
            int returnVal = fileChooser.showOpenDialog(StartWindow.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                mw.openPresentationWindow(loadSlideDeck(file));
            } else {
                System.out.println("closed window");
            }
        }
    }
}
