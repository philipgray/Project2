package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;


/**
 * Taken from:
 * https://stackoverflow.com/questions/33949537/drawing-text-from-keyboard-input-on-a-canvas?rq=1
 *
 * With slight modifications. Used for inline text writing.
 */
public class InvisibleTextField extends JTextField
        implements ActionListener, FocusListener, MouseListener, DocumentListener
{
    DrawingPanel drawingPanel;
    public InvisibleTextField(DrawingPanel drawingPanel)
    {
        this.drawingPanel = drawingPanel;
        setOpaque( false );
        setColumns( 1 );
//      setBorder( null );
        setSize( getPreferredSize() );
        setColumns( 0 );
        addActionListener( this );
        addFocusListener( this );
        addMouseListener( this );
        getDocument().addDocumentListener( this );
    }

//  Implement ActionListener

    public void actionPerformed(ActionEvent e)
    {
        System.out.println("action Performed");
        setEditable( false );
        drawingPanel.removeText(this);
    }

//  Implement FocusListener

    public void focusLost(FocusEvent e)
    {
        System.out.println("focus lost");
        setEditable( false );
        drawingPanel.removeText(this);
    }

    public void focusGained(FocusEvent e) {}

//  Implement MouseListener

    public void mouseClicked( MouseEvent e )
    {
        if (e.getClickCount() == 2)
            setEditable( true );
    }

    public void mouseEntered( MouseEvent e ) {}

    public void mouseExited( MouseEvent e ) {}

    public void mousePressed( MouseEvent e ) {}

    public void mouseReleased( MouseEvent e ) {}

//  Implement DocumentListener

    public void insertUpdate(DocumentEvent e)
    {
        updateSize();
    }

    public void removeUpdate(DocumentEvent e)
    {
        updateSize();
    }

    public void changedUpdate(DocumentEvent e) {}

    private void updateSize()
    {
        setSize( getPreferredSize() );
    }
}
