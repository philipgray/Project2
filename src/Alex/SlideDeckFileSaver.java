package Alex;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import org.json.simple.*;

/**
 * Encodes a slide deck object as a JSON file to store Slide Decks on the computer.
 * 
 * @author Alex Wills
 * @date 7 March 2022
 */
public class SlideDeckFileSaver {
    

    //NOTE TODO: store which slide you left off on

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
        
        // Open a buffered writer at the file location

        BufferedWriter fileOut;
        
        try{

            fileOut = new BufferedWriter(new FileWriter(saveLocation));

            JSONObject jsonfile = encodeSlideDeck(toSave);
            fileOut.write( jsonfile.toJSONString() );

            fileOut.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Converts a SlideDeck object into a JSONObject
     * 
     * @param toSave the slide deck to save
     * @return the JSONObject to write to a .json file
     */
    private static JSONObject encodeSlideDeck(SlideDeck toSave){
        JSONObject jsonDeck = new JSONObject();

        // Save the deafult slide
        jsonDeck.put("defaultSlide", encodeSlide(toSave.getDefaultSlide()));

        // Save all the slides
        JSONArray slides = new JSONArray();
        for(Slide s : toSave){
            slides.add( encodeSlide(s) );
        }

        jsonDeck.put("slides", slides);

        // Store the slide you left off on
        jsonDeck.put("selectedSlide", toSave.getCurrentIndex());

        return jsonDeck;
    }

    /**
     * Encodes a slide into a JSONObject
     * 
     * @param slide the Slide to save as a JSON
     * @return the slide as a JSONObject
     */
    private static JSONObject encodeSlide(Slide slide){

        JSONObject jsonSlide = new JSONObject();
        JSONArray jsonComponents = new JSONArray();

        String type;

        // Encode all of the elements and add them to the array
        for(SlideComponent comp : slide){
            type = comp.getType().toString();
            jsonComponents.add( encodeSingleComponent(comp, type));
        }

        // Add components to the slide object
        jsonSlide.put("components", jsonComponents);

        // Add background
        type = slide.getBackground().getType().toString();
        jsonSlide.put("background", encodeSingleComponent(slide.getBackground(), type));

        return jsonSlide;
    }

    /**
     * Encodes a list of components into a JSON array
     * 
     * @param components the list of components to encode
     * @param type the type to encode these components as
     * @return a JSONArray containing the components
     */
    private static ArrayList<JSONObject> encodeComponents(ArrayList<SlideComponent> components, String type){
        
        ArrayList<JSONObject> jsonComponents = new ArrayList<JSONObject>( components.size() ); 

        // Add all of the components in JSON form to the list
        for(SlideComponent component : components){
            jsonComponents.add( encodeSingleComponent(component, type) );
        }

        // Return the list
        return jsonComponents;
    }


    /**
     * Converts a SlideComponent into a JSON object
     * @param component the component to convert
     * @param type the type of object the component is
     * @return JSONObject representing the component
     */
    private static JSONObject encodeSingleComponent(SlideComponent component, String type){
        JSONObject jsonComponent = new JSONObject();

        jsonComponent.put("type", type);
        jsonComponent.put("content", component.getContent());
        jsonComponent.put("topX", component.getTopLeftCoord()[0]);
        jsonComponent.put("topY", component.getTopLeftCoord()[1]);
        jsonComponent.put("bottomX", component.getBottomRightCoord()[0]);
        jsonComponent.put("bottomY", component.getBottomRightCoord()[1]);


        return jsonComponent;
    }
    
}
