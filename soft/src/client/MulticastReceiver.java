package client;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.KeyEvent;




class Control extends Thread{//키보드 마우스 제어 쓰레드
 
 public boolean stop = true;
// byte re =0 ;
 Robot robot = null;
// public Control (byte received){
//  this.re=received;
// }
 
 public void run(){
  
  try {
   robot = new Robot();
  } catch (AWTException e) {
   // TODO 자동 생성된 catch 블록
   e.printStackTrace();
  }

            robot.setAutoDelay(10);
         //   int count=0;
            while( stop ){
             System.out.println(stop);
//               robot.getAutoDelay();
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
//                  System.out.println("re:"+re);
//                  if(re==0)break;
                 
              //    count++;
            }
             
         
 }
}





public class MulticastReceiver {

  
   @SuppressWarnings("deprecation")
public static void main(String[] args) throws Exception {
      int port=3000;
      boolean listener=true;
      DatagramSocket ds = new DatagramSocket(port);
      byte[] data = new byte[1024];
      ds.setBroadcast(true);
      DatagramPacket dp = new DatagramPacket(data, data.length);
      byte[] received = null;
     
      Control control = null;
      while(listener) {
         ds.receive(dp);
         received = dp.getData();
           
         if(received[0] == 1)
         {//1을 받으면 실행
        	 //System.out.println("실행");
        	 control = new Control();
        	 control.start();
         }
         if(control != null && received[0] == 0)
         {//0을 받으면 쓰레드 스탑.
        	 //System.out.println("종료");
        	 control.stop = false;
         }
         
      }
     //System.out.println("꺼짐");
    ds.close();
 }
 
}






