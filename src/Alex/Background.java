package Alex;
/**
 * Defines the background of a slide
 * 
 * @author Alex Wills
 * @date 7 March 2022
 */
public abstract class Background extends SlideComponent{

    public Background(ComponentType type) {
        super(0, 0, 1000, 1000, type);
    }
    
}
