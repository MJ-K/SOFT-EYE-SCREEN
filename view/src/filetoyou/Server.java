package filetoyou;

import java.io.*;
import java.net.*;

//�л� ����.(������ ��)
public class Server {
   private final static int PORT = 2016;
   private final static String IP="localhost";
   
   public static void main(String[] args)throws Exception{
      DataInputStream dis=null;
      
      
      System.out.println("���� ������ �����մϴ�.");
      
      File file=new File("C:\\Temp\\file\\filetokenize.txt");//������
      if(!file.exists()){
         System.out.println("�������� �������� �ʽ��ϴ١�");
         System.exit(-1);
      }
      DatagramSocket ds= new DatagramSocket();
      InetAddress ia=InetAddress.getByName(IP);
      
      String str="start";
      DatagramPacket dp=new DatagramPacket(str.getBytes(),str.getBytes().length,ia,PORT);
      ds.send(dp);
      
      
      dis=new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
      byte[] bit=new byte[65508];
      while(true){
         int num=dis.read(bit,0,bit.length);
         if(num==-1)break;
         dp=new DatagramPacket(bit,num,ia,PORT);
         ds.send(dp);
      }	
      
      
      str ="end";
      dp=new DatagramPacket(str.getBytes(),str.getBytes().length,ia,PORT);
      ds.send(dp);
      
      ds.close();
      System.out.println("���� �Ϸ�!!!!");
         
   }
}