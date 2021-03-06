package Alex;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;

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

    private File currentFontFile;
    private Font currentFont;
    private Font[] availableFonts;
    private File[] availableFontFiles;
    private String[] availableFontNames;


    /**
     * Creates a slide deck with a specific defaultSlide
     * 
     * <p>
     * Use this constructor when loading a slide
     * 
     * @param defaultSlide the slide to copy into new slides
     */
    public SlideDeck(Slide defaultSlide){
        this.slides = new ArrayList<>();
        this.defaultSlide = defaultSlide;
        this.currentSlide = null;
        this.currentIndex = 0;
        this.saveLocation = null;

        // Load in the existing fonts
        this.loadFonts();
        chooseFont(0);
    }
    
    /**
     * Creates a slide deck without a specific defaultSlide.
     * The defaultSlide will be set to a blank slide. This method will also go ahead and add a new slide.
     * <p>
     * Use this constructor to start a slide from scratch
     */
    public SlideDeck(){
        this(new Slide());
        this.addNewSlide();
        this.getCurrentSlide();
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

    public boolean saveLocationExists() {
        return this.saveLocation != null;
    }
    

    /**
     * Saves this slide deck as a pdf
     * 
     * @param saveLocation file location ending in .pdf to save the file
     */
    public void savePDF(File saveLocation){
        
        BufferedImage[] slideImgs = getSlideImages();

        // Save to pdf
        PDFSaver.savePDF(slideImgs, saveLocation);
    }

    /**
     * Gets an array of all the slide images
     * 
     * @return Array of slide images
     */
    public BufferedImage[] getSlideImages(){
        BufferedImage[] slideImgs = new BufferedImage[ this.getNumSlides() ];

        // Create the array of images
        for(int i = 0; i < this.getNumSlides(); i++){
            slideImgs[i] = slides.get(i).getSlideImage(i);  // Here we access the array directly to avoid messing with currentIndex and currentSlide
        }

        return slideImgs;
    }

    /**
     * Provides a list of all available font names.
     * The index for the font names is the same as the index for the font objects and font files
     * 
     * @return String[] containing the font names.
     */
    public String[] getAvailableFontNames(){
        return this.availableFontNames;
    }
    
    /**
     * Change the currently selected font. Font indices correspond to those
     * found in <code> this.getAvailableFontNames() </code>
     *
     * @param fontIndex the index of the font you want
     */
    public void chooseFont(int fontIndex){
        // Ensure the index is not out of bounds
        if(fontIndex > 0 && fontIndex < this.availableFonts.length){
            this.currentFont = availableFonts[fontIndex];
            this.currentFontFile = availableFontFiles[fontIndex];
        }
    }

    /**
     * setter method for fonts!
     * @param font
     */
    public void setCurrentFont(Font font) {
        this.currentFont = font;
    }

    /**
     * WHY WAS THIS NOT A METHOD ALREADY???
     *
     * Returns the SlideDeck's currentFont.
     * @return Font the currentFont in the SlideDeck.
     */
    public Font getCurrentFont() {
        return this.currentFont;
    }

    /**
     * Returns all loaded fonts
     * 
     * @return array list containing available fonts
     */
    public Font[] getAllFonts(){
        return availableFonts;
    }


    /**
     * Return the font that is currently selected
     * 
     * @return the current font
     */
    public Font getFont(){
        return currentFont;
    }

    /**
     * Return the file of the font currently selected
     * 
     * @return the current font's file
     */
    public File getFontFile(){
        return currentFontFile;
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
    public void removeCurrentSlide(){

        // Only remove slide if there is another slide besides the current one
        if( this.canDeleteSlide() ){
            slides.remove(currentIndex);
            
            // Go to the previous slide (as long as it isn't the first slide)
            previousSlide();
        }
    }

    /**
     * Checks if there are more than 1 slides in the deck, meaning we can delete a slide.
     * 
     * @return True if .removeCurrentSlide will success (i.e. there are more than 1 slides)
     */
    public boolean canDeleteSlide(){
        return slides.size() > 1;
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

        } else {
            // Otherwise make sure we are at index 0
            currentIndex = 0;
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
        currentIndex = this.slides.size() - 1;
        currentSlide = slides.get(currentIndex);
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
    

    /**
     * Helper method to load in all of the fonts and font files into the array
     */
    private void loadFonts(){

        // Load all the font files
        availableFontFiles = new File[] {
            new File("fonts/akaashnormal.ttf").getAbsoluteFile(),
            new File("fonts/Kalpurush-Regular.ttf").getAbsoluteFile(),
            new File("fonts/Mukti-Book.ttf").getAbsoluteFile(),
            new File("fonts/Nikosh-Regular_jvTyYV3.ttf").getAbsoluteFile(),
            new File("fonts/Nirmala.ttf").getAbsoluteFile()
        };

        // Unpack all of these fonts into Font objects
        availableFonts = new Font[ availableFontFiles.length ];
        for(int i = 0; i < availableFontFiles.length; i++){
            
            try {
                availableFonts[i] = Font.createFont(Font.TRUETYPE_FONT, availableFontFiles[i]);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }

        }

        // Store the names of the fonts
        availableFontNames = new String[] {
            "Akaash",
            "Kalpurush",
            "Mukti-Book",
            "Nikosh",
            "Nirmala"
        };
        
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
