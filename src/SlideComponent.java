
/**
 * Interfeace to define a slide component
 * 
 * @author Alex Wills
 * @date 2 March 2022
 */
public abstract class SlideComponent {

    protected int topLeftXCoord;
    protected int topLeftYCoord;
    protected int bottomRightXCoord;
    protected int bottomRightYCoord;

    /**
     * Initializes slide component with coordinates for the corners
     * @param topLeftX X coord of top left corner
     * @param topLeftY Y coord of top left corner
     * @param bottomRightX X coord of bottom right corner
     * @param bottomRightY Y coord of bottom right corner
     */
    public SlideComponent(int topLeftX, int topLeftY, int bottomRightX, int bottomRightY){
        this.setTopLeftCoord(topLeftX, topLeftY);
        this.setBottomRightCoord(bottomRightX, bottomRightY);
    }

    /**
     * Calculates whether the given coordinates are within this text component's bounds.
     * NOTE: Uses top left corner of the window as 0,0
     * Example: Pass in the mouse coordinates to see if the mouse is over this object
     * 
     * @param x the x coordinate to check
     * @param y the y coordinate to check
     * @return true if the coordinates are within this object's bounds, false otherwise
     */
    public boolean isColliding(int x, int y){
        boolean isColliding = false;

        // True if x is between two corners, and y is between two corners
        if(x >= topLeftXCoord && x <= bottomRightXCoord){
            if(y >= topLeftYCoord && y <= bottomRightYCoord){
                isColliding = true;
            }
        }

        return isColliding;
    }

    /**
     * Clones this component by value
     * 
     * @return a clone of this component that can be modified independently
     */
    public abstract SlideComponent cloneComponent();

    /**
     * Set the top left coordinate of this component
     * 
     * @param x x coordinate of top left corner
     * @param y y coordinate of top left corner
     */
    public void setTopLeftCoord(int x, int y){
        this.topLeftXCoord = x;
        this.topLeftYCoord = y;
    }

    /**
     * Set the bottom right coordinate of this component
     * 
     * @param x x coordinate of bottom right corner
     * @param y y coordinate of bottom right corner
     */
    public void setBottomRightCoord(int x, int y){
        this.bottomRightXCoord = x;
        this.bottomRightYCoord = y;
    }

    /**
     * Returns the top left corner of this component as an int[]
     * @return int[] in [x, y] form. [0] = x coordinate, [1] = y coordinate
     */
    public int[] getTopLeftCoord(){
        return new int[] {topLeftXCoord, topLeftYCoord};
    }
    
    /**
     * Returns the bottom right corner of this component as an int[]
     * @return int[] in [x, y] form. [0] = x coordinate, [1] = y coordinate
     */
    public int[] getBottomRightCoord(){
        return new int[] {bottomRightXCoord, bottomRightYCoord};
    }
}
