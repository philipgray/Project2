import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Font;
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

    private Font slideFont;

    private File saveLocation;

    /**
     * Creates a slide deck with a specific defaultSlide
     * 
     * @param defaultSlide the slide to copy into new slides
     */
    public SlideDeck(Slide defaultSlide){
        slides = new ArrayList<Slide>();
        this.defaultSlide = defaultSlide;
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


    /*
    * Font, Fontsize, 
    */



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
     */
    public void addNewSlide(){
        this.slides.add( defaultSlide.cloneSlide() );
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
     * Access a slide in this slide deck
     * 
     * @param index the index of the slide
     * @return the slide at the given index
     */
    public Slide getSlide(int index){
        return slides.get(index);
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
    
    /**
     * Gets the font used in this slide deck
     * 
     * @return the slide deck font
     */
    public Font getFont(){
        return this.slideFont;
    }

    /**
     * Set the font used in this slide deck
     * 
     * @param newFont the new font to set
     */
    public void setFont(Font newFont){
        this.slideFont = newFont;
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
