
/**
 * Class made purely for testing and debugging other files
 * 
 * @author Alex Wills
 * @date 3 March 2022
 */
public class Tester {
    

    public static void main(String[] args){
        //testBulletList();
        testSlideComponentIteration();
    }


    /**
     * Test the Iterator functionality for Slides
     */
    private static void testSlideComponentIteration(){
        Slide slide = new Slide();
        slide.addComponent(new PureText("test"));
        slide.addComponent(new BulletList());

        // Print every slide component
        for(SlideComponent component : slide){
            System.out.println("component: " + component);
        }
    }

    /**
     * Test all functionality of the BulletList class
     */
    private static void testBulletList(){
        BulletList bullets = new BulletList();
        System.out.println("Empty Bullet list: \n" + bullets.getText());

        // Add three text items
        bullets.addItem(new PureText());
        bullets.addItem(new PureText());
        bullets.addItem(new PureText());


        System.out.println("List with 3 empty text boxes: \n" + bullets.getText());

        // Select an item
        TextComponent selected = bullets.getItem(1);
        selected.setText("Item number 1!");

        System.out.println("List with item 1 edited: \n" + bullets.getText());

        // Change bullets
        bullets.setBullet("~~~");
        System.out.println("List with new bullets: \n" + bullets.getText());

        // Nest another list
        bullets.addItem(1, new BulletList());

        // TODO: (backend) Rethink the architecture. Selected sublist is complex, and printing sublist is buggy.
        // Select and cast the sublist
        selected = bullets.getItem(1);
        BulletList sublist = (BulletList)(selected);

        sublist.addItem(new PureText());
        sublist.addItem(new PureText("Hiya there!"));

        System.out.println("List with a sublist in index 1: \n" + bullets.getText());

    }
}
