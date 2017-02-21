package wangs;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by WangS on 2017/2/22.
 * 生成带随机字母的图片
 */
public class RandomImageServlet extends javax.servlet.http.HttpServlet {

    public static final int WIDTH = 120;
    public static final int HEIGHT = 25;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        //1.设置背景色
        setBackGround(g);
        //2.设置边框
        setBorder(g);
        //3.画干扰线
        drawRandomLine(g);
        //4.写随机数
        drawRandomNum((Graphics2D)g);
        //5.图形写给浏览器
        ImageIO.write(image, "jpg", response.getOutputStream());
        //控制浏览器不要缓存
        response.setDateHeader("expires",-1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
    }

    private void setBackGround(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    private void setBorder(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect(1, 1, WIDTH-2, HEIGHT-2);
    }

    private void drawRandomLine(Graphics g) {
        g.setColor(Color.GREEN);
        for (int i = 0; i < 5; i++) {
            int x1 = new Random().nextInt(WIDTH);
            int y1 = new Random().nextInt(HEIGHT);

            int x2 = new Random().nextInt(WIDTH);
            int y2 = new Random().nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }

    }

    private void drawRandomNum(Graphics2D g) {
        g.setColor(Color.RED);
        g.setFont(new Font("宋体", Font.BOLD, 20));

        String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        int x = 5;
        for (int i = 0; i < 4; i++) {
            //保证每个字的旋转角度都在30°以内
            int degree = new Random().nextInt() % 30;
            String ch = base.charAt(new Random().nextInt(base.length())) + "";
            g.rotate(degree*Math.PI/180, x, 20); //设置旋转角度
            g.drawString(ch, x, 20);
            //恢复初始的正位置
            g.rotate(-degree*Math.PI/180, x, 20);
            x += 30;
        }

    }








}
