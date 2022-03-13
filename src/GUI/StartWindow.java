package GUI;

import Alex.SlideDeck;
import Alex.BengaliFont;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


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
        title = new JLabel("Join Climbing Club! ক্লাইম্বিং ক্লাবে যোগ দিন! ", icon, JLabel.CENTER);
        // Format the text
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.BOTTOM);
        title.setFont(BengaliFont.getBengaliFont());

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

            // Customize file chooser for slide deck purpose
            File saveLocation = new File("saved_slides").getAbsoluteFile();
            fileChooser.setCurrentDirectory(saveLocation);
            
            // Limit to json files
            FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Slide Deck Files", "json");
            fileChooser.setFileFilter(fileFilter);
            fileChooser.setAcceptAllFileFilterUsed(false);
            int returnVal = fileChooser.showOpenDialog(StartWindow.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                mw.openPresentationWindow(SlideDeck.openSlideFile(file));
            } else {
                System.out.println("closed window");
            }
        }
    }
}
