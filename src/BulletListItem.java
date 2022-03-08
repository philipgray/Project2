
/**
 * Item to store in a bullet list. Can be any kind of text component, at any level of the bullet list.
 * Similar to a linked list node.
 * 
 * @author Alex Wills
 * @date 7 March 2022
 */
public class BulletListItem {
    // Parents are higher on the hierarchy, children are lower
    private BulletListItem parent;
    private BulletListItem child;

    // Previous/next are equal in the hierarchy
    private BulletListItem previous;
    private BulletListItem next;
    private int level;

    // This is the bullet that will show up next to the text item
    private String bullet;

    private TextComponent content;


    public BulletListItem(TextComponent text, String bullet){
        this.content = text;
        this.parent = null;
        this.child = null;
        this.previous = null;
        this.next = null;
        this.level = 0;
        this.bullet = bullet;
    }


    // Getters and setters

    public TextComponent getContent(){
        return this.content;
    }

    public int getLevel(){
        return this.level;
    }

    public void setParent(BulletListItem parent){
        this.parent = parent;
        this.level = parent.getLevel() + 1;
    }

    public BulletListItem getParent(){
        return this.parent;
    }

    public void setChild(BulletListItem child){
        this.child = child;
    }

    public BulletListItem getChild(){
        return this.child;
    }

    public void setPrevious(BulletListItem previous){
        this.previous = previous;
    }
    
    public BulletListItem getPrevious(){
        return this.previous;
    }
    
    public void setNext(BulletListItem next){
        this.next = next;
    }
    
    public BulletListItem getNext(){
        return this.next;
    }
    
    public void setBullet(String bullet){
        this.bullet = bullet;
    }
    
}
