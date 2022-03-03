import java.util.ArrayList;
/**
 * A slide for a presentation. Contains multiple slide components
 * 
 * @author Alex Wills
 * @date 2 March 2022
 */
public class Slide{

    ArrayList<SlideComponent> components;

    public Slide(){
        components = new ArrayList<SlideComponent>();
    }
}