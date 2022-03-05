import java.util.ArrayList;

/**
 * A list of text components, organized with bullets
 * 
 * @author Alex Wills
 * @date 3 March 2022
 */
public class BulletList extends TextComponent {

    // Ordered list of items in the bullet list.
    private ArrayList<TextComponent> listItems;
    // The string to use as a bullet (e.g. "-", "~", etc.)
    private String bullet;

    /**
     * Creates an empty bulleted list
     */
    public BulletList(){
        listItems = new ArrayList<TextComponent>();
        bullet = "-";
    }

    /**
     * Changes the string/character used as the bullets in the list
     * 
     * @param newBullet the bullet to use
     */
    public void setBullet(String newBullet){
        this.bullet = newBullet;
    }

    /**
     * Adds a new item to the bullet list in a specific location
     * 
     * @param index the index for the new item in the list
     * @param newItem the item to add to the list
     */
    public void addItem(int index, TextComponent newItem){
        listItems.add(index, newItem);
    }

    /**
     * Adds a new item to the end of the bullet list
     * 
     * @param newItem the item to add to the list
     */
    public void addItem(TextComponent newItem){
        listItems.add(newItem);
    }

    /**
     * Gets a specific element of the list
     * 
     * @param index the index of the element you would like to access
     * @return the text component at that index
     */
    public TextComponent getItem(int index){
        return listItems.get(index);
    }

    
    /**
     * Copy the bullet list in its entirety
     */
    @Override
    public BulletList cloneComponent(){
        // Create a new list, set the bullet, and copy every element in the list
        BulletList list = new BulletList();
        list.setBullet(list.bullet);

        // Copy all of the text components
        for(TextComponent entry : this.listItems){
            list.addItem( (TextComponent) entry.cloneComponent() );
        }

        return list;
    }


    //TODO: Rework nested lists
    @Override
    public String getText() {
        
        String finalString = "";

        // Add each item to the list one by one
        for(TextComponent item : listItems){
            finalString += "\t" + this.bullet + " " + item.getText() + "\n";
        }

        return finalString;
    }
    
    /**
     * Changes the bullet to be used by the list
     * 
     * @param text the new bullet to use
     */
    @Override
    public void setText(String text){
        this.setBullet(text);
    }

    
}
