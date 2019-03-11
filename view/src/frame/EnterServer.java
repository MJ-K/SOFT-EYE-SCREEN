package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import frame.Teacher;

public class EnterServer extends Thread{
	public static int counter1;
	public static int Show(int count)throws Exception{
		counter1=count;
		return counter1;
		 
	 }
//	 JLabel ex;
//	 JLabel screen;
	static int counter=0;
	public static int[] number=new int[3];
	String[] student_number=new String[3];
	public void run(){
		 int port=4000;
	      boolean listener=true;
	      DatagramSocket ds = null;
	     try {
	        ds = new DatagramSocket(port);
	     } catch (SocketException e) {
	        // TODO 자동 생성된 catch 블록
	        e.printStackTrace();
	     }
	      try {
	        ds.setBroadcast(true);
	     } catch (SocketException e) {
	        // TODO 자동 생성된 catch 블록
	        e.printStackTrace();
	     }
	     
	      //studnet_number
	      int num=0;
	      
	      while(listener) {
	    	  counter=0;
	         if(num>2) num=0;
	         int temp=0;
	         
	         String sn = null;
	         byte[] data = new byte[1024]; 
	         DatagramPacket dp = new DatagramPacket(data, data.length);
	         try {
	        ds.receive(dp);
	     } catch (IOException e) {
	        // TODO 자동 생성된 catch 블록
	        e.printStackTrace();
	     }
	         sn = new String(dp.getData()).trim();
	        //System.out.println(sn);
	        
//	         for(int l=0;l<3;l++){
//	            student_number[l]=null;
//	         }
	         //if(sn=="1")sn=null;
	            
	            student_number[num]=sn;
//	            
//	            if(l>0){
//	               for(int z=0;z<l;z++){//같은 수 있으면 넘기기.
//	             if(student_number[z].equals(student_number[l])){
//	                student_number[z]=sn; 
//	                    }
//	              }
//	            }
//	           if(student_number[l]!=null&&!(student_number[l].equals(sn))){
//	                  student_number[l+1]=sn;
//	              }
//	           
	          
	        
	        //if(student_number[j].equals("1"))student_number[l]=null;           
	                 for(int l=0;l<3;l++){
	                    number[l]=0;
	                 }
//	           }
	        num++;
	        
	       // String max=student_number[0];
	        if(num==3){
	           
	           number[0]=Integer.valueOf(student_number[0]);
	           number[1]=Integer.valueOf(student_number[1]);
	           number[2]=Integer.valueOf(student_number[2]);
	           for(int q=0;q<3;q++){
	              for(int p=0;p<3;p++){
	                 if(number[q]<number[p]){
	                    temp=number[q];
	                    number[q]=number[p];
	                    number[p]=temp;
	                    }
	                 }
	              
	              }
	              for(int p=0;p<2;p++){
	                if(number[p]==number[p+1]){
	                   number[p+1]=0;
	                   if(number[1]==0){
	                      temp=number[1];
	                      number[1]=number[2];
	                      number[2]=temp;
	                      if(number[0]==number[1]) number[1]=0;
	                   }
	                }
	             }
	           
	              System.out.println("number : "+number[0]+","+number[1]+","+number[2]);
	           }
	           // System.out.println(student_number[0]+","+student_number[1]+","+student_number[2]);
	        
	       /* if(number[0]!=0){
	            ImageIcon image = new ImageIcon("img/asdf.png");
	             Image img = image.getImage();
	             screen = new JLabel(image);
	             screen.setOpaque(false);
	             screen.setBounds(42, 49, 100, 100);     
	             screen.setLayout(null);
	             ex = new JLabel(String.valueOf(number[0]));
	             ex.setFont(new Font("맑은 고딕",Font.BOLD,15));
	             ex.setForeground(Color.BLUE);
	             ex.setBounds(49, 30, 100,100);
	             screen.setVisible(true);
	             ex.setVisible(true);
	             
	             layeredpane.add(screen);
	             layeredpane.add(ex);
	            
	         }*/
	        for(int i=0;i<3;i++){
	        	if(number[i]!=0){
	        		counter=counter+1;
	        		}
	        	}
	      /*  try {
				Teacher.man=Show(counter);
			} catch (Exception e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}*/
	        }
	         
	         ds.close();
	      
	}
}
