package frame;

import java.net.*;
import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import frame.Login;

public class Enter extends Thread{
	public static String Ipconfig ()throws Exception{
	      String IP_ADDRESS=null;
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
	        IP_ADDRESS=temp[0]+"."+temp[1]+"."+temp[2]+"."+"255";
	        System.out.println(IP_ADDRESS);
	      return IP_ADDRESS;
	   }
	public String studentN=Login.id;
	//public String studentN="15054007";
	public boolean listener=true;
	public void run(){
		
		int port2=4000;
	      DatagramSocket ds2 = null;
		try {
			ds2 = new DatagramSocket();
		} catch (SocketException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}

	      InetAddress ia = null;
	   		try {
			ia = InetAddress.getByName(Ipconfig());
		} catch (Exception e1) {
			// TODO 자동 생성된 catch 블록
			e1.printStackTrace();
		}


	      
		/*try {
			ia = InetAddress.getByName("192.168.43.184");
		} catch (UnknownHostException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}*/

	  // 브로드캐스트용 주소로 학번을 전송한다 
	  while(listener){
		  byte[] data2=new byte[1024];
	   
		  data2=studentN.getBytes();
	    
	   // 123번 포트에서 감청하고 있는 모든 컴퓨터에 데이터를 전송한다.
		  DatagramPacket dp2 = new DatagramPacket(data2, data2.length, ia, port2);
	  // DatagramPacket dp2 = new DatagramPacket(data, data.length, ib, port);
		  try {
			ds2.send(dp2);
		} catch (IOException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		  System.out.println("전송 완료");
		  System.out.println("보낸 학번 : "+studentN);
	  // ds.send(dp2);
	  
	   try{
		   Thread.sleep(1000);
	   }catch(InterruptedException ie){}

	  
	  ///////////////////////////
	     
	  }ds2.close();
	}
  
}
