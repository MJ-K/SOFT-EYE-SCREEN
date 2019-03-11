package filetoyou;

import java.io.*;
import java.net.*;

//학생 입장.(보내는 측)
public class Server {
   private final static int PORT = 2016;
   private final static String IP="localhost";
   
   public static void main(String[] args)throws Exception{
      DataInputStream dis=null;
      
      
      System.out.println("파일 전송을 시작합니다.");
      
      File file=new File("C:\\Temp\\file\\filetokenize.txt");//절대경로
      if(!file.exists()){
         System.out.println("※파일이 존재하지 않습니다※");
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
      System.out.println("전송 완료!!!!");
         
   }
}