package Alex;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import java.awt.image.BufferedImage;
import java.awt.Color;

/**
 * This class uses the iText library and the frontend GUI to save the presentation as a PDF
 *
 * @author Alex Wills
 * @date 13 March 2022
 */
public class PDFSaver {


    /**
     * Saves a PDF to the output file, where every page is a BufferedImage
     * 
     * @param pages the list of buffered images to save as a PDF
     * @param outputLocation the file location to save the PDF as
     */
    public static void savePDF(BufferedImage[] pages, File outputLocation){

        PdfWriter writer;
        try {
            // Create file writer and pdf doc
            writer = new PdfWriter(outputLocation);
            PdfDocument pdfDoc = new PdfDocument(writer);

            // Set page to same size as images
            pdfDoc.setDefaultPageSize( new PageSize(pages[0].getWidth(), pages[0].getHeight()));
            Document doc = new Document(pdfDoc);
            doc.setMargins(0, 0, 0, 0);
            
            
            ImageData tempImgData;
            Image pdfImg;
            // Add the images as new pages
            for(int i = 0; i < pages.length; i++){
                
                pdfDoc.addNewPage();

                // Create the image element for the pdf
                tempImgData = ImageDataFactory.create(pages[i], Color.WHITE);
                pdfImg = new Image(tempImgData);
                doc.add(pdfImg);    
            }

            // Close the document to save it!
            doc.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
