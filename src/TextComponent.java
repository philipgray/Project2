
/**
 * A text component stores text in a unique format
 * 
 * @author Alex Wills
 * @date 3 March 2022
 */
public abstract class TextComponent extends SlideComponent {
    

    /**
     * Initialize text component with top left and bottom right coordinates 
     * @param topLeftX
     * @param topLeftY
     * @param bottomRightX
     * @param bottomRightY
     */
    public TextComponent(int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {
        super(topLeftX, topLeftY, bottomRightX, bottomRightY, ComponentType.Text);
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
     * TextComponents return their text-representation
     */
    @Override
    public String toString(){
        return super.toString() + "\n" + getText();
    }
}
