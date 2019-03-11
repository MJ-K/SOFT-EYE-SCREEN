package frame;


import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import frame.screen;
import frame.Login;
import frame.DatagramMulticast;
import frame.DatagramBroadcast;
import frame.EnterServer;

import chat.server_frame;
import chat.client_frame;
public class Teacher extends JFrame implements ActionListener{
	////////////////
	//static int counter1=0;
	static int man=0;
	
	///////////////
   BufferedImage img = null;
 
   int receiver;

   static String[] student_number=new String[3];
   
   JButton logoutbutton;
   JButton QA_button;
   JButton openserver;
   JButton control;
   JButton chatting;
   JButton screenshare;
   JButton enter;
   JLabel teacherim;
   JLabel teacher;
   JLabel st_number;
   JLabel chatting1;
   JLabel chatting2;
   JLabel chatting3;
   JLabel chatting4;
   static JLabel ex;
   static JLabel ex1;
   static JLabel ex2;
   static JLabel screen;
   String teacher_number;
   static int i = 0;
   public static int[] number=new int[3];
   
   static JLayeredPane layeredpane =new JLayeredPane();
  
   
   static int j = 0;
   static int num=0;
   EnterServer es= new EnterServer();
 
   imageSender is=new imageSender();

 
   Mymouse aa = new Mymouse();
   
   JPanel screens = new JPanel();
   int Xscreens,Yscreens;
   screen[] sp = new screen[30];
   
/*   public String[] studentnum(){
      for(int l=0;l<30;l++){
         if(student_number[l]!=null){
            student_number[l+1]=Student.studentN;
         }
         else{
            student_number[l]=Student.studentN;
         }
         System.out.println(student_number[l]);
         
      }
      
      
      return student_number;
   }*/

   public static void main(String args[])throws Exception{
      
      new Teacher();
     /* EnterServer ent=new EnterServer();
      ent.start();*/
      
      
      
     /* int port=4000;
      boolean listener=true;
      DatagramSocket ds = null;
     try {
        ds = new DatagramSocket(port);
     } catch (SocketException e) {
        // TODO 자동 생성된 catch 블록
        e.printStackTrace();
     }
      try {
        ds.setBroadcast(true);
     } catch (SocketException e) {
        // TODO 자동 생성된 catch 블록
        e.printStackTrace();
     }
     
      //studnet_number
      int num=0;
      
      while(listener) {
         if(num>2) num=0;
         int temp=0;
         
         String sn = null;
         byte[] data = new byte[1024]; 
         DatagramPacket dp = new DatagramPacket(data, data.length);
         try {
        ds.receive(dp);
     } catch (IOException e) {
        // TODO 자동 생성된 catch 블록
        e.printStackTrace();
     }
         sn = new String(dp.getData()).trim();
        //System.out.println(sn);
        
//         for(int l=0;l<3;l++){
//            student_number[l]=null;
//         }
         //if(sn=="1")sn=null;
            
            student_number[num]=sn;
//            
//            if(l>0){
//               for(int z=0;z<l;z++){//같은 수 있으면 넘기기.
//             if(student_number[z].equals(student_number[l])){
//                student_number[z]=sn; 
//                    }
//              }
//            }
//           if(student_number[l]!=null&&!(student_number[l].equals(sn))){
//                  student_number[l+1]=sn;
//              }
//           
          
        
        //if(student_number[j].equals("1"))student_number[l]=null;           
                 for(int l=0;l<3;l++){
                    number[l]=0;
                 }
//           }
        num++;
        
       // String max=student_number[0];
        if(num==3){
           
           number[0]=Integer.valueOf(student_number[0]);
           number[1]=Integer.valueOf(student_number[1]);
           number[2]=Integer.valueOf(student_number[2]);
           for(int q=0;q<3;q++){
              for(int p=0;p<3;p++){
                 if(number[q]<number[p]){
                    temp=number[q];
                    number[q]=number[p];
                    number[p]=temp;
                    }
                 }
              
              }
              for(int p=0;p<2;p++){
                if(number[p]==number[p+1]){
                   number[p+1]=0;
                   if(number[1]==0){
                      temp=number[1];
                      number[1]=number[2];
                      number[2]=temp;
                      if(number[0]==number[1]) number[1]=0;
                   }
                }
             }
           
              System.out.println("number : "+number[0]+","+number[1]+","+number[2]);
           }
           // System.out.println(student_number[0]+","+student_number[1]+","+student_number[2]);
        
        if(number[0]!=0){
            ImageIcon image = new ImageIcon("img/asdf.png");
             Image img = image.getImage();
             screen = new JLabel(image);
             screen.setOpaque(false);
             screen.setBounds(42, 49, 100, 100);     
             screen.setLayout(null);
             ex = new JLabel(String.valueOf(number[0]));
             ex.setFont(new Font("맑은 고딕",Font.BOLD,15));
             ex.setForeground(Color.BLUE);
             ex.setBounds(49, 30, 100,100);
             screen.setVisible(true);
             ex.setVisible(true);
             
             layeredpane.add(screen);
             layeredpane.add(ex);
            
         }
         }
         
         ds.close();
      */
      /*ImageIcon image2;

      
      
  	//int	man=Show(EnterServer.counter);
  
        switch(man){
        	case 3:
        		image2= new ImageIcon("img/asdf.png");
        		Image img2 = image2.getImage();
        		screen = new JLabel(image2);
        		screen.setOpaque(false);
        		screen.setBounds(407, 49, 100, 100);     
        		screen.setLayout(null);
        		ex2 = new JLabel("1505400?");
                ex2.setFont(new Font("맑은 고딕",Font.BOLD,15));
                ex2.setForeground(Color.BLUE);
                ex2.setBounds(49, 30, 100,100);
        		layeredpane.add(screen);
        		layeredpane.add(ex2);   
            case 2:
                 ImageIcon image1 = new ImageIcon("img/asdf.png");
                  Image img1 = image1.getImage();
                  screen = new JLabel(image1);
                  screen.setOpaque(false);
                  screen.setBounds(225, 49, 100, 100);     
                  screen.setLayout(null);
                  ex1 = new JLabel("15054053");
                  ex1.setFont(new Font("맑은 고딕",Font.BOLD,15));
                  ex1.setForeground(Color.BLUE);
                  ex1.setBounds(231, 30, 100,100);
                  
                    layeredpane.add(screen);
                    layeredpane.add(ex1);

            case 1:
                ImageIcon image = new ImageIcon("img/asdf.png");
                 Image img = image.getImage();
                 screen = new JLabel(image);
                 screen.setOpaque(false);
                 screen.setBounds(42, 49, 100, 100);     
                 screen.setLayout(null);
                 ex = new JLabel("15054007");
                 ex.setFont(new Font("맑은 고딕",Font.BOLD,15));
                 ex.setForeground(Color.BLUE);
                 ex.setBounds(49, 30, 100,100);
                 

                   layeredpane.add(screen);
                   layeredpane.add(ex);
                   break;
               
               
            }*/
  	
       }
  	
   


   
   
   @SuppressWarnings("deprecation")
public Teacher() throws Exception {
	   
	   
      setTitle("Teacher Screen");
      setSize(1350,850);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setLayout(null);
      //이미지 받아오기
      try{
         img = ImageIO.read(new File("img/교수화면.png"));
      }catch(IOException e){
         System.out.println("이미지를 불러오지 못했습니다.");
         System.exit(0);
      }
      

      
      JLayeredPane layeredpane =new JLayeredPane();
      layeredpane.setBounds(0, 0, 1350, 800);
      layeredpane.setLayout(null);
  
           
   
      Mypanel panel = new Mypanel();
      panel.setBounds(0,0,1350,800);      

      String a=new String();
      a=String.valueOf(EnterServer.number[0]);
      
      ImageIcon image2;

          
     // int man=2;
	//int	man=EnterServer.Show(EnterServer.counter);
      
      EnterServer ent=new EnterServer();
      ent.start();
      
      ImageIcon image1 = new ImageIcon("img/asdf.png");
      Image img1 = image1.getImage();
      screen = new JLabel(image1);
      screen.setOpaque(false);
      screen.setBounds(225, 49, 100, 100);     
      screen.setLayout(null);
      ex1 = new JLabel("15054053");
      ex1.setFont(new Font("맑은 고딕",Font.BOLD,15));
      ex1.setForeground(Color.BLUE);
      ex1.setBounds(231, 30, 100,100);
      
      layeredpane.add(screen);
      layeredpane.add(ex1);

      
        ImageIcon image = new ImageIcon("img/asdf.png");
        Image img = image.getImage();
        screen = new JLabel(image);
        screen.setOpaque(false);
        screen.setBounds(42, 49, 100, 100);     
        screen.setLayout(null);
        ex = new JLabel("15054012");
        ex.setFont(new Font("맑은 고딕",Font.BOLD,15));
        ex.setForeground(Color.BLUE);
        ex.setBounds(49, 30, 100,100);
        

        layeredpane.add(screen);
        layeredpane.add(ex);
        
    /*  switch(ent.counter){
      	case 3:
      		image2= new ImageIcon("img/asdf.png");
      		Image img2 = image2.getImage();
      		screen = new JLabel(image2);
      		screen.setOpaque(false);
      		screen.setBounds(407, 49, 100, 100);     
      		screen.setLayout(null);
      		ex2 = new JLabel("1505400?");
              ex2.setFont(new Font("맑은 고딕",Font.BOLD,15));
              ex2.setForeground(Color.BLUE);
              ex2.setBounds(49, 30, 100,100);
      		layeredpane.add(screen);
      		layeredpane.add(ex2);   
          case 2:
               ImageIcon image1 = new ImageIcon("img/asdf.png");
                Image img1 = image1.getImage();
                screen = new JLabel(image1);
                screen.setOpaque(false);
                screen.setBounds(225, 49, 100, 100);     
                screen.setLayout(null);
                ex1 = new JLabel("15054053");
                ex1.setFont(new Font("맑은 고딕",Font.BOLD,15));
                ex1.setForeground(Color.BLUE);
                ex1.setBounds(231, 30, 100,100);
                
                  layeredpane.add(screen);
                  layeredpane.add(ex1);

          case 1:
              ImageIcon image = new ImageIcon("img/asdf.png");
               Image img = image.getImage();
               screen = new JLabel(image);
               screen.setOpaque(false);
               screen.setBounds(42, 49, 100, 100);     
               screen.setLayout(null);
               ex = new JLabel("15054007");
               ex.setFont(new Font("맑은 고딕",Font.BOLD,15));
               ex.setForeground(Color.BLUE);
               ex.setBounds(49, 30, 100,100);
               

                 layeredpane.add(screen);
                 layeredpane.add(ex);
                 break;
             
             
          }
      */
      
   /*switch(EnterServer.number[i]){
      case 0:
           ImageIcon image = new ImageIcon("img/asdf.png");
            Image img = image.getImage();
            screen = new JLabel(image);
            screen.setOpaque(false);
            screen.setBounds(42, 49, 100, 100);     
            screen.setLayout(null);
            ex = new JLabel(a);
            ex.setFont(new Font("맑은 고딕",Font.BOLD,15));
            ex.setForeground(Color.BLUE);
            ex.setBounds(49, 30, 100,100);
            

              layeredpane.add(screen);
              layeredpane.add(ex);
      case 1:
           ImageIcon image1 = new ImageIcon("img/asdf.png");
            Image img1 = image1.getImage();
            screen = new JLabel(image1);
            screen.setOpaque(false);
            screen.setBounds(42, 49, 100, 100);     
            screen.setLayout(null);

              layeredpane.add(screen);
         
         
      }
*/
    

    
     
     teacher_number = Login.id;
     teacher = new JLabel (teacher_number+" Hello!");
     teacher.setFont(new Font("맑은 고딕",Font.BOLD,25));
     teacher.setForeground(Color.WHITE);
     teacher.setBounds(1102,330,224,100);
     layeredpane.add(teacher);
     
     st_number = new JLabel ("학생 수 :");
     st_number.setFont(new Font("맑은 고딕",Font.BOLD,25));
     st_number.setForeground(Color.WHITE);
     st_number.setBounds(1102,358,224,100);
     layeredpane.add(st_number);
     
     teacherim = new JLabel("채팅 사용 방법");
     teacherim.setFont(new Font("맑은 고딕",Font.BOLD,22));
     teacherim.setForeground(Color.WHITE);
     teacherim.setBounds(1102,388,224,100);
     layeredpane.add(teacherim);
     
     chatting1 = new JLabel("1.채팅 서버 버튼을");
     chatting1.setFont(new Font("맑은 고딕",Font.BOLD,20));
     chatting1.setForeground(Color.WHITE);
     chatting1.setBounds(1102,412,224,100);
     layeredpane.add(chatting1);
     
     chatting3 = new JLabel("눌러주세요");
     chatting3.setFont(new Font("맑은 고딕",Font.BOLD,20));
     chatting3.setForeground(Color.WHITE);
     chatting3.setBounds(1102,436,224,100);
     layeredpane.add(chatting3);
     
     chatting2= new JLabel("2.채팅 버튼을 ");
     chatting2.setFont(new Font("맑은 고딕",Font.BOLD,20));
     chatting2.setForeground(Color.WHITE);
     chatting2.setBounds(1102,460,224,100);
     layeredpane.add(chatting2);
     
     chatting4= new JLabel("눌러주세요");
     chatting4.setFont(new Font("맑은 고딕",Font.BOLD,20));
     chatting4.setForeground(Color.WHITE);
     chatting4.setBounds(1102,482,224,100);
     layeredpane.add(chatting4);
    
            
      openserver = new JButton(new ImageIcon("img/chatting server.png"));//안에 이미지 넣어주기
      openserver.setBounds(1100,31, 224, 57);
      openserver.setBorder(BorderFactory.createEmptyBorder());
      layeredpane.add(openserver);
      openserver.addActionListener(this);
      openserver.addMouseListener(aa);

      screenshare = new JButton(new ImageIcon("img/screenshare.png"));//안에 이미지 넣어주기
      screenshare.setBounds(1100,262, 224, 57);
      screenshare.setBorder(BorderFactory.createEmptyBorder());
      layeredpane.add(screenshare);
      screenshare.addActionListener(this);
      screenshare.addMouseListener(aa);

      
      control = new JButton(new ImageIcon("img/control.png"));//안에 이미지 넣어주기
      control.setBounds(1100,185, 224, 57);
      control.setBorder(BorderFactory.createEmptyBorder());
      layeredpane.add(control);
      control.addActionListener(this);
      control.addMouseListener(aa);
      

      chatting = new JButton(new ImageIcon("img/chatting.png"));//안에 이미지 넣어주기
      chatting.setBounds(1100,108, 224, 57);
      chatting.setBorder(BorderFactory.createEmptyBorder());
      layeredpane.add(chatting);
      chatting.addActionListener(this);
      chatting.addMouseListener(aa);

   
      logoutbutton = new JButton(new ImageIcon("img/Logout.png"));//안에 이미지 넣어주기
      logoutbutton.setBounds(1100,600, 224, 57);
      logoutbutton.setBorder(BorderFactory.createEmptyBorder());
      layeredpane.add(logoutbutton);
      logoutbutton.addActionListener(this);
      logoutbutton.addMouseListener(aa);

      
      QA_button = new JButton(new ImageIcon("img/Q_A교수.png"));//안에 이미지 넣어주기
      QA_button.setBounds(1100,677, 224, 57);
      QA_button.setBorder(BorderFactory.createEmptyBorder());
      layeredpane.add(QA_button);
      QA_button.addActionListener(this);
      QA_button.addMouseListener(aa);
      


   
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
      
  
      if(e.getSource() == control){
  
    	  if((j%2)==0)
    	  {   
    		  JOptionPane.showMessageDialog(null, "마우스,키보드 제어를 시작합니다.");
    	  }
    	  else 
    	  {
    		  JOptionPane.showMessageDialog(null, "마우스,키보드 제어를 종료합니다.");
    	  }
       
          try {
                j++;
             
                DatagramBroadcast.main(null);
                 System.out.println("count:"+j);
             
      } catch (Exception e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
          }
     
      
       if(e.getSource() == logoutbutton)
       {
          JOptionPane.showMessageDialog(null, "시스템을 종료 합니다.");
             System.exit(0);
          
       }
       if(e.getSource() == screenshare)
       {   
      try{
         is.start();
      }catch(Exception e3){
         e3.printStackTrace();
      }
//        imageReceiver.start();
       }
       
       if(e.getSource() == QA_button)
       { 
          Q_A.main(null);
          }
       if(e.getSource() == openserver)
       {
          JOptionPane.showMessageDialog(null, "채팅서버를 엽니다");
          try {
         server_frame.main(null);
      } catch (Exception e2) {
         // TODO 자동 생성된 catch 블록
         e2.printStackTrace();
      }
         
       }

       if(e.getSource() == chatting)   
       {
          try{
             client_frame.main(null);
          }catch (Exception e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
        
             
          
       }
      }
   class Mymouse implements MouseListener{
         public void mouseEntered(MouseEvent e){
            if(e.getSource() == openserver){            
               openserver.setIcon(new ImageIcon("img/chatting server반전.png"));
}
            if(e.getSource() == chatting){           
               chatting.setIcon(new ImageIcon("img/chatting반전.png"));
}
            if(e.getSource() == control){            
               control.setIcon(new ImageIcon("img/control반전.png"));
}
            if(e.getSource() == screenshare){            
               screenshare.setIcon(new ImageIcon("img/screenshare반전.png"));
}

            if(e.getSource() == logoutbutton){            
               logoutbutton.setIcon(new ImageIcon("img/Logout반전.png"));          
}

            if(e.getSource() == QA_button){           
               QA_button.setIcon(new ImageIcon("img/QA반전.png"));
}


         }

         @Override
         public void mouseClicked(MouseEvent arg0) {
            // TODO Auto-generated method stub
            
         }

         @Override
         public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            if(e.getSource() == openserver)
               {
               openserver.setIcon(new ImageIcon("img/chatting server.png"));
               }
            if(e.getSource() == chatting)
               {
               chatting.setIcon(new ImageIcon("img/chatting.png"));
               }
            if(e.getSource() == control)
               {
               control.setIcon(new ImageIcon("img/control.png"));
               }
            if(e.getSource() == screenshare)
               {
               screenshare.setIcon(new ImageIcon("img/screenshare.png"));
               }
            if(e.getSource() == logoutbutton)
               {
               logoutbutton.setIcon(new ImageIcon("img/Logout.png"));
               }
               if(e.getSource() == QA_button)
               {
                  QA_button.setIcon(new ImageIcon("img/Q_A교수.png"));
               }

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