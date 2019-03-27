package client;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

public class Q_A extends JFrame implements ActionListener{
   
   BufferedImage img = null;
   
   JButton Cafebutton;


   public static void main(String args[]){
      new Q_A();
      
   }
   
   public Q_A(){
      setTitle("Q_A");
      setSize(1350,800);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setLayout(null);
      //�̹��� �޾ƿ���
      try{
         img = ImageIO.read(new File("img/���ǻ���.png"));
      }catch(IOException e){
         System.out.println("�̹����� �ҷ����� ���߽��ϴ�.");
         System.exit(0);
      }
      
      
      JLayeredPane layeredpane =new JLayeredPane();
      layeredpane.setBounds(0, 0, 1350, 800);
      layeredpane.setLayout(null);
      
   
      Mypanel panel = new Mypanel();
      panel.setBounds(0,0,1350,800);      
   

      
      Cafebutton = new JButton(new ImageIcon("img/Cafe.png"));//�ȿ� �̹��� �־��ֱ�
      Cafebutton.setBounds(80, 403, 1163, 83);
      Cafebutton.setBorder(BorderFactory.createEmptyBorder());
      layeredpane.add(Cafebutton);
      Cafebutton.addActionListener(this);
   
      layeredpane.add(panel);//�г�1�� ���̾ƿ��� �ֱ�
      
      add(layeredpane);
      setVisible(true);
   }
   class Mypanel extends JPanel{
      public void paint(Graphics g){
         g.drawImage(img,0,0,null);
      }
   }
   
   @Override
   public void actionPerformed(ActionEvent e){
      
       if(e.getSource() == Cafebutton)
       {
          
         try{ Desktop d = Desktop.getDesktop();
          d.browse(new URI("http://cafe.naver.com/softeyescreen"));
       }catch(URISyntaxException e1){} catch (IOException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
       }
 }
   
}