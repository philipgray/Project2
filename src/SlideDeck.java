import java.util.ArrayList;
import java.util.Iterator;

/**
 * SlideDecks are made up of many slides
 * 
 * @author Alex Wills
 * @date 4 March 2022
 */
public class SlideDeck implements Iterable<Slide> {
    
    private ArrayList<Slide> slides;
    
    // When a new slide is created, this defaultSlide is copied to the list.
    // This defaultSlide is determined by the template, and can be changed.
    private Slide defaultSlide;

    /**
     * Creates a slide deck with a specific defaultSlide
     * 
     * @param defaultSlide the slide to copy into new slides
     */
    public SlideDeck(Slide defaultSlide){
        slides = new ArrayList<Slide>();
        this.defaultSlide = defaultSlide;
        this.addNewSlide();
    }

    /**
     * Creates a slide deck without a specific defaultSlide.
     * The defaultSlide will be set to a blank slide.
     */
    public SlideDeck(){
        this(new Slide());
    }


    /**
     * Updates the default slide (used to change the template)
     * 
     * @param newDefault the Slide that defines what a newly added slide looks like
     */
    public void setDefaultSlide(Slide newDefault){
        this.defaultSlide = newDefault;
    }

    /**
     * Adds a new slide to the slide deck, a copy of the defaultSlide
     */
    public void addNewSlide(){
        this.slides.add( defaultSlide.cloneSlide() );
    }

    /**
     * Access a slide in this slide deck
     * @param index the index of the slide
     * @return the slide at the given index
     */
    public Slide getSlide(int index){
        return slides.get(index);
    }

    // Iterator interface
    @Override
    public Iterator<Slide> iterator() {
        return new SlideIterator(slides);
    }

    /**
     * Returns the string representation of every slide in the slide deck
     */
    @Override
    public String toString(){
        String representation = "Slide Deck: \n";
        for(Slide s : this){
            representation += "\n" + s;
        }

        return representation;
    }

}
