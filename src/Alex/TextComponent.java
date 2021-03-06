package Alex;
import java.awt.Font;
import java.io.File;

/**
 * A text component stores text in a unique format
 * 
 * @author Alex Wills
 * @date 3 March 2022
 */
public abstract class TextComponent extends SlideComponent {

    private int fontSize;
    private Font font;
    private File fontFile;
    private static Font defaultFont = Font.getFont( Font.DIALOG );

    /**
     * Initialize text component with top left and bottom right coordinates 
     * @param topLeftX
     * @param topLeftY
     * @param bottomRightX
     * @param bottomRightY
     */
    public TextComponent(int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {
        super(topLeftX, topLeftY, bottomRightX, bottomRightY, ComponentType.Text);
        fontSize = 16;
        this.font = TextComponent.defaultFont;
    }

    /**
     * Returns a string containing the TextComponent's text
     * @return the text representation of the string
     */
    public abstract String getText();

    /**
     * Changes the text in the TextComponent
     * @param text the new text for the text box
     */
    public abstract void setText(String text);

    /**
     * Sets the font size of this text
     * @param fontSize the font size (in pts) of this text
     * @return true if the font is too small! Use this return to display a warning
     */
    public boolean setFontSize(int fontSize){
        this.fontSize = fontSize;
        boolean tooSmall = false;

        // Update font size
        if(this.font != null){
            this.font = this.font.deriveFont((float)fontSize);
        }

        if(fontSize < 15){
            tooSmall = true;
        }

        return tooSmall;
    }

    /**
     * Gets the font size of this text component
     * @return
     */
    public int getFontSize(){
        return this.fontSize;
    }

    /**
     * Returns the font for this text
     * @return the text font
     */
    public Font getFont(){
       
        // Default font
        if(font == null){
            font = BengaliFont.getBengaliFont().deriveFont(fontSize);
        }
        
        return this.font;
    }

    /**
     * Changes the font of all the text
     * 
     * @param font the font of this text object
     * @param fontFile the location of the font file (so that the font can be saved with the component)
     */
    public void setFont(Font font, File fontFile){
        this.font = font.deriveFont((float)this.fontSize);
        this.fontFile = fontFile;
    }

    public void setFont(Font font){
        this.font = font;
    }


    /**
     * Returns the file of the font (used for saving the text component)
     * 
     * @return the file where this component's font is
     */
    public File getFontFile(){
        return this.fontFile;
    }

    /**
     * TextComponents return their text-representation
     */
    @Override
    public String toString(){
        return super.toString() + "\n" + getText() + "(font size: " + fontSize + ")";
    }

    /**
     * For Text components, specify the font size at the beginning of the string with /fs=fontSize/
     * <p>
     * e.g. /fs=12/ for a font size of 12.
     * If this component is not formatted with the font size, it will default to 12
     * 
     */
    @Override
    public void setContent(String content) {
        

        // If a font size is specified, set the font size
        if(content.contains("/fs=")){
            // Get the font size
            int fontSizeIndex = content.indexOf("/fs=") + 4;    
            int fontSizeEndIndex = content.indexOf("/", fontSizeIndex);
            String fontSizeStr = content.substring(fontSizeIndex, fontSizeEndIndex);
            this.setFontSize( Integer.parseInt(fontSizeStr) );
            
            // Trim the font size tag from the content string
            content = content.substring(fontSizeEndIndex + 1);

        } else {
            this.setFontSize(15);
        }

        setText(content);       
    }

    @Override
    public String getContent() {
        return "/fs=" + this.fontSize + "/" + getText();
    }
}
