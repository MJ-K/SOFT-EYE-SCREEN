package frame;

import java.net.*;
import java.util.StringTokenizer;
import java.io.*;
import frame.Teacher;
/* 브로드캐스트용 IP 주소를 이용하여 192.168.0.8.* 에 속하는 모든 컴퓨터에게 데이터를 전송하는 예
* 일련의 숫자를 전송할 때 브로드캐스트용 주소로 전송하여 감청하고 있는 모든 컴퓨터에서
* 수신할 수 있도록 한다.
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
 /* 브로드캐스트용 주소로 일련의 숫자를 전송한다*/ 
  for(int i=number;i<(number+1);i++) {
   byte[] data = {(byte)i};
    
   // 123번 포트에서 감청하고 있는 모든 컴퓨터에 데이터를 전송한다.
   DatagramPacket dp = new DatagramPacket(data, data.length, ia, port);
  // DatagramPacket dp2 = new DatagramPacket(data, data.length, ib, port);
   ds.send(dp);
   System.out.println("전송 완료");
   System.out.println("보낸 숫자 : "+i);
  // ds.send(dp2);
  }
   try{
    Thread.sleep(1000);
   }catch(InterruptedException ie){}
  
  ds.close();
 }
}