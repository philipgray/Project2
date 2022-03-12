package Alex;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Iterator for slide components, to allow for easy iteration over slide components
 * 
 * @author Alex Wills
 * @date 4 March 2022
 */
public class SlideComponentIterator implements Iterator<SlideComponent>{

    private ArrayList<SlideComponent> components;
    private int currentIndex = 0;
    private int maxIndex;

    /**
     * Creates an iterator for SlideComponents
     * 
     * @param components list of slide components to iterate over
     */
    public SlideComponentIterator(ArrayList<SlideComponent> components){
        this.components = components;
        maxIndex = components.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return currentIndex <= maxIndex;
    }

    @Override
    public SlideComponent next() {
        SlideComponent next = components.get(currentIndex);
        currentIndex++;
        return next;
    }
    
}
