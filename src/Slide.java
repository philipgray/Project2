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

    /**
     * Constructs an empty slide for the slide deck.
     */
    public Slide(){
        components = new ArrayList<SlideComponent>();
    }


    /**
     * Adds a new component to this slide
     * 
     * @param newComponent the component to add to the slide
     */
    public void addComponent(SlideComponent newComponent){
        this.components.add(newComponent);
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

        return clone;
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