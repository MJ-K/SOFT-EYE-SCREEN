package filetoyou;


import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

//������ ����.(�޴���)
public class Client {
   private final static int PORT = 2016;
   private final static String IP="localhost";
   public static void main(String[] args)throws Exception{
      DatagramSocket soc=new DatagramSocket(PORT);
      System.out.println("���� �غ� �Ϸ�!");
      
      File file=null;
      File newfile=null;
      DataOutputStream dos=null;
      
      while(true){
         DatagramPacket dp=new DatagramPacket(new byte[65508],65508);
         soc.receive(dp);
         
         String str=new String(dp.getData()).trim();
         if(str.equals("start")){
            System.out.println("������");   
            file =new File("number_file.txt");
            dos=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file,true)));
       
         }
      
         else if(str.equals("end")){
            System.out.println("���� �Ϸ�!!");
            
            break;
         }
         else if(file!=null){
           BufferedWriter writer = new BufferedWriter(new FileWriter("number_file.txt"+".", true));
           dos.write(str.getBytes(),0,str.getBytes().length);
           writer.close();
         }
      }
      dos.close();
   }
}