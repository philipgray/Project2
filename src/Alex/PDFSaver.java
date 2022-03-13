package Alex;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class uses Apache's PDFBox library and the frontend GUI to save the presentation as a PDF
 *
 * @author Alex Wills
 * @date 13 March 2022
 */
public class PDFSaver {


    public static void savePDF(){

        Document doc = new Document(PageSize.A4, 20, 20, 20, 20);
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("saved_slides/test.pdf"));

            doc.open();
            doc.add( new Table(4, 2));
            doc.newPage();
            doc.close();




        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        savePDF();
    }
}
