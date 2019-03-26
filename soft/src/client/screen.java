package client;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class screen extends JPanel {
     
     
    private BufferedImage img = null;
    private int num;
     
    public screen(int num) {
        this.num = num;
        img("sadaf");
        setLayout(null);
 
        JPanel panImg = new InnerPanel();
        panImg.setBounds(0, 0, 100, 100);
        panImg.setOpaque(false);
                 
        add(panImg);
        setVisible(true);
        setOpaque(false);
        setFocusable(true);
    }
 
    public static void main(String[] args) {
        JFrame frameTest = new JFrame();
        frameTest.setTitle("screen");
        frameTest.add(new screen(1));
        frameTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTest.setSize(99, 144);
        frameTest.setVisible(true);
    }
 
    class InnerPanel extends JPanel {
    private static final long serialVersionUID = 1547128190348749556L;
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(img, 0, 0, null);
        }
    }
 
    public void img(String filename) {
        // 이미지 받아오기 - gameOn, gameOff (로그인, 로그오프)
        try {
            img = ImageIO.read(new File("img/" + filename + ".png"));
        } catch (IOException e) {
            System.out.println("이미지 불러오기 실패!");
            System.exit(0);
        }
        repaint();
    }
}