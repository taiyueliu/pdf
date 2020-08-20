package com.csxm;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSONObject;
import com.csxm.pdf.MyHeaderFooter;
import com.csxm.util.PdfFont;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

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
            //后面设置间距
            p1.setSpacingAfter(500f);
            document.add(p1);

            PdfPTable table = new PdfPTable(3);
            table.setSplitLate(false);
            table.setTotalWidth(PageSize.A4.getWidth() - 144);
            int width1[] = {1,1,2};
            table.setWidths(width1);
            PdfPCell pdfPCell=getTableCell(new Paragraph("对接系统",PdfFont.bfSong14));
            pdfPCell.setRowspan(3);
            table.addCell(pdfPCell);
            table.addCell(getTableCell(new Paragraph("系统名称",PdfFont.bfSong14)));
            table.addCell(getTableCell(new Paragraph("火灾自动报警系统",PdfFont.bfSong14)));

            table.addCell(getTableCell(new Paragraph("系统接口",PdfFont.bfSong14)));
            table.addCell(getTableCell(new Paragraph("火灾报警信息",PdfFont.bfSong14)));


            table.addCell(getTableCell(new Paragraph("测试时间",PdfFont.bfSong14)));
            table.addCell(getTableCell(new Paragraph("2010-10-10 10:10:12",PdfFont.bfSong14)));
            document.add(table);

            String ss = "{\n" +
                    "\"descript\":\"长阳创谷0012\",\n" +
                    "\"deviceName\":\"长阳创谷0021\",\n" +
                    "\"deviceNo\":\"123test002011126\",\n" +
                    "\"deviceType\":\"117\",\n" +
                    "\"deviceTypeName\":\"防火卷帘控制器\",\n" +
                    "\"openProjectId\":\"744503453971120128\",\n" +
                    "\"logTime\":\"2020-10-35 10:10:45\",\n" +
                    "\"actionStatus\":\"0\",\n" +
                    "\"workStatus\":\"1\",\n" +
                    "\"faultStatus\":\"0\"\n" +
                    "}";

            JSONObject jsonObject = JSONObject.parseObject(ss);
            Map<String, Object> jsonMap = JSONObject.toJavaObject(jsonObject, Map.class);



            PdfPTable table1 = new PdfPTable(2);
            table1.setSplitLate(false);
            table1.setTotalWidth(PageSize.A4.getWidth() - 144);
            int width2[] = {1,3};
            table1.setWidths(width2);

            PdfPCell pdfPCell1=getTableCellMin(new Paragraph("上报数据",PdfFont.bfSong14),Element.ALIGN_CENTER,jsonMap.size());
            table1.addCell(pdfPCell1);




            StringBuffer stringBuffer = new StringBuffer(" {\n");
            int i = 0;
            for (Map.Entry<String, Object> stringObjectEntry: jsonMap.entrySet()) {

                stringBuffer.append("  \"")
                        .append(stringObjectEntry.getKey())
                        .append("\":\"")
                        .append(stringObjectEntry.getValue())
                        .append("\"");

                if(i<jsonMap.size()-1){
                    stringBuffer.append(",\n");
                }else{
                    stringBuffer.append("\n }");
                }
                i++;
            }
            Paragraph elements = new Paragraph();
            Chunk chunk = new Chunk(stringBuffer.toString(), PdfFont.bfSong9);
            chunk.setLineHeight(15);
            elements.add(chunk);
            PdfPCell tableCellMin = getTableCellMin(elements, Element.ALIGN_LEFT, jsonMap.size());
            table1.addCell(tableCellMin);
            document.add(table1);


            Paragraph elementsEnd1 = new Paragraph("声明",PdfFont.bfMsyh12);
            elementsEnd1.setKeepTogether(true);
            elementsEnd1.setAlignment(Element.ALIGN_CENTER);
            //下一页
            document.newPage();
            document.add(elementsEnd1);
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


    public static PdfPCell getTableCell(Paragraph paragraph){
        PdfPCell cell = new PdfPCell(paragraph);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorderWidth(1f);
        cell.setFixedHeight(30);
        return cell;
    }

    public static PdfPCell getTableCellMin(Paragraph paragraph,int element,int size){
        PdfPCell cell = new PdfPCell(paragraph);
        cell.setHorizontalAlignment(element);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorderWidth(1f);
//        cell.setBorderWidthLeft(1f);
//        cell.setBorderWidthRight(1f);
//        cell.setBorderWidthBottom(1f);
        cell.setFixedHeight(19*size);
        return cell;
    }



    public static void main(String[] args) {
        String ss = "{\n" +
                "\"descript\":\"长阳创谷0012\",\n" +
                "\"deviceName\":\"长阳创谷0021\",\n" +
                "\"deviceNo\":\"123test002011126\",\n" +
                "\"deviceType\":\"117\",\n" +
                "\"deviceTypeName\":\"防火卷帘控制器\",\n" +
                "\"openProjectId\":\"744503453971120128\",\n" +
                "\"logTime\":\"2020-10-35 10:10:45\",\n" +
                "\"actionStatus\":\"0\",\n" +
                "\"workStatus\":\"1\",\n" +
                "\"faultStatus\":\"0\"\n" +
                "}";

        JSONObject jsonObject = JSONObject.parseObject(ss);


        System.out.println(jsonObject.toJSONString());
    }
}
