package com.csxm.pdf;

import com.csxm.util.PdfFont;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

/**
 * @Author 刘太月
 * @Despriction 页眉和页脚 忽略首页
 * @Created in 2019/12/16 11:21
 * @version: 1.0
 * <p>copyright: Copyright (c) 2018</p>
 */
public class MyHeaderFooter extends PdfPageEventHelper{
    /**
     * 总页数
     */
    PdfTemplate totalPage;

    /**
     * 打开文档时，创建一个总页数的模版
     * @param writer
     * @param document
     */
    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        PdfContentByte cb =writer.getDirectContent();
        totalPage = cb.createTemplate(30, 16);
    }

    /**
     * 一页加载完成触发，写入页眉和页脚
     * @param writer
     * @param document
     */
    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        try {
            //忽略首页

            //页眉网格
            PdfPTable headerTable = new PdfPTable(1);
            headerTable.setTotalWidth(PageSize.A4.getWidth() - 144);
            int width1[] = {1};
            headerTable.setWidths(width1);
            headerTable.setLockedWidth(true);
            headerTable.getDefaultCell().setBorder(Rectangle.BOTTOM);

//                Image image = Image.getInstance("http://rayeye-crm.oss-cn-shanghai.aliyuncs.com/file/crm/files/pdfheader.png");
//                image.setAlignment(Image.ALIGN_MIDDLE);
//                image.setAlignment(Image.ALIGN_CENTER);
//                //依照比例缩放
//                image.setWidthPercentage(100);
//                PdfPCell headerCell1 = new PdfPCell();
//                headerCell1.addElement(image);
//                headerCell1.setBorderWidth(0);
//                headerCell1.setBorderWidthBottom(1f);
//                headerCell1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
//                //然并卵
//                headerCell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
//                //把字垂直居中
//                headerCell1.setPaddingTop(-2f);
//                //把字垂直居中
//                headerCell1.setPaddingBottom(8f);
//                headerCell1.setMinimumHeight(40);
//                headerTable.addCell(headerCell1);
            PdfPCell headerCell2 = new PdfPCell(new Paragraph("消防大数据对接平台（beta版）对接能力报告", PdfFont.bfSong9));
            //0 无边际 1全边框
            headerCell2.setBorderWidth(0);
            //下边框
            headerCell2.setBorderWidthBottom(1f);
            headerCell2.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            //然并卵
            headerCell2.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
            //把字垂直居中
            headerCell2.setPaddingBottom(6f);
            headerTable.addCell(headerCell2);
            headerTable.writeSelectedRows(0, -1, 72, PageSize.A4.getHeight() - 20, writer.getDirectContent());

//            //页脚网格
            PdfPTable footTable = new PdfPTable(2);
            int width2[] = {23,4};
            footTable.setWidths(width2);
            footTable.getDefaultCell().setFixedHeight(10);
            footTable.getDefaultCell().setBorder(Rectangle.BOTTOM);
            footTable.setTotalWidth(PageSize.A4.getWidth() - 144);
            footTable.setLockedWidth(true);
            PdfPCell footCell1 = new PdfPCell(new Paragraph("",  PdfFont.bfSong9));
            footCell1.setBorderWidth(0);
            footCell1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            footCell1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            footTable.addCell(footCell1);


            String text = "第" + writer.getPageNumber() + "页/";
            PdfPCell footCell2 = new PdfPCell(new Paragraph(text, PdfFont.bfSong9));
            footCell2.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            footCell2.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
            footCell2.setBorderWidth(0);
            footTable.addCell(footCell2);

            // 新建获得用户页面文本和图片内容位置的对象
            PdfContentByte pdfContentByte = writer.getDirectContent();
            // 保存图形状态
            pdfContentByte.saveState();
            // 将模板加入到内容（content）中- // 定位'Y'
            pdfContentByte.addTemplate(totalPage, PageSize.A4.getWidth() - 110, 44);
            pdfContentByte.restoreState();
            // 将页眉写到document中，位置可以指定，指定到下面就是页脚
            footTable.writeSelectedRows(0, -1, 72, 60,pdfContentByte);

        } catch (Exception de) {
            de.printStackTrace();
            throw new ExceptionConverter(de);
        }
    }

    /**
     * 全部完成后，将总页数的pdf模版写到指定位置
     * @param writer
     * @param document
     */
    @Override
    public void onCloseDocument(PdfWriter writer,Document document) {
        totalPage.beginText();
        totalPage.setFontAndSize(PdfFont.bfSong, 9);
        //上升 不设置文字下部被遮住
        totalPage.setTextRise(5);
        // 设置总页数的值到模板上，并应用到每个界面
        totalPage.showText("共"+String.valueOf(writer.getPageNumber())+"页");
        totalPage.endText();

    }

}
