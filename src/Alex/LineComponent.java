package Alex;

/**
 * Line component has a start point and endpoint
 * 
 * @author Alex Wills
 * @date 11 March 2022
 */
public class LineComponent extends SlideComponent{

    // Start and end points
    protected int startX;
    protected int startY;
    protected int endX;
    protected int endY;

    /**
     * Creates a line with the given starting and end points
     * 
     * @param startX starting X coordinate
     * @param startY starting Y coordinate
     * @param endX ending X coordinate
     * @param endY ending Y coordinate
     */
    public LineComponent(int startX, int startY, int endX, int endY) { 
        super(0, 0, 0, 0, ComponentType.Line);
        this.setStartPoint(startX, startY);
        this.setEndPoint(endX, endY);
    }

    /**
     * Set the start point for this line
     * 
     * @param startX starting X coordinate
     * @param startY starting Y coordinate
     */
    public void setStartPoint(int startX, int startY){
        
        updateCorners(startX, startY);
        this.startX = startX;
        this.startY = startY;

    }

    /**
     * Set the end point for this line
     * 
     * @param endX end X coordinate
     * @param endY end Y coordinate
     */
    public void setEndPoint(int endX, int endY){
        
        updateCorners(endX, endY);
        this.endX = endX;
        this.endY = endY;

    }

    /**
     * Gets the starting coordinate for this line
     * 
     * @return array for starting point [0] = x, [1] = y
     */
    public int[] getStartPoint(){
        return new int[] {startX, startY};
    }

    /**
     * Gets the end coordinate for this line
     * 
     * @return array for the end point [0] = x, [1] = y
     */
    public int[] getEndPoint(){
        return new int[] {endX, endY};
    }

    /**
     * Update the corners of this component if they should be updated
     * 
     * @param xCoord the x coordinate to set
     * @param yCoord the y coordinate to set
     */
    private void updateCorners(int xCoord, int yCoord){
        
        if(xCoord < this.topLeftXCoord){
            this.topLeftXCoord = xCoord;
        }

        if(xCoord > this.bottomRightXCoord){
            this.bottomRightXCoord = xCoord;
        }

        if(yCoord < this.topLeftYCoord){
            this.topLeftYCoord = yCoord;
        }

        if(yCoord > this.bottomRightYCoord){
            this.bottomRightYCoord = yCoord;
        }
    }

    @Override
    public SlideComponent cloneComponent() {
        return new LineComponent(this.startX, this.startY, this.endX,  this.endY);
    }

    /**
     * @param content coordinates of the line in string form "startX startY endX endY"
     */
    @Override
    public void setContent(String content) {
        String[] points = content.split(" ");
        
        // Only change length if the string is formatted correctly
        if(points.length >= 4){
            this.startX = Integer.parseInt(points[0]);
            this.startY = Integer.parseInt(points[1]);
            this.endX = Integer.parseInt(points[2]);
            this.endY = Integer.parseInt(points[3]);
        }
    }

    /**
     * @return string in form "startX startY endX endY" (e.g. "0 0 10 10")
     */
    @Override
    public String getContent() {
        return startX + " " + startY + " " + endX + " " + endY; 
    }

}