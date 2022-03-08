import java.util.ArrayList;
import java.util.Iterator;

/**
 * A slide for a presentation. Contains multiple slide components
 * 
 * @author Alex Wills
 * @date 2 March 2022
 */
public class Slide implements Iterable<SlideComponent> {

    private ArrayList<SlideComponent> components;
    private ArrayList<TextComponent> textComponents;
    private Background background;

    /**
     * Constructs an empty slide for the slide deck.
     */
    public Slide(){
        components = new ArrayList<SlideComponent>();
        textComponents = new ArrayList<TextComponent>();
    }


    /**
     * Adds a new component to this slide
     * 
     * @param newComponent the component to add to the slide
     */
    public void addComponent(SlideComponent newComponent){
        this.components.add(newComponent);

        // Add component to a list based on its type
        if (newComponent.getType() == ComponentType.Text){
            this.textComponents.add( (TextComponent) newComponent);
        }
    }

    /**
     * Returns a specific element in the slide
     * 
     * @param index the index of the slide element
     * @return the slide component at the specified index
     */
    public SlideComponent getComponent(int index){
        return this.components.get(index);
    }

    /**
     * Sets the background for this slide
     * 
     * @param bg the new background for the slide
     */
    public void setBackground(Background bg){
        this.background = bg;
    }

    /**
     * Get the background of this slide
     * 
     * @return the background of this slide
     */
    public Background getBackground(){
        return this.background;
    }


    /**
     * Clones this slide and returns a copy.
     * The slide is copied by value, not reference, so this slide and the copy can be edited separately
     * 
     * @return a copy of this slide that can be modified without modifying this slide
     */
    public Slide cloneSlide(){
        Slide clone = new Slide();

        // Add a copy of every component
        for(SlideComponent component : this){
            clone.addComponent( component.cloneComponent() );
        }

        // Copy the background
        clone.setBackground((Background) this.background.cloneComponent());

        return clone;
    }

    /**
     * Returns a list of this slide's text components.
     * Example use: to draw all of the text to the screen, you can use this method and iterate over the array list.
     * 
     * @return an ArrayList containing all of this slide's text components
     */
    public ArrayList<TextComponent> getTextComponents(){
        return textComponents;
    }

    // Iterable interface
    @Override
    public Iterator<SlideComponent> iterator() {
        return new SlideComponentIterator(components);
    }

    /**
     * Print out the slide's information and all of its components
     */
    @Override
    public String toString(){
        String representation = "Slide: \n\tComponents:";
        for(SlideComponent s : this){
            representation += "\n\t" + s;
        }

        return representation;
    }
}