package com.csxm;

import static org.junit.Assert.assertTrue;

import com.csxm.pdf.MyHeaderFooter;
import com.csxm.util.PdfFont;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void createPdf() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String filePath = "D:\\temp\\test.pdf";
        Document document = new Document(PageSize.A4, 72.0F, 72.0F, 72.0F, 72.0F);
        FileOutputStream out = null;
        PdfWriter instance = null;
        try {
            out = new FileOutputStream(filePath);
            instance = PdfWriter.getInstance(document, out);
            //页眉页脚
            MyHeaderFooter myHeaderFooter = new MyHeaderFooter();
            instance.setPageEvent(myHeaderFooter);
            document.open();
            String numberName = "报告编号： ";
            Date date = new Date();
            String format = sdf.format(date);
            String number = "SH" + format;
            Paragraph p1 = new Paragraph();
            Chunk chunk11 = new Chunk(numberName, PdfFont.bfMsyh11);
            Chunk chunk12 = new Chunk(number, PdfFont.bfMsyh11);
            p1.add(chunk11);
            p1.add(chunk12);
            p1.setAlignment(Element.ALIGN_LEFT);
            document.add(p1);



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
            instance.close();
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
