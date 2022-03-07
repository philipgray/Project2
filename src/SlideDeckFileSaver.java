import java.io.File;

/**
 * Encodes a slide deck object as a JSON file to store Slide Decks on the computer.
 * 
 * @author Alex Wills
 * @date 7 March 2022
 */
public class SlideDeckFileSaver {
    
    // These are the file locations that should not be overwritten,
    // because they are our templates
    File[] templateSaves = new File[] {
        new File("saved_slides/defaultSlide.json")
    };

    /**
     * Saves a SlideDeck as a JSON at the specified file path
     * 
     * @param toSave the slide deck to save
     * @param saveLocation the .json location to save the slide deck
     */
    public static void saveSlideDeck(SlideDeck toSave, File saveLocation){
        
    }
}
