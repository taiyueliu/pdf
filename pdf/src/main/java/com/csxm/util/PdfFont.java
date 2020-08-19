package com.csxm.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;

/**
 * @description:
 * @author: 刘太月
 * @time: 2020/8/19 14:28
 */
public class PdfFont {
    // 定义全局的字体静态变量
    //黑体26 一号
    public static Font bfHei26;
    //黑体22 二号
    public static Font bfHei22;
    //宋体9
    public static Font bfSong9;
    //宋体14 四号
    public static Font bfSong14;
    //宋体14 四号  下划线
    public static Font bfSong14Underline;
    //宋体14 四号  下划线
    public static Font bfSong14Strong;
    //宋体24 小一
    public static Font bfSong24;
    //宋体16 三号
    public static Font bfSong16;
    //宋体11 五号
    public static Font bfSong11;

    //微软雅黑11 五号
    public static Font bfMsyh11;
    //微软雅黑36 小初
    public static Font bfMsyh36;
    //微软雅黑36 小初 加粗
    public static Font bfMsyh36Strong;
    //微软雅黑20
    public static Font bfMsyh20;
    //微软雅黑26 一号
    public static Font bfMsyh26;
    //微软雅黑15 小三
    public static Font bfMsyh15;
    //微软雅黑12 小四
    public static Font bfMsyh12;
    //微软雅黑12 小四 灰色
    public static Font bfMsyh12Gray;
    //微软雅黑14 小三
    public static Font bfMsyh14Strong;
    //微软雅黑18 小二
    public static Font bfMsyh18Strong;
    //微软雅黑24 小一
    public static Font bfMsyh24Strong;

    public static BaseFont bfSong;
    public static BaseFont bfHei;
    public static BaseFont bfMsyh;
    // 初始化字体
    static {
        try {
            //字体存放路径
            /**
             * 普通Java项目
             */
            String fontPath = PdfFont.class.getClassLoader().getResource("font").getPath();
            /**
             * springbot项目
             */
//            new ClassPathResource("/font").getPath();

            bfSong = BaseFont.createFont(fontPath + "/song.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            bfSong9 = new Font(bfSong, 9,Font.NORMAL);
            bfSong14 = new Font(bfSong, 14);
            bfSong14Underline = new Font(bfSong, 14);
            bfSong14Underline.setStyle(4);
            bfSong14Strong = new Font(bfSong, 14);
            bfSong14Strong.setStyle(1);
            bfSong24 = new Font(bfSong, 24);
            bfSong16 = new Font(bfSong, 16);
            bfSong11 = new Font(bfSong, 11);
            bfHei = BaseFont.createFont(fontPath + "/hei.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            bfHei26 = new Font(bfHei, 26);
            bfHei22 = new Font(bfHei, 22);

            bfMsyh = BaseFont.createFont(fontPath + "/msyh.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            bfMsyh11 = new Font(bfMsyh, 11);
            bfMsyh36 = new Font(bfMsyh, 36);
            bfMsyh36Strong = new Font(bfMsyh, 36);
            bfMsyh36Strong.setStyle(1);
            bfMsyh20 = new Font(bfMsyh, 20);
            bfMsyh26 = new Font(bfMsyh, 26);
            bfMsyh15 = new Font(bfMsyh, 15);
            bfMsyh12 = new Font(bfMsyh, 12);
            bfMsyh12Gray = new Font(bfMsyh, 12);
            bfMsyh12Gray.setColor(new BaseColor(126,126,126) );
            bfMsyh14Strong = new Font(bfMsyh, 14);
            bfMsyh18Strong = new Font(bfMsyh, 18);
            bfMsyh18Strong.setStyle(1);
            bfMsyh24Strong = new Font(bfMsyh, 24);
            bfMsyh24Strong.setStyle(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
