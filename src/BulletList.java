import java.util.ArrayList;

import org.json.simple.ItemList;

/**
 * A list of text components, organized with bullets
 * 
 * @author Alex Wills
 * @date 3 March 2022
 */
public class BulletList extends TextComponent {

    // Ordered list of items in the bullet list.
    private ArrayList<TextComponent> listItems;
    // This list contains the level of each component (instead of pairing the level directly with the text component)
    private ArrayList<Integer> listLevels;
    // The string to use as a bullet (e.g. "-", "~", etc.)
    private String bullet;

    // When editing a bullet list, you select a component to edit.
    private TextComponent selected;
    private int selectedLevel;
    private int selectedIndex;

    /**
     * Creates an empty bulleted list
     */
    public BulletList(){
        // Default without coordinates
        super(0, 0, 0, 0);
        listItems = new ArrayList<TextComponent>();

        listLevels = new ArrayList<Integer>();

        selectedLevel = 0;
        selected = null;
        bullet = "-";

        // Mark as a bullet list
        this.componentType = ComponentType.BulletList;
    }


    /**
     * Call this method to begin editing the list. 
     * It will return the last TextComponent in the list and internally prepare
     * the list to be edited. 
     * 
     * After calling this method, the last item in the list will be "selected".
     * You can call methods to navigate up and down the list, and you can call a method to 
     * indent the currently selected item as part of a sublist. 
     * You can also call a metho to add to the list, which will add an element at the same 
     * sublevel as the currently selected one.
     * 
     * @return the last item in the list, or null if there is no item to select
     */
    public TextComponent selectList(){
        // If the list is empty, there is nothing to select. You will be able to add items to this list.
        if(listItems.size() != 0){

            // Select the last item in the list
            selectedIndex = listItems.size() - 1;
            selected = listItems.get(selectedIndex);
            selectedLevel = listLevels.get(selectedIndex);

        } else {
            // If the list is empty, ensure the selected values are correct
            selected = null;
            selectedLevel = 0;
        }

        return selected;
    }

    /**
     * When an item is selected, you can call this method to move up in the list.
     * 
     * @return the element above the currently selected item. It returns the same item that was 
     * previously selected if it is at the top of the list (i.e. nothing happens)
     */
    public TextComponent moveUp(){
        
        // Only move up if it is possible to do so
        if(selectedIndex > 0){
            selectedIndex--;
            selected = listItems.get(selectedIndex);
            selectedLevel = listLevels.get(selectedIndex);
        }

        return selected;
    }

    /**
     * When an item is selected, call this method to move down in the list.
     * 
     * @return the element below the currently selected item. It returns the same item
     * that was previously selected if it is at the bottom of the list.
     */
    public TextComponent moveDown(){

        // Only move down if there is an element below the selected one. 
        if(selectedIndex + 1 < listItems.size()){
            selectedIndex++;
            selected = listItems.get(selectedIndex);
            selectedLevel = listLevels.get(selectedIndex);
        }

        return selected;
    }

    /**
     * Adds a text component underneath the currently selected component.
     * Also selects this element in the list, so that you can moveUp(), moveDown(),
     * indent(), and deleteSelected()
     * 
     * @param toAdd the text component to add
     */
    public void insertItem(TextComponent toAdd){
        
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

    /**
     * Sets the entire bullet list, using a string as content.
     * \n represents a new entry in the list, \t represents nested lists.
     * A bullet is specified at the beginning.
     * 
     * @param content the list in string form. Begin the list with the bullet, \n and then the list.
     * Separate items in the list with \n, and use \t to specify sublevels of the list.
     */
    @Override
    public void setContent(String content) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getContent() {
        // TODO Auto-generated method stub
        return null;
    }

    
}
