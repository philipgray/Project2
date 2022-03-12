package Alex;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

/**
 * Image component to add to the slide
 * 
 * @author Alex Wills
 * @date 7 March 2022
 */
public class ImageComponent extends SlideComponent{

    File imageFile;
    BufferedImage image;

    /**
     * Construct an image component from an image file
     * 
     * @param imageFile filepath of the image file
     */
    public ImageComponent(File imageFile){
        super(0, 0, 0, 0, ComponentType.Image);
        this.imageFile = imageFile;
        loadImage();
    }

    /**
     * Load the image from the imageFile as a buffered image
     */
    private void loadImage(){
        
        // Try to load in the image
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            // Here you could add a default image file packaged with the software
            e.printStackTrace();
        }
    }

    /**
     * Returns the buffered image loaded in this component
     * 
     * @return the buffered image loaded in this component
     */
    public BufferedImage getImage(){
        return this.image;
    }

    @Override
    public SlideComponent cloneComponent() {
        File newFile = new File( imageFile.getAbsolutePath() );
        return new ImageComponent(newFile);
    }

    /**
     * @param content the file path of the image file
     */
    @Override
    public void setContent(String content) {
        imageFile = new File(content);
        imageFile = imageFile.getAbsoluteFile();  
        loadImage();      
    }

    /**
     * @return the filepath of the image file
     */
    @Override
    public String getContent() {
        return imageFile.getAbsolutePath();
    }
    
    @Override
    public String toString(){
        return "Image: " + imageFile.getAbsolutePath();
    }
}
