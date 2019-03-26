
package frame;


import java.awt.AWTException;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import frame.MulticastReceiver;

public class Login extends JFrame implements ActionListener{
   
   private static final String System = null;

   BufferedImage img = null;
   
   static JTextField logintext;
   JButton loginbutton;
   JRadioButton teacher = new JRadioButton();
   JRadioButton student = new JRadioButton();
   public ButtonGroup group = new ButtonGroup();
   Mymouse listener = new Mymouse();
   public static String id;
   
   public static void main(String args[]){
	   new Login();
   }
   
   public Login(){
      setTitle("Soft-Eyescreen");
      setSize(1350,850);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setLayout(null);
      //이미지 받아오기
      try{
         img = ImageIO.read(new File("img/이게바로바탕이다.png"));//바탕화면 집어넣기
      }catch(IOException e){
    	  //System.out.println("파일을 불러오지 못함");
    	  
         
      }
      
      
      JLayeredPane layeredpane =new JLayeredPane();
      layeredpane.setBounds(0, 0, 1350, 850);
      layeredpane.setLayout(null);
      
   
      Mypanel panel = new Mypanel();
      panel.setBounds(0,0,1350,850);      
      
      //teacher,student 선택
            group.add(teacher);
            group.add(student);
            
            teacher.setBounds(580, 400, 20,20);
            teacher.setBorder(BorderFactory.createEmptyBorder());
            teacher.setOpaque(false);
            student.setBounds(810, 400, 20, 20);
            student.setBorder(BorderFactory.createEmptyBorder());
            student.setOpaque(false);
            
            layeredpane.add(teacher);
            layeredpane.add(student);
      
   
      logintext = new JTextField(40);
      logintext.setBounds(520, 530, 270, 40);//바탕에 일일이 다해보기
      logintext.setOpaque(false);
      logintext.setForeground(Color.blue);
      logintext.setBorder(BorderFactory.createEmptyBorder());
      
      layeredpane.add(logintext);

      loginbutton = new JButton(new ImageIcon("img/Login.png"));//안에 이미지 넣어주기
      loginbutton.setBounds(844,520, 180, 62);
      loginbutton.setBorder(BorderFactory.createEmptyBorder());
      layeredpane.add(loginbutton);
      loginbutton.addActionListener(this);
      loginbutton.addMouseListener(listener);
   
      layeredpane.add(panel);//패널1을 레이아웃에 넣기
      
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
	   id = logintext.getText();
//	   System.out.println(id);
       if( teacher.isSelected()){
           if (id.equals("")) {
               JOptionPane.showMessageDialog(null, "학번을 입력해주세요.");
           }
           else{
              try {
            	 Teacher tc = new Teacher();
            	
            	 this.setVisible(false);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
           }
       }
       if( student.isSelected()){
    	 
              if (id.equals("")) {
                  JOptionPane.showMessageDialog(null, "학번을 입력해주세요.");
              }
              else{
        
				try {
					new Student();
					this.setVisible(false);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
              
          }
       }
           
       else if(!teacher.isSelected()&&!student.isSelected())
          JOptionPane.showMessageDialog(null, "직업을 선택해주세요.");
   }


class Mymouse implements MouseListener{
      public void mouseEntered(MouseEvent e){
         loginbutton.setIcon(new ImageIcon("img/Login반전.png"));
      }

      @Override
      public void mouseClicked(MouseEvent arg0) {
         // TODO Auto-generated method stub
         
      }

      @Override
      public void mouseExited(MouseEvent e) {
         // TODO Auto-generated method stub
         loginbutton.setIcon(new ImageIcon("img/Login.png"));
      }

      @Override
      public void mousePressed(MouseEvent e) {
         // TODO Auto-generated method stub
         
      }

      @Override
      public void mouseReleased(MouseEvent e) {
         // TODO Auto-generated method stub
         
      }
   }

  
}