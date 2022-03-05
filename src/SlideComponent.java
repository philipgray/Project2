import java.util.Iterator;

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
