import java.util.ArrayList;
import java.util.Iterator;

/**
 * A slide for a presentation. Contains multiple slide components
 * 
 * @author Alex Wills
 * @date 2 March 2022
 */
public class Slide implements Iterable<SlideComponent>{

    private ArrayList<SlideComponent> components;

    /**
     * Constructs an empty slide for the slide deck.
     */
    public Slide(){
        components = new ArrayList<SlideComponent>();
    }


    public void addComponent(SlideComponent newComponent){
        this.components.add(newComponent);

    }

    @Override
    public Iterator<SlideComponent> iterator() {
        return new SlideComponentIterator(components);
    }
}