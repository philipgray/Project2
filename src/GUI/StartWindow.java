package GUI;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class StartWindow extends JPanel implements ActionListener {
    protected JButton open, load;


    public StartWindow() {

        open = new JButton("New Presentation");
        open.setVerticalTextPosition(AbstractButton.CENTER);
        open.setHorizontalTextPosition(AbstractButton.LEADING);
        open.setToolTipText("নতুন উপস্থাপনা");
        open.setPreferredSize(new Dimension(150, 200));
        open.setFocusPainted(false);
        open.setActionCommand("open");
        open.addActionListener(this);

        load = new JButton("Load Presentation");
        load.setVerticalTextPosition(AbstractButton.CENTER);
        load.setHorizontalTextPosition(AbstractButton.LEADING);
        load.setPreferredSize(new Dimension(150, 200));
        load.setToolTipText("উপস্থাপনা খুলুন");
        load.setFocusPainted(false);
        load.setActionCommand("load");
        load.addActionListener(this);


        add(open);
        add(load);
    }

    /**
     * https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
     * @param event
     */
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("open")) {
            System.out.println("clicked open");

        } else if (event.getSource() == load) {
            System.out.println("clicked load");
            final JFileChooser fileChooser = new JFileChooser();
            int returnVal = fileChooser.showOpenDialog(StartWindow.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("hi");
                File file = fileChooser.getSelectedFile();
                open.removeActionListener(this);
                load.removeActionListener(this);
            } else {
                System.out.println("closed window");
            }
        }
    }
}
