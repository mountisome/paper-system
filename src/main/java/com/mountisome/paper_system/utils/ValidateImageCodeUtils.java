package com.mountisome.paper_system.utils;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ValidateImageCodeUtils {

    // 随机产生颜色值
    public static Color getColor() {
        Random ran = new Random();
        int r = ran.nextInt(256);
        int g = ran.nextInt(256);
        int b = ran.nextInt(256);
        return new Color(r, g, b);
    }

    // 产生验证码
    public static String getNum() {
        int ran = (int) (Math.random() * 9000) + 1000;
        return String.valueOf(ran);
    }

    public static BufferedImage createImage(HttpSession session) {
        // 绘制验证码
        BufferedImage image = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
        // 画笔
        Graphics graphics = image.getGraphics();
        graphics.fillRect(0, 0, 80, 30);
        // 绘制干扰线条
        for(int i = 1; i < 60; i++) {
            Random ran = new Random();
            int xBegin = ran.nextInt(80);
            int yBegin = ran.nextInt(30);

            int xEnd = ran.nextInt(xBegin + 10);
            int yEnd = ran.nextInt(yBegin + 10);

            graphics.setColor(getColor());
            graphics.drawLine(xBegin, yBegin, xEnd, yEnd);
        }

        graphics.setFont(new Font("seif", Font.BOLD, 20));

        // 绘制验证码
        graphics.setColor(Color.BLACK);
        String checkCode = getNum();
        StringBuffer buffer = new StringBuffer();
        for(int i = 0 ; i < checkCode.length(); i++) {
            buffer.append(checkCode.charAt(i) + " " );
        }

        // 绘制验证码
        graphics.drawString(buffer.toString(), 20, 20);

        // 将验证码真实值保存
        session.setAttribute("checkCode", checkCode);

        graphics.dispose();

        return image;
    }

}
