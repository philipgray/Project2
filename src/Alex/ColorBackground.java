package Alex;
import java.awt.Color;

/**
 * Background for a slide that is a solid color
 * 
 * @author Alex Wills
 * @date 7 March 2022
 */
public class ColorBackground extends Background {
    
    private Color color;

    /**
     * Creates a color background with rgb values
     * 
     * @param r red value, from 0 - 255
     * @param g green value, from 0 - 255
     * @param b blue value, from 0 - 255
     */
    public ColorBackground(int r, int g, int b){
        super(ComponentType.Color);
        color = new Color(r, g, b);
    }

    /**
     * Creates a color background with a Color object
     * 
     * @param color the color object for the background
     */
    public ColorBackground(Color color){
        super(ComponentType.Color);
        this.color = new Color(color.getRed(), color.getGreen(), color.getBlue());
    }


    /**
     * Returns the color
     * @return the color
     */
    public Color getColor(){
        return this.color;
    }

    /**
     * Change the color
     * @param color the new background color
     */
    public void setColor(Color color){
        this.color = color;
    }

    @Override
    public SlideComponent cloneComponent() {
        return new ColorBackground(color);
    }

    /**
     * Set the color based on text input.
     * If the string is not formatted correctly, the color will default to white.
     * 
     * @param content Color, formatted "r g b". e.g. "20 255 128"
     */
    @Override
    public void setContent(String content) {
        
        String[] valuesStr = content.split(" ");
        
        // Default values to white
        int[] values = {255, 255, 255};

        // Only change values if there are 3 values in the array
        if(valuesStr.length >= 3) {
            // Try parsing the values to ints
            try {
                values[0] = Integer.parseInt(valuesStr[0]);
                values[1] = Integer.parseInt(valuesStr[1]);
                values[2] = Integer.parseInt(valuesStr[2]);
            } catch (Exception e) {
                System.out.println("Color could not be loaded properly.");
            }
        }

        // Ensure values are between 0 and 255, otherwise set to white
        if (values[0] > 255 || values[1] > 255 || values[2] > 255) {
            if(values[0] < 0 || values[1] < 255 || values[2] < 255) {
                values[0] = 255;
                values[1] = 255;
                values[2] = 255;
            }
        }

        this.color = new Color(values[0], values[1], values[2]);
        
    }

    /**
     * @return Color in the form of "r g b", where r g and b are ints between 0 and 255.
     */
    @Override
    public String getContent() {
        String colorStr = this.color.getRed() + " " + this.color.getGreen() + " " + this.color.getBlue();
        return colorStr;
    }

    @Override
    public String toString(){
        return "Color BG: " + this.color;
    }
}
