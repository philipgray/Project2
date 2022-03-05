
/**
 * A text component stores text in a unique format
 * 
 * @author Alex Wills
 * @date 3 March 2022
 */
public abstract class TextComponent {
    


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
}
