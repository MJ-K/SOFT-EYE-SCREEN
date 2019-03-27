
package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.DAO;

public class Login extends JFrame implements ActionListener, KeyListener, MouseListener{
   BufferedImage img = null;
   
   static JTextField logintext;
   JPasswordField passwdtext;
   JButton loginbutton;
   
   public ButtonGroup group = new ButtonGroup();
   String idStr;
   public static int id;
   public static String name;
   
   public static void main(String args[]){
	   new Login();
   }
   
   public Login(){
      setTitle("Soft-Eyescreen");
      setBounds(300, 70, 1350, 838);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setLayout(null);
      //�̹��� �޾ƿ���
      try{
         img = ImageIO.read(new File("img/background_font.png"));//����ȭ�� ����ֱ�
      } catch(IOException e){
    	  //System.out.println("������ �ҷ����� ����");
      }
      
      JLayeredPane layeredpane = new JLayeredPane();
      layeredpane.setBounds(0, 0, 1350, 850);
      layeredpane.setLayout(null);
      
      Mypanel panel = new Mypanel();
      panel.setBounds(0, 0, 1350, 850);      
     
      logintext = new JTextField();
      logintext.setDocument(new JTextFieldLimit(12));
      logintext.setBounds(520, 531, 270, 40);//������ ������ ���غ���
      logintext.setFont(new Font("����", Font.BOLD, 20));
      logintext.setOpaque(false);
      logintext.setForeground(Color.blue);
      logintext.setBorder(BorderFactory.createEmptyBorder());
      logintext.addKeyListener(this);
      logintext.setFocusTraversalKeysEnabled(false);
      
      passwdtext = new JPasswordField();
      passwdtext.setDocument(new JTextFieldLimit(20));
      passwdtext.setBounds(520, 593, 270, 40);//������ ������ ���غ���
      passwdtext.setFont(new Font("", Font.BOLD, 20));
      passwdtext.setOpaque(false);
      passwdtext.setForeground(Color.blue);
      passwdtext.setBorder(BorderFactory.createEmptyBorder());
      passwdtext.addKeyListener(this);
      passwdtext.setFocusTraversalKeysEnabled(false);
      
      layeredpane.add(logintext);
      layeredpane.add(passwdtext);

      loginbutton = new JButton(new ImageIcon("img/Login.png"));//�ȿ� �̹��� �־��ֱ�
      loginbutton.setBounds(844, 553, 180, 62);
      loginbutton.setBorder(BorderFactory.createEmptyBorder());
      layeredpane.add(loginbutton);
      loginbutton.addActionListener(this);
      loginbutton.addMouseListener(this);
      loginbutton.addKeyListener(this);
      loginbutton.setFocusTraversalKeysEnabled(false);
   
      layeredpane.add(panel);//�г�1�� ���̾ƿ��� �ֱ�
      
      add(layeredpane);
      setVisible(true);
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   class Mypanel extends JPanel{
      public void paint(Graphics g){
         g.drawImage(img, 0, 0, null);
      }
   }
   
   @Override
   public void actionPerformed(ActionEvent e){ 
	   loginCheck();
   }
   
   public void loginCheck() {
	   idStr = logintext.getText();
	   char[] secret_pw = passwdtext.getPassword();
	   String passwd = "";
	   for(char cha : secret_pw){ 
		   passwd += Character.toString(cha);
	   }
	   
	   if(idStr.equals("")) {
		   JOptionPane.showMessageDialog(null, "�й��� �Է����ּ���.");
	   }
	   else if(passwd.equals("")) {
		   JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���.");
	   }
	   else {
		   try {
			   id = Integer.parseInt(idStr);
			   
			   DAO dao = new DAO();
			   name = dao.getName(id);
			   
			   if(dao.isExist(id, passwd)) {
				   if(dao.getIsStudent(id) == 0) {
					   System.out.println("���� �α���");
					   try {
						   new Teacher();
					   } catch (Exception e) {
						   // TODO Auto-generated catch block
						   e.printStackTrace();
					   }
					   this.setVisible(false);
				   }
				   else if(dao.getIsStudent(id) == 1) {
					   System.out.println("�л� �α���");
					   dao.connect(id);
					   new Student();
					   this.setVisible(false);
				   }
				   else System.out.println("����ġ ���� ����");
			   }
			   else {
				   JOptionPane.showMessageDialog(null, "�й� Ȥ�� ��й�ȣ�� Ʋ���ϴ�.");
			   }
		   } catch(NumberFormatException e1) {
			   JOptionPane.showMessageDialog(null, "�߸��� �й� �����Դϴ�.");
		   }
	   }
   }

   @Override
   public void keyPressed(KeyEvent e) {
	   // TODO Auto-generated method stub
	   if(e.getKeyCode()==KeyEvent.VK_TAB && e.getSource()==logintext) {
		   passwdtext.requestFocus();
	   }
		
	   if(e.getKeyCode()==KeyEvent.VK_TAB && e.getSource()==passwdtext) {
		   logintext.requestFocus();
	   }
		
	   if(e.getKeyCode()==KeyEvent.VK_ENTER) {
		   loginCheck();
	   }
   }
	
   @Override
   public void keyReleased(KeyEvent e) {
	   // TODO Auto-generated method stub
	   
   }
	
   @Override
   public void keyTyped(KeyEvent e) {
	   // TODO Auto-generated method stub
	   
   }

   @Override
   public void mouseClicked(MouseEvent e) {
	   // TODO Auto-generated method stub
		
   }

   @Override
   public void mouseEntered(MouseEvent e) {
	   // TODO Auto-generated method stub
	   loginbutton.setIcon(new ImageIcon("img/Login����.png"));
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