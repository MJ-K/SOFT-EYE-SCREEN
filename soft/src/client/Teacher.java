package client;


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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.DAO;

public class Teacher extends JFrame implements ActionListener{

	static int man = 0;

	BufferedImage img = null;
 
	int receiver;

	static String[] student_number = new String[3];
   
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
   	int teacher_number;
   	static int i = 0;
   	public static int[] number = new int[3];
   
   	static JLayeredPane layeredpane = new JLayeredPane();
  
   	static int j = 0;
   	static int num = 0;
   	EnterServer es= new EnterServer();
 
   	imageSender is=new imageSender();
 
   	Mymouse aa = new Mymouse();
   
   	JPanel screens = new JPanel();
   	int Xscreens,Yscreens;
   	screen[] sp = new screen[30];

   	public static void main(String args[])throws Exception{
   		new Teacher();
   	}

   	@SuppressWarnings("deprecation")
   	public Teacher() throws Exception {
   		setTitle("Teacher Screen");
   		setBounds(300, 70, 1350, 838);
   		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   		setLayout(null);
   		//이미지 받아오기
   		try{
   			img = ImageIO.read(new File("img/교수화면.png"));
   		}catch(IOException e){
   			System.out.println("이미지를 불러오지 못했습니다.");
   			System.exit(0);
   		}
      
   		JLayeredPane layeredpane = new JLayeredPane();
   		layeredpane.setBounds(0, 0, 1350, 800);
   		layeredpane.setLayout(null);
      
   		JLayeredPane layeredpane2 = new JLayeredPane();
   		layeredpane2.setBounds(0, 0, 1350, 800);
   		layeredpane2.setLayout(null);
   
   		Mypanel panel = new Mypanel();
   		panel.setBounds(0,0,1350,800);      
      
   		EnterServer ent=new EnterServer();
   		ent.start();
      
   		ArrayList<Integer> list = new DAO().getConnect();
	  
   		for(int i=0; i<list.size(); i++) {
   			ImageIcon image = new ImageIcon("img/asdf.png");
   			Image img = image.getImage();
   			screen = new JLabel(image);
   			screen.setOpaque(false);
   			screen.setBounds(42 + 182 * (i % 6), 49 + 149 * (i / 6), 100, 100);     
   			screen.setLayout(null);
   			ex = new JLabel(String.valueOf(list.get(i)));
   			ex.setFont(new Font("맑은 고딕",Font.BOLD,15));
   			ex.setForeground(Color.BLUE);
   			ex.setBounds(55 + 182 * (i % 6), 35 + 149 * (i / 6), 100, 100);
          
   			layeredpane.add(screen);
   			layeredpane.add(ex);
   		}
	  
   		Timer timer = new Timer();
   		TimerTask task = new TimerTask() {
   			@Override
   			public void run() {
   				// TODO Auto-generated method stub
   				ArrayList<Integer> li = new ArrayList<>();
   				
   				for(int i=0; i<layeredpane.getComponentCount(); i++) {
   					if(layeredpane.getComponent(i).getClass().toString().equals("class javax.swing.JLabel")) {
   						if(((JLabel)layeredpane.getComponent(i)).getText() != null) {
   							li.add(Integer.parseInt(((JLabel)layeredpane.getComponent(i)).getText()));
   						}
   					}
   		      	}
   				
   				ArrayList<Integer> l = new DAO().getConnect();
   				if(!l.equals(li)) {
   					layeredpane.removeAll();
    			  
   					for(int i=0; i<l.size(); i++) {
   						ImageIcon image = new ImageIcon("img/asdf.png");
   						Image img = image.getImage();
   						screen = new JLabel(image);
   						screen.setOpaque(false);
   						screen.setBounds(42 + 182 * (i % 6), 49 + 149 * (i / 6), 100, 100);     
   						screen.setLayout(null);
   						ex = new JLabel(String.valueOf(l.get(i)));
   						ex.setFont(new Font("맑은 고딕",Font.BOLD,15));
   						ex.setForeground(Color.BLUE);
   						ex.setBounds(55 + 182 * (i % 6), 35 + 149 * (i / 6), 100, 100);
    		          
   						layeredpane.add(screen);
   						layeredpane.add(ex);
   					}
    			  
   					layeredpane.add(panel);
    		      
   					layeredpane.revalidate();
   					layeredpane.repaint();
   				}
   			}
   		};
      
   		timer.schedule(task, 0, 5000);
   		
   		teacher_number = Login.id;
   		teacher = new JLabel (" " + teacher_number + " Hello!");
   		teacher.setFont(new Font("맑은 고딕", Font.BOLD,25));
   		teacher.setForeground(Color.WHITE);
   		teacher.setBounds(1102, 330, 224, 100);
   		layeredpane2.add(teacher);
     
   		teacherim = new JLabel(" 채팅 사용 방법");
   		teacherim.setFont(new Font("맑은 고딕", Font.BOLD,22));
     	teacherim.setForeground(Color.WHITE);
     	teacherim.setBounds(1102, 388, 224, 100);
     	layeredpane2.add(teacherim);
     
     	chatting1 = new JLabel("  1. 채팅 서버 버튼을");
     	chatting1.setFont(new Font("맑은 고딕", Font.BOLD,20));
     	chatting1.setForeground(Color.WHITE);
     	chatting1.setBounds(1102, 412, 224, 100);
     	layeredpane2.add(chatting1);
     
     	chatting3 = new JLabel("     눌러주세요.");
     	chatting3.setFont(new Font("맑은 고딕", Font.BOLD,20));
     	chatting3.setForeground(Color.WHITE);
     	chatting3.setBounds(1102, 436, 224, 100);
     	layeredpane2.add(chatting3);
     
     	chatting2= new JLabel("  2. 채팅 버튼을 ");
     	chatting2.setFont(new Font("맑은 고딕", Font.BOLD,20));
     	chatting2.setForeground(Color.WHITE);
     	chatting2.setBounds(1102, 460, 224, 100);
     	layeredpane2.add(chatting2);
     
     	chatting4= new JLabel("     눌러주세요.");
     	chatting4.setFont(new Font("맑은 고딕", Font.BOLD,20));
     	chatting4.setForeground(Color.WHITE);
     	chatting4.setBounds(1102, 482, 224, 100);
     	layeredpane2.add(chatting4);
            
     	openserver = new JButton(new ImageIcon("img/chatting server.png"));//안에 이미지 넣어주기
     	openserver.setBounds(1100, 31, 224, 57);
      	openserver.setBorder(BorderFactory.createEmptyBorder());
      	layeredpane2.add(openserver);
      	openserver.addActionListener(this);
      	openserver.addMouseListener(aa);

      	screenshare = new JButton(new ImageIcon("img/screenshare.png"));//안에 이미지 넣어주기
      	screenshare.setBounds(1100, 262, 224, 57);
      	screenshare.setBorder(BorderFactory.createEmptyBorder());
      	layeredpane2.add(screenshare);
      	screenshare.addActionListener(this);
      	screenshare.addMouseListener(aa);

      	control = new JButton(new ImageIcon("img/control.png"));//안에 이미지 넣어주기
      	control.setBounds(1100, 185, 224, 57);
      	control.setBorder(BorderFactory.createEmptyBorder());
      	layeredpane2.add(control);
      	control.addActionListener(this);
      	control.addMouseListener(aa);

      	chatting = new JButton(new ImageIcon("img/chatting.png"));//안에 이미지 넣어주기
      	chatting.setBounds(1100, 108, 224, 57);
      	chatting.setBorder(BorderFactory.createEmptyBorder());
      	layeredpane2.add(chatting);
      	chatting.addActionListener(this);
      	chatting.addMouseListener(aa);
   
      	logoutbutton = new JButton(new ImageIcon("img/Logout.png"));//안에 이미지 넣어주기
      	logoutbutton.setBounds(1100, 600, 224, 57);
      	logoutbutton.setBorder(BorderFactory.createEmptyBorder());
      	layeredpane2.add(logoutbutton);
      	logoutbutton.addActionListener(this);
      	logoutbutton.addMouseListener(aa);
      
      	QA_button = new JButton(new ImageIcon("img/Q_A교수.png"));//안에 이미지 넣어주기
      	QA_button.setBounds(1100, 677, 224, 57);
      	QA_button.setBorder(BorderFactory.createEmptyBorder());
      	layeredpane2.add(QA_button);
      	QA_button.addActionListener(this);
      	QA_button.addMouseListener(aa);
   
      	layeredpane.add(panel);//패널1을 레이아웃에 넣기
      
      	add(layeredpane2);
      	add(layeredpane);
      
      	setVisible(true);
   
      	setDefaultCloseOperation(EXIT_ON_CLOSE);
   	}
   
   	class Mypanel extends JPanel{
   		public void paint(Graphics g){
   			g.drawImage(img, 0, 0, null);
   		}
   	}
   
   @Override
   public void actionPerformed(ActionEvent e){
      if(e.getSource() == control){  
    	  if((j%2)==0)
    	  {   
    		  JOptionPane.showMessageDialog(null, "마우스, 키보드 제어를 시작합니다.");
    	  }
    	  else 
    	  {
    		  JOptionPane.showMessageDialog(null, "마우스, 키보드 제어를 종료합니다.");
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