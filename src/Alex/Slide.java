import java.util.ArrayList;
import java.util.Iterator;
import java.awt.image.BufferedImage;

/**
 * A slide for a presentation. Contains multiple slide components
 * 
 * @author Alex Wills
 * @date 2 March 2022
 */
public class Slide implements Iterable<SlideComponent> {

    // Array list containing all components
    private ArrayList<SlideComponent> components;

    // Array lists containing specific components
    private ArrayList<TextComponent> textComponents;
    private ArrayList<ImageComponent> imageComponents;
    private ArrayList<BulletList> listComponents;
    private ArrayList<SlideComponent> remainingComponents;

    private Background background;

    // True if the slide should have a small numer at the bottom of the screen.
    private boolean showNumber = true;

    /**
     * Constructs an empty slide for the slide deck.
     */
    public Slide(){
        components = new ArrayList<SlideComponent>();
        textComponents = new ArrayList<TextComponent>();
        imageComponents = new ArrayList<ImageComponent>();
        remainingComponents = new ArrayList<SlideComponent>();
        listComponents = new ArrayList<BulletList>();
    }


    /**
     * Adds a new component to this slide
     * 
     * @param newComponent the component to add to the slide
     */
    public void addComponent(SlideComponent newComponent){
        this.components.add(newComponent);

        ComponentType type = newComponent.getType();

        // Add component to a list based on its type
        if (type == ComponentType.Text){
            this.textComponents.add( (TextComponent) newComponent);

        } else if (type == ComponentType.Image){
            this.imageComponents.add( (ImageComponent) newComponent);

        } else if (type == ComponentType.BulletList){
            this.listComponents.add( (BulletList) newComponent);

        } else {
            this.remainingComponents.add( newComponent );
        }
    }

    /**
     * Removes a component from the slide
     * 
     * @param component the component to remove from the slide
     */
    public void removeComponent(SlideComponent toRemove){

        this.components.remove(toRemove);
        ComponentType type = toRemove.getType();

        // Remove the component from its sublist
        if (type == ComponentType.Text){
            this.textComponents.remove(toRemove);

        } else if (type == ComponentType.Image){
            this.imageComponents.remove(toRemove);

        } else if (type == ComponentType.BulletList){
            this.listComponents.remove(toRemove);

        } else {
            this.remainingComponents.remove(toRemove);
        }
    }

    /**
     * Returns a specific element in the slide
     * 
     * @param index the index of the slide element
     * @return the slide component at the specified index
     */
    public SlideComponent getComponent(int index){
        return this.components.get(index);
    }

    /**
     * Sets the background for this slide
     * 
     * @param bg the new background for the slide
     */
    public void setBackground(Background bg){
        this.background = bg;
    }

    /**
     * Get the background of this slide
     * 
     * @return the background of this slide
     */
    public Background getBackground(){
        return this.background;
    }

    /**
     * Should this slide have a number at the bottom of the screen?
     * The number is determined by the SlideDeck array list index.
     * 
     * @return true if the slide should have a number at the bottom of the slide
     */
    public boolean shouldShowNumber(){
        return showNumber;
    }

    /**
     * Set the slide to not show its number at the bottom of the screen.
     * (Only changes the showNumber variable)
     */
    public void hideNumber(){
        showNumber = false;
    }

    /**
     * Sets the slide to show its number at the bottom of the screen.
     * (Only changes the showNubmer variable)
     */
    public void showNumber(){
        showNumber = true;
    }

    /**
     * Toggles the slide's showNumber field
     */
    public void toggleShowNumber(){
        showNumber = !showNumber;
    }


    /**
     * Draws the slide as a buffered image and returns the product for presenting and thumbnails.
     * 
     * @return BufferedImage representation of the slide
     */
    public BufferedImage getSlideImage(int slideNumber, int width, int height){
        //TODO: Slide as buffered image
        // Store the slide in a BufferedImage
        BufferedImage slideImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);


        // If we are showing the number, add a small number at the bottom
        if(showNumber){

        }

        return slideImg;
    }

    /**
     * Clones this slide and returns a copy.
     * The slide is copied by value, not reference, so this slide and the copy can be edited separately
     * 
     * @return a copy of this slide that can be modified without modifying this slide
     */
    public Slide cloneSlide(){
        Slide clone = new Slide();

        // Add a copy of every component
        for(SlideComponent component : this){
            SlideComponent duplicateComp = component.cloneComponent();

            // Copy the component to the same location
            duplicateComp.setTopLeftCoord(component.getTopLeftCoord()[0], component.getTopLeftCoord()[1]);
            duplicateComp.setBottomRightCoord(component.getBottomRightCoord()[0], component.getBottomRightCoord()[1]);
            clone.addComponent( duplicateComp );
        }

        // Copy the background
        clone.setBackground((Background) this.background.cloneComponent());

        return clone;
    }

    /**
     * Returns a list of this slide's text components.
     * Example use: to draw all of the text to the screen, you can use this method and iterate over the array list.
     * 
     * @return an ArrayList containing all of this slide's text components
     */
    public ArrayList<TextComponent> getTextComponents(){
        return textComponents;
    }

    /**
     * Returns a list of this slide's image components.
     * All image components have a method to return a buffered image.
     * 
     * @return ArrayList of all ImageComponents on this slide
     */
    public ArrayList<ImageComponent> getImageComponents(){
        return imageComponents;
    }

    /**
     * Returns a list of all the slide's list components
     * 
     * @return ArrayList of all BulletList components on this slide
     */
    public ArrayList<BulletList> getListComponents(){
        return listComponents;
    }

    /**
     * Returns a list of this slide's components that are not in any specific sublists.
     * 
     * @return ArrayList of all slide components that are not in any other sublists
     */
    public ArrayList<SlideComponent> getNonspecificComponents(){
        return remainingComponents;
    }

    // Iterable interface
    @Override
    public Iterator<SlideComponent> iterator() {
        return new SlideComponentIterator(components);
    }

    /**
     * Print out the slide's information and all of its components
     */
    @Override
    public String toString(){
        String representation = "\nSlide: \nComponents:";
        for(SlideComponent s : this){
            representation += "\n\t" + s;
        }

        return representation;
    }
}