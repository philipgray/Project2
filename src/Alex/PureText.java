package Alex;

/**
 * A text component that only contains text
 * 
 * @author Alex Wills
 * @date 3 March 2022
 */
public class PureText extends TextComponent {

    protected String text;

    /**
     * Creates a text object with starting text
     * @param startingText the initial text 
     */
    public PureText(String startingText){
        super(0, 0, 0, 0);
        text = startingText;
    }

    /**
     * Creates a text object with default text to start
     */
    public PureText(){
        this("Lorem Ipsum");
    }

    @Override
    public String getText(){
        return this.text;
    }

    @Override
    public void setText(String text){
        this.text = text;
    }

    @Override
    public TextComponent cloneComponent() {
        PureText clone = new PureText();
        clone.setContent(this.getContent());
        
        return clone;
    }

}
