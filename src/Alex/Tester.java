package Alex;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;


import java.awt.Desktop;
import java.awt.Color;
import java.net.URI;

import java.awt.image.BufferedImage;

import GUI.*;

/**
 * Class made purely for testing and debugging other files 
 * 
 * @author Alex Wills
 * @date 3 March 2022
 */
public class Tester {
    

    public static void main(String[] args){
        // testBulletList();
        // testSlideComponentIteration();
        // testSlide();
        // testSlideDeck();

        // testJson();

        // testFileLoading();

        // testLinksInBrowser();

        // testFileLoadEditSave();
        // testLinks();

        // Test slide PNG
        testSlidePNG();
    }


    private static void testSlidePNG(){
        SlideDeck deck = SlideDeck.openSlideFile(new File("saved_slides/my amazing slideshow.json"));
        
        // Turn off the number for slide 0
        Slide slide = deck.getSlide(0);
        slide.hideNumber();

        // // This is a good way to get the buffered image
        // BufferedImage img = deck.getCurrentSlide().getSlideImage(deck.getCurrentIndex());
        // BufferedImage[] imgs = new BufferedImage[deck.getNumSlides()];

        // // This is a way to get all of the slide images (ex: for saving the PDF, or maybe for slide previews)
        // for(int i = 0; i < deck.getNumSlides(); i++){
        //     slide = deck.getSlide(i);
        //     img = slide.getSlideImage(deck.getCurrentIndex());
        //     imgs[i] = img;

        //     // Here i'm just writing the image to a folder to see the output
        //     try {
        //         ImageIO.write(img, "png", new File("slide_pdfs/slideimg" + deck.getCurrentIndex() + ".png"));


        //     } catch (IOException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     }

        // }
        
        // PDFSaver.savePDF(imgs, new File("slide_pdfs/test2.pdf"));

        // Everything is now in a single method
        deck.savePDF(new File("slide_pdfs/test3.pdf"));
    }

    private static void testLinks(){
        BrowserLinkComponent youtube = new BrowserLinkComponent("www.youtube.com");
        youtube.openURL();
    }

    /**
     * Open up youtube in the users browser
     */
    private static void testLinksInBrowser(){
        Desktop desk = Desktop.getDesktop();

        try{

            desk.browse(new URI("https://youtube.com"));
        } catch (Exception e){

        }
    }

    /**
     * Loads the default file, edits the first slide, and saves it
     */
    private static void testFileLoadEditSave(){

        // Load file
        File file = new File("saved_slides/SlideTemplates/defaultSlide.json");
        file = file.getAbsoluteFile();
        SlideDeck deck = SlideDeckFileLoader.loadSlideDeck(file);

        
        // Edit file
        System.out.println("Before edit: " + deck);

        deck.addNewSlide();
        deck.addNewSlide();
        Slide selectedSlide = deck.getCurrentSlide();
        selectedSlide.addComponent(new PureText("New component!!!"));
        selectedSlide.getTextComponents().get(0).setFontSize(45);

        // add bullet list
        SlideComponent selectedComp = new BulletList();
        selectedSlide.addComponent(selectedComp);

        BulletList list = (BulletList) selectedComp;
        list.selectList();
        list.insertItem(new PureText("Shopping List"));
        list.insertItem(new PureText("Apples"));
        list.indentItem();
        list.insertItem(new PureText("BANANA"));
        list.insertItem(new PureText("Pudding?"));
        list.indentItem();
        list.insertItem(new PureText("Banana bread?"));
        list.insertItem(new PureText("TODO"));
        list.unIndentItem();
        list.unIndentItem();
        list.insertItem(new PureText("Go to store"));
        list.indentItem();

        list.setFontSize(20);
        selectedSlide.addComponent(new LineComponent(0, 0, 20, 20, 12, Color.BLUE));

        deck.setDefaultSlide(selectedSlide);

        deck.addNewSlide();

        System.out.println("Save? " + deck.save());

        // Save file
        File outFile = new File("saved_slides/example_output.json");
        outFile = outFile.getAbsoluteFile();
        deck.saveAs(outFile);

        System.out.println("Save now? " + deck.save());

        // Confirm save by loading file again
        SlideDeck loaded = SlideDeckFileLoader.loadSlideDeck(outFile);
        System.out.println("Saved file after edit: " + loaded);
    }

    /**
     * Strictly tests files loading
     */
    private static void testFileLoading(){

        File file = new File("saved_slides/example_output.json");
        file = file.getAbsoluteFile();

        SlideDeck deck = SlideDeckFileLoader.loadSlideDeck(file);

        System.out.println("Loaded slides: " + deck);
    }

    /**
     * Test to see how manipulating JSON works
     */
    private static void testJson(){

        // Reading JSON -----------------------------
        // This is how you can get the absolute path. This way the JSON parser can use the file
        File file = new File("saved_slides/defaultSlide.json");
        file = file.getAbsoluteFile();

        FileReader fileReader;
        try {
            fileReader = new FileReader(file);

            JSONParser parser = new JSONParser();
            JSONObject slideDeckJSON = (JSONObject) parser.parse(fileReader);

            JSONObject defaultslide = (JSONObject)slideDeckJSON.get("defaultSlide");
            System.out.println(defaultslide);

            JSONArray slides = (JSONArray) slideDeckJSON.get("slides");
            
            Iterator<JSONObject> components = slides.iterator();

        // There's 3 exceptions to catch haha
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }



        // Writing JSON -----------------------------------

    }

    /**
     * Test core slide deck functionality
     */
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
    
        // BulletList list = (BulletList) slide.getComponent(2);
        // list.addItem(new PureText("List item 1"));
        // list.addItem(new PureText());

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

        bullets.insertItem(new PureText("Item 1"));
        bullets.insertItem(new PureText("Item 2"));

        System.out.println("List with 2 items: \n" + bullets.getText());

        bullets.indentItem();

        System.out.println("Indented item 2: \n" + bullets.getText());

        bullets.unIndentItem();
        bullets.unIndentItem();
        bullets.unIndentItem();

        TextComponent selected = bullets.moveUp();
        selected.setText("New Item 1");

        bullets.indentItem();
        bullets.indentItem();

        System.out.println("Unindented item 2 3 times, item 1 edited, item 1 indented twice:\n" + bullets.getText());

        bullets.deleteSelected();

        System.out.println("Deleted item 1: \n" + bullets.getText());

        bullets.deleteSelected();
        bullets.deleteSelected();

        System.out.println("Deleted item twice: \n" + bullets.getText());

        // Add lots of elements
        bullets.insertItem(new PureText("Item 1"));
        bullets.insertItem(new PureText("Item 2"));
        bullets.insertItem(new PureText("Item 3"));
        bullets.insertItem(new PureText("Item 4"));
        bullets.insertItem(new PureText("Item 5"));
        bullets.insertItem(new PureText("Item 6"));
        bullets.insertItem(new PureText("Item 7"));
        bullets.insertItem(new PureText("Item 8"));

        // Try to organize them
        bullets.moveUp();
        bullets.moveUp();
        bullets.moveUp();
        bullets.insertItem(new PureText("Item a"));
        bullets.indentItem();

        bullets.insertItem(new PureText("Item I"));
        bullets.indentItem();
        bullets.moveDown();

        bullets.insertItem(new PureText("Item a"));
        bullets.indentItem();

        bullets.moveDown();

        bullets.insertItem(new PureText("Item I"));
        bullets.indentItem();
        bullets.indentItem();

        System.out.println("New list: \n" + bullets.getText());


    }
}
