package Alex;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;

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

    private File saveLocation;

    private Slide currentSlide;
    private int currentIndex;

    /**
     * Creates a slide deck with a specific defaultSlide
     * 
     * @param defaultSlide the slide to copy into new slides
     */
    public SlideDeck(Slide defaultSlide){
        slides = new ArrayList<Slide>();
        this.defaultSlide = defaultSlide;
        currentIndex = 0;
        currentSlide = null;
    }
    
    /**
     * Creates a slide deck without a specific defaultSlide.
     * The defaultSlide will be set to a blank slide.
     */
    public SlideDeck(){
        this(new Slide());
    }

    /**
     * Open a slide deck from a JSON file. If the file is in the SlideTemplates folder (really if "SlideTemplates" is anywhere
     * in the path), it will not remember where it came from (so you can't overwrite templates unintentionally). Otherwise,
     * it will remember where it came from so you can easily save.
     * 
     * @param fileLocation the file path of a JSON file with slide information.
     * @return the slide deck contained in the JSON file
     */
    public static SlideDeck openSlideFile(File fileLocation){
        SlideDeck deck = SlideDeckFileLoader.loadSlideDeck(fileLocation);

        // Do not allow user to save over the templates!
        if( fileLocation.getAbsolutePath().contains("SlideTemplates")){
            deck.saveLocation = null;
        } else {
            // If it's not a template, remember where it was opened so you can quickly save
            deck.saveLocation = fileLocation;
        }

        // Make sure there is a slide
        if(deck.getNumSlides() == 0 ){
            deck.addNewSlide();
            deck.getCurrentSlide();
        }

        return deck;
    }

    /**
     * Saves this file to the location stored in this slide deck. If the silde deck does not have a save location,
     * it will return false.
     * 
     * @return True if it saves the slide, false if there is nowhere specified to save the slide
     */
    public boolean save(){

        boolean success;

        // Only save if there is already a location to save it to
        if(saveLocation == null){
            success = false;

        } else {
            // If there is already a location, quickly save this deck
            success = true;
            SlideDeckFileSaver.saveSlideDeck(this, saveLocation);
        }

        return success;
    }

    /**
     * Save this deck in a specied location. This will also store the save location
     * so that in the future you can call .save() without specifying a location
     * 
     * @param saveLocation the File location to save the slide deck
     */
    public void saveAs(File saveLocation){
        this.saveLocation = saveLocation;
        save();
    }

    /**
     * Returns the number of slides in this slide deck
     * 
     * @return the number of slides in the deck
     */
    public int getNumSlides(){
        return this.slides.size();
    }

    /**
     * This is the current index the slide is on.
     * 
     * @return the current index
     */
    public int getCurrentIndex(){
        return this.currentIndex;
    }

    /**
     * Gets the currently selected slide
     * 
     * @return the currently selected slide. Null if there are no slides
     */
    public Slide getCurrentSlide(){

        // If no slide is selected, select the first one if possible.
        if(currentSlide == null && slides.size() > 0){
            currentSlide = slides.get(0);
            currentIndex = 0;
        }

        return currentSlide;
    }

    /**
     * Removes the current slide, unless it is the only slide in the slide deck
     * 
     * @return true if the current slide is deleted (there must be at least one other slide in the deck)
     */
    public boolean removeCurrentSlide(){
        boolean success = slides.size() > 1;

        // Only remove slide if there is another slide besides the current one
        if(success){
            slides.remove(currentIndex);
            
            // Go to the previous slide (as long as it isn't the first slide)
            previousSlide();
        }

        return success;
    }

    /**
     * Move to the next slide in the deck. If the current slide is the last slide, it stays at this slide
     * 
     * @return the next slide in the deck
     */
    public Slide nextSlide(){
        // Only go to the next slide if there is a slide after
        if(currentIndex < slides.size() - 1){
            currentIndex++;
            currentSlide = slides.get(currentIndex);
        }

        return currentSlide;
    }

    /**
     * Goes to the previous slide in the deck
     * 
     * @return the previous slide
     */
    public Slide previousSlide(){
        // Only go back if it's possible
        if(currentIndex > 0){
            currentIndex--;
            currentSlide = slides.get(currentIndex);
        }

        return currentSlide;
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
     * Returns the default slide 
     * 
     * @return the default slide
     */
    public Slide getDefaultSlide(){
        return this.defaultSlide;
    }

    /**
     * Adds a new empty slide to the slide deck, a copy of the defaultSlide
     * 
     * @see <code> addNewSlideHere() </code> for adding a slide after the currently selected slide
     */
    public void addNewSlide(){
        this.slides.add( defaultSlide.cloneSlide() );
    }

    /**
     * Adds a new empty slide based on the defaul slide, right after the currentSlide.
     * It then selects and returns this new slide.
     * 
     * @return the newly added slide
     */
    public Slide addNewSlideHere(){
        currentIndex++;
        slides.add( currentIndex, defaultSlide.cloneSlide() );
        currentSlide = slides.get(currentIndex);
        
        return currentSlide;
    }

    /**
     * Adds an existing slide to the slide deck
     * 
     * @param newSlide the slide to add to the deck
     */
    public void addSlide(Slide newSlide){
        this.slides.add(newSlide);
    }

    /**
     * Adds a new slide to a specific index in the deck
     * 
     * @param index the index to add the new slide
     * @param newSlide the slide to add to the deck
     */
    public void addSlide(int index, Slide newSlide){
        this.slides.add(index, newSlide);
    }

    /**
     * Access and select a slide in this slide deck
     * 
     * @param index the index of the slide
     * @return the slide at the given index
     */
    public Slide getSlide(int index){
        
        if(index >= 0 && index < slides.size()){
            currentIndex = index;
            currentSlide = slides.get(currentIndex);
        }

        return currentSlide;
    }


    /**
     * Removes a slide at a particular index
     * 
     * @param index the index of the slide to remove
     */
    public void removeSlide(int index){
        this.slides.remove(index);
    }

    /**
     * Removes a particular slide
     * 
     * @param toRemove the slide to remove
     */
    public void removeSlide(Slide toRemove){
        this.slides.remove(toRemove);
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
