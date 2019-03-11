package frame;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sun.image.codec.jpeg.ImageFormatException;

/**
 * Multicast Image Sender
 * Version: 0.1
 * 
 * @author Jochen Luell
 *
 */
public class imageSender extends Thread {
   
   /* Flags and sizes */
   public static int HEADER_SIZE = 8;
   public static int MAX_PACKETS = 255;
   public static int SESSION_START = 128;
   public static int SESSION_END = 64;
   public static int DATAGRAM_MAX_SIZE = 65507 - HEADER_SIZE;
   public static int MAX_SESSION_NUMBER = 255;

   /*
    * The absolute maximum datagram packet size is 65507, The maximum IP packet
    * size of 65535 minus 20 bytes for the IP header and 8 bytes for the UDP
    * header.
    */
   public static String OUTPUT_FORMAT = "jpg";

   public static int COLOUR_OUTPUT = BufferedImage.TYPE_INT_RGB;

   /* Default parameters */
   public static double SCALING = 0.5;
   public static int SLEEP_MILLIS = 2000;
   public static String IP_ADDRESS =  "127.0.0.1";
   //public static String IP_ADDRESS =null;//=  "127.0.0.1";
   public static int PORT = 4444;
   public static boolean SHOW_MOUSEPOINTER = true;

   /**
    * Takes a screenshot (fullscreen)
    * 
    * @return Sreenshot
    * @throws AWTException
    * @throws ImageFormatException
    * @throws IOException
    */
   public static String Ipconfig ()throws Exception{
      
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
   
   public static BufferedImage getScreenshot() throws AWTException,ImageFormatException, IOException {
      Toolkit toolkit = Toolkit.getDefaultToolkit();
      Dimension screenSize = toolkit.getScreenSize();
      Rectangle screenRect = new Rectangle(screenSize);

      Robot robot = new Robot();
      BufferedImage image = robot.createScreenCapture(screenRect);

      return image;
   }

   /**
    * Returns a random image from given directory
    * 
    * @param dir Image directory
    * @return Random Image
    * @throws IOException
    */
   public static BufferedImage getRandomImageFromDir(File dir) throws IOException {
      String[] images = dir.list(new ImageFileFilter());
        int random = new Random().nextInt(images.length);

        String fileName = dir.getAbsoluteFile() + File.separator + images[random];
        File imageFile = new File(fileName);

      return ImageIO.read(imageFile);
   }

   /**
    * Converts BufferedImage to byte array
    * 
    * @param image Image to convert
    * @param format Image format (JPEG, PNG or GIF)
    * @return Byte Array
    * @throws IOException
    */
   public static byte[] bufferedImageToByteArray(BufferedImage image, String format) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write(image, format, baos);
      return baos.toByteArray();
   }

   /**
    * Scales a bufferd image 
    * 
    * @param source Image to scale
    * @param w Image widht
    * @param h Image height
    * @return Scaled image
    */
   public static BufferedImage scale(BufferedImage source, int w, int h) {
      Image image = source
            .getScaledInstance(w, h, Image.SCALE_AREA_AVERAGING);
      BufferedImage result = new BufferedImage(w, h, COLOUR_OUTPUT);
      Graphics2D g = result.createGraphics();
      g.drawImage(image, 0, 0, null);
      g.dispose();
      return result;
   }

   /**
    * Shrinks a BufferedImage
    * 
    * @param source Image to shrink
    * @param factor Scaling factor
    * @return Scaled image
    */
   public static BufferedImage shrink(BufferedImage source, double factor) {
      int w = (int) (source.getWidth() * factor);
      int h = (int) (source.getHeight() * factor);
      return scale(source, w, h);
   }

   /**
    * Copies a BufferedImage
    * 
    * @param image Image to copy
    * @return Copied image
    */
   public static BufferedImage copyBufferedImage(BufferedImage image) {
      BufferedImage copyOfIm = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g = copyOfIm.createGraphics();
        g.drawRenderedImage(image, null);
        g.dispose();
        return copyOfIm;
    }

   /*
   
    */
   /**
    * Sends a byte array via multicast
    * Multicast addresses are IP addresses in the range of 224.0.0.0 to
    * 239.255.255.255.
    * 
    * @param imageData Byte array
    * @param multicastAddress IP multicast address
    * @param port Port
    * @return <code>true</code> on success otherwise <code>false</code>
    */
   private boolean sendImage(byte[] imageData, String multicastAddress,
         int port) {
      InetAddress ia;
      
      boolean ret = false;
   //   int ttl = 16;

      try {
         ia = InetAddress.getByName(multicastAddress);
         //ia = InetAddress.getByName("192.168.1.225");
      } catch (UnknownHostException e) {
         e.printStackTrace();
         return ret;
      }

      DatagramSocket ms = null;
      boolean yorn;
//      try {
//         yorn=ms.getBroadcast();
//         System.out.println();
//      } catch (SocketException e1) {
//         // TODO 자동 생성된 catch 블록
//         e1.printStackTrace();
//      }
      try {
         ms = new DatagramSocket();
         //ms.setTimeToLive(ttl);
         DatagramPacket dp = new DatagramPacket(imageData, imageData.length,ia, port);
         ms.send(dp);
         ret = true;
      } catch (IOException e) {
         e.printStackTrace();
         ret = false;
      } finally {
         if (ms != null) {
               ms.close();
         }
      }

      return ret;
   }

   /**
    * @param args
    */
   public void run() {
      imageSender sender = new imageSender();
      int sessionNumber = 0;
      boolean multicastImages = false;
      
      
      // Create Frame
      JFrame frame = new JFrame("Multicast Image Sender");
      if(frame.getDefaultCloseOperation()==JFrame.EXIT_ON_CLOSE)
      {
         frame.setVisible(false);
      }
      JLabel label = new JLabel();
      frame.getContentPane().add(label);
      frame.setVisible(true);


      /* Handle command line arguments */
      /*switch (args.length) {
      case 5:
         IP_ADDRESS = args[4];
      case 4:
         PORT = Integer.parseInt(args[3]);
      case 3:
         SHOW_MOUSEPOINTER = Integer.parseInt(args[2]) == 1 ? true : false;
      case 2:
         SLEEP_MILLIS = Integer.parseInt(args[1]) * 1000;
      case 1:
         SCALING = Double.parseDouble(args[0]);
      }
*/
      /* Check weather to multicast screenshots or images */
      if(new File("images").exists() && new File("images").isDirectory()) {
         label.setText("Multicasting images...");
         multicastImages = true;
      }
      else {
         label.setText("Multicasting screenshots...");
      }
      
      frame.pack();

      try {
         /* Continuously send images */
         while (true) {
            BufferedImage image;

            /* Get image or screenshot */
            if(multicastImages) {
               image = getRandomImageFromDir(new File("images"));
            }
            else {
               image = getScreenshot();
               
               /* Draw mousepointer into image */
               if(SHOW_MOUSEPOINTER) {
                   PointerInfo p = MouseInfo.getPointerInfo();
                   int mouseX = p.getLocation().x;
                   int mouseY = p.getLocation().y;
                   
                   Graphics2D  g2d = image.createGraphics();
                   g2d.setColor(Color.red);
                   Polygon polygon1 = new Polygon(new int[] { mouseX, mouseX+10, mouseX, mouseX},
                                                         new int[] { mouseY, mouseY+10, mouseY+15, mouseY}
                            , 4);
                   
                   Polygon polygon2 = new Polygon(new int[] { mouseX+1, mouseX+10+1, mouseX+1, mouseX+1},
                                new int[] { mouseY+1, mouseY+10+1, mouseY+15+1, mouseY+1}, 4);
                   g2d.setColor(Color.black);
                   g2d.fill(polygon1);
                   
                   g2d.setColor(Color.red);
                   g2d.fill(polygon2);
                   g2d.dispose();
               }
                
                
            }

            /* Scale image */
            image = shrink(image, SCALING);
            byte[] imageByteArray = bufferedImageToByteArray(image, OUTPUT_FORMAT);
            int packets = (int) Math.ceil(imageByteArray.length / (float)DATAGRAM_MAX_SIZE);

            /* If image has more than MAX_PACKETS slices -> error */
            if(packets > MAX_PACKETS) {
               System.out.println("Image is too large to be transmitted!");
               continue;
            }

            /* Loop through slices */
            for(int i = 0; i <= packets; i++) {
               int flags = 0;
               flags = i == 0 ? flags | SESSION_START: flags;
               flags = (i + 1) * DATAGRAM_MAX_SIZE > imageByteArray.length ? flags | SESSION_END : flags;

               int size = (flags & SESSION_END) != SESSION_END ? DATAGRAM_MAX_SIZE : imageByteArray.length - i * DATAGRAM_MAX_SIZE;

               /* Set additional header */
               byte[] data = new byte[HEADER_SIZE + size];
               data[0] = (byte)flags;
               data[1] = (byte)sessionNumber;
               data[2] = (byte)packets;
               data[3] = (byte)(DATAGRAM_MAX_SIZE >> 8);
               data[4] = (byte)DATAGRAM_MAX_SIZE;
               data[5] = (byte)i;
               data[6] = (byte)(size >> 8);
               data[7] = (byte)size;

               /* Copy current slice to byte array */
               
               System.arraycopy(imageByteArray, i * DATAGRAM_MAX_SIZE, data, HEADER_SIZE, size);
               /* Send multicast packet */
               sender.sendImage(data, Ipconfig(), PORT);

               /* Leave loop if last slice has been sent */
               if((flags & SESSION_END) == SESSION_END) break;
            }
            /* Sleep */
            Thread.sleep(SLEEP_MILLIS);
            
            /* Increase session number */
            sessionNumber = sessionNumber < MAX_SESSION_NUMBER ? ++sessionNumber : 0;
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

}

/**
 * File filter class
 * 
 * @author luelljoc
 *
 */
class ImageFileFilter implements FilenameFilter
{
    /* (non-Javadoc)
     * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
     */
    public boolean accept( File dir, String name )
    {
      String nameLc = name.toLowerCase();
      return nameLc.endsWith(".jpg") ? true : false;
    }
}