
/**
 * Class made purely for testing and debugging other files
 * 
 * @author Alex Wills
 * @date 3 March 2022
 */
public class Tester {
    

    public static void main(String[] args){
        // testBulletList();
        testSlideComponentIteration();
        // testSlide();
        // testSlideDeck();
    }

    private static void testSlideDeck(){
        SlideDeck deck1 = new SlideDeck();

        System.out.println("Empty slide deck:\n" + deck1);

        deck1.addNewSlide();
        deck1.addNewSlide();

        System.out.println("Deck with 3 empty slides:\n" + deck1);
        
        Slide selectedSlide = deck1.getSlide(1);
        selectedSlide.addComponent(new PureText());

        System.out.println("Modified slide 1 to have a text component:\n" + deck1);

        selectedSlide = selectedSlide.cloneSlide();
        SlideDeck deck2 = new SlideDeck(selectedSlide);
        deck2.addNewSlide();
        deck2.addNewSlide();
        
        System.out.println("New slide deck with a cutsom default slide:\n" + deck2);
        selectedSlide.addComponent(new PureText("SECOND ELEMENT OF DEFAULT SLIDE"));
        deck2.addNewSlide();
        System.out.println("Changed default slide and added new slide:\n" + deck2);
    }

    /**
     * Test the slide class
     */
    private static void testSlide(){
        Slide slide = new Slide();

        // Add some guys to the slide
        slide.addComponent(new PureText("Text 1"));
        slide.addComponent(new PureText("Text 2"));
        slide.addComponent(new BulletList());
    
        BulletList list = (BulletList) slide.getComponent(2);
        list.addItem(new PureText("List item 1"));
        list.addItem(new PureText());

        // Print out and iterate all text components
        // Example of .getTextComponents
        System.out.println("Printing text components: ");
        for(TextComponent s : slide.getTextComponents()){
            System.out.println(s);
        }

        // Clone slide
        Slide slide2 = slide.cloneSlide();
        // Fake clone slide (to make sure the clone function isnt useless)
        Slide sameSlide = slide2;

        System.out.println("Printing slide2 components: ");
        for(SlideComponent s : slide2){
            System.out.println(s);
        }

        TextComponent text = (TextComponent)(slide2.getComponent(0));
        text.setText("CHANGED TEXT");

        // Confirm two separate slides
        System.out.println("Printing original slide components: ");
        for(SlideComponent s : slide){
            System.out.println(s);
        }

        // slide2 was cloned with the clone function. It is independent from the original slide
        System.out.println("Printing slide2 components (changed first text component): ");
        for(SlideComponent s : slide2){
            System.out.println(s);
        }

        // sameSlide was copied by reference, before the text was changed. It is not independent from slide2
        System.out.println("Printing sameSlide components (should be the same as slide2): ");
        for(SlideComponent s : sameSlide){
            System.out.println(s);
        }

        // Test slide's toString method
        System.out.println("Slide.toString() = \n" + slide);
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

        // Print every text component specifically
        for(TextComponent text : slide.getTextComponents()){
            System.out.println("Text: " + text.getText());
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
