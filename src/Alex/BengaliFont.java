package Alex;

import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontFormatException;

/**
 * This file only exists for universal access of a Bengali supporting font (for the system windows before we create a slide deck)
 * 
 * @author Alex Wills
 * @date 12 March 2022
 */
public class BengaliFont {
    
    private static Font bengaliFont = null;

    public static Font getBengaliFont(){
        
        // If the font isn't initialized, initialize it
        if(bengaliFont == null){
            try {
                bengaliFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/akaashnormal.ttf").getAbsoluteFile());
                bengaliFont = bengaliFont.deriveFont(20f);
            } catch (FontFormatException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return bengaliFont;
    }
}
