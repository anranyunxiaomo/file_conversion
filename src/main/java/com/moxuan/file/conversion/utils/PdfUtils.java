package com.moxuan.file.conversion.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PdfUtils {
    /**
     * @param imageFolderPath 图片文件夹地址
     * @param pdfPath         PDF文件保存地址
     */
    public static void toPdf(String imageFolderPath, String pdfPath) {
        try {
            // 图片文件夹地址
            // String imageFolderPath = "D:/Demo/ceshi/";
            // 图片地址
            String imagePath = null;
            // PDF文件保存地址
            // String pdfPath = "D:/Demo/ceshi/hebing.pdf";
            // 输入流
            FileOutputStream fos = new FileOutputStream(pdfPath);
            // 获取图片文件夹对象
            File file = new File(imageFolderPath);
            File[] files = file.listFiles();
            // 创建文档
            Document doc = new Document(null, 0, 0, 0, 0);
            //doc.open();
            // 写入PDF文档
            PdfWriter.getInstance(doc, fos);
            // 读取图片流
            BufferedImage img = null;
            // 实例化图片
            Image image = null;
            ArrayList<Image> images = new ArrayList<>();
            // 循环获取图片文件夹内的图片
            for (File file1 : files) {
                if (file1.getName().endsWith(".png")
                        || file1.getName().endsWith(".jpg")
                        || file1.getName().endsWith(".gif")
                        || file1.getName().endsWith(".jpeg")
                        || file1.getName().endsWith(".tif")) {
                    imagePath = imageFolderPath + file1.getName();
                    System.out.println(file1.getName());
                    // 读取图片流
                    img = ImageIO.read(new File(imagePath));
                    // 根据图片大小设置文档大小
                    doc.setPageSize(new Rectangle(img.getWidth(), img
                            .getHeight()));
                    // 实例化图片
                    image = Image.getInstance(imagePath);
                    images.add(image);
                }
            }
            // 添加图片到文档
            doc.open();
            for (Image image1 : images) {
                doc.add(image1);
            }
            // 关闭文档
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        toPdf("D:\\2022\\个人\\职场进阶-Java架构直通车2020-40周完结\\课程资料\\Java架构师pdf文件\\6周\\", "D:\\2022\\个人\\职场进阶-Java架构直通车2020-40周完结\\课程资料\\Java架构师pdf文件\\6周\\pdf\\hebing.pdf");
        long time2 = System.currentTimeMillis();
        int time = (int) ((time2 - time1) / 1000);
        System.out.println("执行了：" + time + "秒！");
    }
}
