package Alex;


import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Text that stores a URL that can be opened in a browser
 * 
 * @author Alex Wills
 * @date 12 March 2022
 */
public class BrowserLinkComponent extends PureText{
    
    /**
     * Create a link with a specific URL
     * 
     * @param url the url for this component to link to
     */
    public BrowserLinkComponent(String url){
        super(url);
        this.componentType = ComponentType.Link;
    }

    /**
     * Opens the URL attached to this object in the browser
     */
    public void openURL(){
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI( this.text ));
        } catch (IOException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
