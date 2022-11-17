package cn.cocowwy.javajdk.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class CreateImage {
    public static void main(String[] args) throws IOException {
            /**
             * 得到图片缓冲区
             * INT精确度达到一定,RGB三原色，高度280,宽度360
             */
            BufferedImage bi = new BufferedImage(360, 280, BufferedImage.TYPE_INT_RGB);

            //得到它的绘制环境(这张图片的笔)
            Graphics2D g2 = (Graphics2D) bi.getGraphics();

            //填充一个矩形 左上角坐标(0,0),宽70,高150;填充整张图片
            g2.fillRect(0, 0, 360, 280);
            //设置颜色
            g2.setColor(Color.BLACK);
            //填充整张图片(其实就是设置背景颜色)
            g2.fillRect(0, 0, 360, 280);

            //画边框
            g2.setColor(Color.RED);
            g2.drawRect(0, 0, 150 - 1, 70 - 1);

            //设置字体:字体、字号、大小
            g2.setFont(new Font("楷体", Font.BOLD, 18));
            //设置背景颜色
            g2.setColor(Color.white);

            //添加文字 xy坐标位置
            g2.drawString("你好啊~！", 10, 30);

            //保存图片 JPEG表示保存格式
            ImageIO.write(bi, "JPEG", new FileOutputStream("/Users/cocowwy/Desktop/test.jpeg"));

            System.out.println("成功！");
    }
}
