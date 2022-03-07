import org.json.simple.*;
import java.io.File;
import java.io.FileReader;

import org.json.simple.parser.JSONParser;

/**
 * Class to manage loading files. Loads a JSON file to create a SlideDeck object.
 * 
 * @author Alex Wills
 * @date 5 March 2022
 */
public class SlideDeckFileLoader {
    

    /**
     * Loads a slide deck from a JSON file
     * 
     * @param file the JSON file to load
     * @return a new slide deck with all of the slides and elements specified by the JSON file,
     * or an empty slide deck, if the file fails to laod.
     */
    public static SlideDeck loadSlideDeck(File file) {
        
        // New deck to load the slides into
        SlideDeck deck = new SlideDeck();

        // Try loading the file. If the file does not load, this funciton will return an empty slide
        try {

            JSONObject inFile = loadJSONFile(file);
            decodeSlideDeckJSON(inFile, deck);

        } catch (Exception e) {
            // If the JSON file does not load properly, the slide deck will be empty.
            e.printStackTrace();
        }

        return deck;
    }

    /**
     * Loads a .json file and parses it into a JSONObject
     * 
     * @param file the file to load (should be JSON)
     * @return JSONObject represeentation of the file
     * @throws Exception when the file cannot be found, accessed, or parsed into a JSON object. The program cannot load the slide.
     */
    public static JSONObject loadJSONFile(File inFile) throws Exception {
        
        // JSONObject to store file in
        JSONObject slideFileIn;

        // JSONParser to read the file
        JSONParser parser = new JSONParser();

        // FileReader to access the file
        FileReader fileReader;
        
        // It is possible that the file cannot be read. In that case, we cannot load the slide.
        // Try accessing the file
        fileReader = new FileReader(inFile);
        slideFileIn = (JSONObject) parser.parse(fileReader);
        
        return slideFileIn;
    }

    /**
     * Decodes a JSON file to convert it into a SlideDeck. Does this by adding all slides specified by the JSON
     * to the specified slide deck.
     * 
     * @param slideFileIn JSONObject containing Slide Information
     * @param slideDeckOut the SlideDeck object to modify according to the json
     */
    private static void decodeSlideDeckJSON(JSONObject slideFileIn, SlideDeck slideDeckOut){
        
        // This object will be used to parse JSON objects into slides
        JSONObject slide;

        // Set the default slide
        slide = (JSONObject) slideFileIn.get("defaultSlide");
        slideDeckOut.setDefaultSlide( decodeSlideJSON(slide) );

        // Add slides to the deck
        JSONArray slides = (JSONArray) slideFileIn.get("slides");
        for(Object jsonObj : slides){
            slide = (JSONObject) jsonObj;
            slideDeckOut.addSlide( decodeSlideJSON(slide));
        }
        
    }

    /**
     * Decodes a JSON representation of a slide into a Slide
     * 
     * @param slideJSON contains information about the slide.
     * It should contain a list of components, and each component should have a 
     * type, content, topX, topY, bottomX, and bottomY
     * 
     * @return Slide object specified by the JSON
     */
    private static Slide decodeSlideJSON(JSONObject slideJSON){
        Slide newSlide = new Slide();

        JSONArray components = (JSONArray) slideJSON.get("components");
        
        // I'm not a fan of how much type casting this library requires
        JSONObject component;
        for (Object jsonObj : components){
            component = (JSONObject) jsonObj;

            // Decode the JSON further to create the slide component
            newSlide.addComponent( decodeComponentJSON(component) );
        }

        return newSlide;
    }


    /**
     * Creates a slide component based on the JSON
     * 
     * @param componentJSON contatins information about the component's type, coordinates,
     * and content
     * @return a SlideComponent specified by the JSON
     */
    private static SlideComponent decodeComponentJSON(JSONObject componentJSON){
        SlideComponent newComponent;

        // Get information common to all components
        String type = (String) componentJSON.get("type");
        int topX = Math.toIntExact((long) componentJSON.get("topX"));
        int topY = Math.toIntExact((long) componentJSON.get("topY"));
        
        int bottomX = Math.toIntExact((long) componentJSON.get("bottomX"));
        int bottomY = Math.toIntExact((long) componentJSON.get("bottomY"));
        Object content = componentJSON.get("content");

        // Create component based on what the type is
        // NOTE: This is a good place for the command pattern
        // Encapsulating methods so that when you add new component types, you can easily add the decoding logic for the JSON
        if(type.equals("text")){
            newComponent = new PureText((String) content);


        } else {
            newComponent = null;
        }

        // Set component coordinates
        newComponent.setTopLeftCoord(topX, topY);
        newComponent.setBottomRightCoord(bottomX, bottomY);
        return newComponent;
    }


    
}
