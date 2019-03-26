package client;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;

/**
 * Multicast Image Receiver
 * Version: 0.1
 * 
 * @author Jochen Luell
 * 
 */

public  class imageReceiver extends Thread implements KeyListener {
   /* Flags and sizes */
   public static int HEADER_SIZE = 8;

   public static int SESSION_START = 128;

   public static int SESSION_END = 64;

   /*
    * The absolute maximum datagram packet size is 65507, The maximum IP packet
    * size of 65535 minus 20 bytes for the IP header and 8 bytes for the UDP
    * header.
    */
   private static int DATAGRAM_MAX_SIZE = 65507;

   /* Default values */
   public static String IP_ADDRESS = "192.168.43.67";

   public static int PORT = 4444;

   JFrame frame;

   boolean fullscreen = false;

   JWindow fullscreenWindow = null;
   

   /**
    * Revceive method
    * 
    * @param multicastAddress
    *            IP multicast adress
    * @param port
    *            Port
    */
   private void receiveImages(String multicastAddress, int port) throws IOException {
      boolean debug = true;

      
      DatagramSocket ds = null;

      /* Constuct frame */
      JLabel labelImage = new JLabel();
      JLabel windowImage = new JLabel();

      frame = new JFrame("Multicast Image Receiver");
      if(frame.getDefaultCloseOperation()==JFrame.EXIT_ON_CLOSE)
      {
         frame.setVisible(false);
      }
      frame.getContentPane().add(labelImage);
      frame.setSize(1200,500);
      frame.setVisible(true);
      frame.addKeyListener(this);
      
      /////////////////////////
      frame.getContentPane().add(windowImage);
      frame.addKeyListener(this);
      /////////////////////////////
      
      /* Construct full screen window */
      fullscreenWindow = new JWindow();
//      fullscreenWindow.setSize(frame.getWidth(),frame.getHeight());
//      windowImage.setSize(frame.getWidth(),frame.getHeight());
//      fullscreenWindow.getContentPane().add(windowImage);
//      fullscreenWindow.addKeyListener(this);

      try {
         /* Get address */
         
         /* Setup socket and join group */
         ds = new DatagramSocket(port);
         ds.setBroadcast(true);
         int currentSession = -1;
         int slicesStored = 0;
         int[] slicesCol = null;
         byte[] imageData = null;
         boolean sessionAvailable = false;

         /* Setup byte array to store data received */
         byte[] buffer = new byte[DATAGRAM_MAX_SIZE];

         timeCheck FrameCheck = new timeCheck();
         int Frame =0;
         
         /* Receiving loop */
         while (true) {
            /* Receive a UDP packet */
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            ds.receive(dp);
            byte[] data = dp.getData();

            /* Read header infomation */
            short session = (short) (data[1] & 0xff);
            short slices = (short) (data[2] & 0xff);
            int maxPacketSize = (int) ((data[3] & 0xff) << 8 | (data[4] & 0xff)); // mask
            // the
            // sign
            // bit
            short slice = (short) (data[5] & 0xff);
            int size = (int) ((data[6] & 0xff) << 8 | (data[7] & 0xff)); // mask
            // the
            // sign
            // bit

            if (debug) {
               System.out.println("------------- PACKET -------------");
               System.out.println("SESSION_START = "
                     + ((data[0] & SESSION_START) == SESSION_START));
               System.out.println("SSESSION_END = "
                     + ((data[0] & SESSION_END) == SESSION_END));
               System.out.println("SESSION NR = " + session);
               System.out.println("SLICES = " + slices);
               System.out.println("MAX PACKET SIZE = " + maxPacketSize);
               System.out.println("SLICE NR = " + slice);
               System.out.println("SIZE = " + size);
               System.out.println("------------- PACKET -------------\n");
               
               if(FrameCheck.Count_X_millisec(1000))
               {
                  System.out.println("Frame: "+Frame);
                  Frame = 0;
                  FrameCheck.Rcount();
                  
               }
               else
               {
                  Frame++;
               }
               
            }

            /* If SESSION_START falg is set, setup start values */
            if ((data[0] & SESSION_START) == SESSION_START) {
               if (session != currentSession) {
                  currentSession = session;
                  slicesStored = 0;
                  /* Consturct a appropreately sized byte array */
                  imageData = new byte[slices * maxPacketSize];
                  slicesCol = new int[slices];
                  sessionAvailable = true;
               }
            }

            /* If package belogs to current session */
            if (sessionAvailable && session == currentSession) {
               if (slicesCol != null && slicesCol[slice] == 0) {
                  slicesCol[slice] = 1;
                  System.arraycopy(data, HEADER_SIZE, imageData, slice
                        * maxPacketSize, size);
                  slicesStored++;
               }
            }

            /* If image is complete dispay it */
            if (slicesStored == slices) {
               ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
               BufferedImage image = ImageIO.read(bis);
//               labelImage.setBounds(0, 0, frame.getWidth(), frame.getHeight());
//               windowImage.setBounds(0, 0, frame.getWidth(), frame.getHeight());
//               labelImage.setSize(frame.getWidth(), frame.getHeight());
//               windowImage.setSize(frame.getWidth(), frame.getHeight());
   
               frame.getGraphics().drawImage(image, 0, 0, frame.getWidth(), frame.getHeight(), null);
               //내가 원하는 크기로!화질이 icon보다 아주 조금 안좋다.(왜냐면 내가 원하는 해상도에 맞추려다보니..)
               
               //ImageIcon ii=new ImageIcon(image);
               
//               labelImage.setIcon(ii);
//               windowImage.setIcon(ii);

               //frame.pack();
            }

            if (debug) {
               System.out.println("STORED SLICES: " + slicesStored);
            }
                    
            
         }
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         if (ds != null) {
            ds.close();
         }
      }
   }

   /**
    * @param args
    */
 
   public void run() {

      /* Handle command line arguments */
      /*switch (args.length) {
     
      case 2:
         IP_ADDRESS = args[1];
      case 1:
         PORT = Integer.parseInt(args[0]);
      }*/

      imageReceiver receiver = new imageReceiver();
      try {
         receiver.receiveImages(IP_ADDRESS, PORT);
      } catch (IOException e) {
         // TODO 자동 생성된 catch 블록r
         e.printStackTrace();
      }
   }
 

   /*
    * (non-Javadoc)
    * 
    * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
    */
   public void keyPressed(KeyEvent keyevent) {
      GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

      /* Toggle full screen mode on key press */
      if (fullscreen) {
         device.setFullScreenWindow(null);
         frame.setVisible(false);
//         fullscreenWindow.setVisible(false);
         fullscreen = false;
      } else {
         //device.setFullScreenWindow(fullscreenWindow);
         device.setFullScreenWindow(frame);
         frame.setVisible(true);
//         fullscreenWindow.setVisible(true);
         fullscreen = true;
      }

   }

   public void keyReleased(KeyEvent keyevent) {
   }

   public void keyTyped(KeyEvent keyevent) {
   }

}