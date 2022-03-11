import java.util.ArrayList;
import java.util.Iterator;

/**
 * Iterator to iterate over Slides, so that you can use the SlideDeck in a for-each loop
 * 
 * @author Alex Wills
 * @date 4 March 2022
 */
public class SlideIterator implements Iterator<Slide> {

    private ArrayList<Slide> slides;
    private int currentIndex = 0;
    private int maxIndex;

    /**
     * Creates an iterator to iterate over slides
     * 
     * @param slides the slides to iterate over
     */
    public SlideIterator(ArrayList<Slide> slides){
        this.slides = slides;
        this.maxIndex = slides.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return currentIndex <= maxIndex;
    }

    @Override
    public Slide next() {
        Slide next = slides.get(currentIndex);
        currentIndex++;
        return next;
    }
    
}
