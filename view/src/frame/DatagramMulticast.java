package frame;

import java.net.*;
import java.util.StringTokenizer;
import java.io.*;
import frame.Teacher;
/* ��ε�ĳ��Ʈ�� IP �ּҸ� �̿��Ͽ� 192.168.0.8.* �� ���ϴ� ��� ��ǻ�Ϳ��� �����͸� �����ϴ� ��
* �Ϸ��� ���ڸ� ������ �� ��ε�ĳ��Ʈ�� �ּҷ� �����Ͽ� ��û�ϰ� �ִ� ��� ��ǻ�Ϳ���
* ������ �� �ֵ��� �Ѵ�.
*/
public class DatagramMulticast {
	public static String Ipconfig ()throws Exception{
	      String IP_ADDRESS="127.0.0.1";
	      InetAddress ipAddress=null;
	      ipAddress = InetAddress.getLocalHost() ;
	        
	        
	        StringTokenizer st = new StringTokenizer (ipAddress.getHostAddress(), ".");
	        String[] temp = new String[3];
	        for(int i=0 ;i<3;i++){
	           temp[i]="";
	        }
	        for(int i=0 ;i<3;i++){
	           temp[i]= st.nextToken();
	        }
	        //IP_ADDRESS=temp[0]+"."+temp[1]+"."+temp[2]+"."+"255";
	        System.out.println(IP_ADDRESS);
	      return IP_ADDRESS;
	   }
 public static void main(String args[]) throws Exception {
  int port=3000;
  DatagramSocket ds = new DatagramSocket();
  

  InetAddress ia = InetAddress.getByName(Ipconfig());

  
  //InetAddress ib = InetAddress.getByName("192.168.1.1");
  int number=Teacher.j%2;
 /* ��ε�ĳ��Ʈ�� �ּҷ� �Ϸ��� ���ڸ� �����Ѵ�*/ 
  for(int i=number;i<(number+1);i++) {
   byte[] data = {(byte)i};
    
   // 123�� ��Ʈ���� ��û�ϰ� �ִ� ��� ��ǻ�Ϳ� �����͸� �����Ѵ�.
   DatagramPacket dp = new DatagramPacket(data, data.length, ia, port);
  // DatagramPacket dp2 = new DatagramPacket(data, data.length, ib, port);
   ds.send(dp);
   System.out.println("���� �Ϸ�");
   System.out.println("���� ���� : "+i);
  // ds.send(dp2);
  }
   try{
    Thread.sleep(1000);
   }catch(InterruptedException ie){}
  
  ds.close();
 }
}