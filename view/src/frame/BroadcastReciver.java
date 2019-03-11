package frame;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.KeyEvent;
 
/* 브로드캐스트 IP 주소에 속하는 컴퓨터에서 실행되는 수신측 프로그램*/
public class BroadcastReciver {
	

	private static boolean listener=true;
	public static void main(String[] args) throws Exception {
		int port=3000;
		DatagramSocket ds = new DatagramSocket(port);
		byte[] data = new byte[1024];
		
		DatagramPacket dp = new DatagramPacket(data, data.length);
		int len = 0;
		Robot robot = new Robot();
		while(listener) {
			ds.receive(dp);
			
			byte[] received = dp.getData();
			System.out.println(received[0]);
			if(received[0]==1){
				
				robot.setAutoDelay(10);
				int count=0;
				while(count<100){
	   	    
					robot.getAutoDelay();
					robot.mouseMove(0, 0);
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_A); 
						//robot.keyPress(KeyEvent.VK_BACK_SPACE);
						robot.getAutoDelay();
					 
						robot.keyRelease(KeyEvent.VK_BACK_SPACE);
						robot.keyRelease(KeyEvent.VK_ALT);
						robot.keyRelease(KeyEvent.VK_F4);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.keyRelease(KeyEvent.VK_ALT);
						robot.keyRelease(KeyEvent.VK_DELETE);
						robot.setAutoDelay(10);
						
						if(received[0]==0)break;
					
	   	   		
					count++;
	   	          
				}
	   	     
			 
			}
		 
		} 
	 try {
		 Thread.sleep(2000);
	 } catch (Exception e) {
	 }
	 ds.close();
 }
 
}



