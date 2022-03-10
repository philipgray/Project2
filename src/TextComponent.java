
/**
 * A text component stores text in a unique format
 * 
 * @author Alex Wills
 * @date 3 March 2022
 */
public abstract class TextComponent extends SlideComponent {

    private int fontSize;

    /**
     * Initialize text component with top left and bottom right coordinates 
     * @param topLeftX
     * @param topLeftY
     * @param bottomRightX
     * @param bottomRightY
     */
    public TextComponent(int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {
        super(topLeftX, topLeftY, bottomRightX, bottomRightY, ComponentType.Text);
        fontSize = 12;
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
     */
    public void setFontSize(int fontSize){
        this.fontSize = fontSize;
    }

    /**
     * Gets the font size of this text component
     * @return
     */
    public int getFontSize(){
        return this.fontSize;
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
            System.out.println("Custom font size");
            // Get the font size
            int fontSizeIndex = content.indexOf("/fs=") + 4;    
            int fontSizeEndIndex = content.indexOf("/", fontSizeIndex);
            String fontSizeStr = content.substring(fontSizeIndex, fontSizeEndIndex);
            this.fontSize = Integer.parseInt(fontSizeStr);
            
            // Trim the font size tag from the content string
            content = content.substring(fontSizeEndIndex + 1);

        } else {
            this.fontSize = 12;
        }

        setText(content);       
    }

    @Override
    public String getContent() {
        return "/fs=" + this.fontSize + "/" + getText();
    }
}
