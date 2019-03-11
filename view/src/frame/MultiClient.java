package frame;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
public class MultiClient implements ActionListener, 
    Runnable {
  //  ��� ���� ����
  private Socket socket;          //  ����
  private ObjectInputStream ois;  //  �Է�
  private ObjectOutputStream oos;  //  ���
  private JFrame jframe;          //  ������â
  private JTextField jtf;          //  ä�� �Է¶�
  private JTextArea jta;          //  ä�� ���� �����ִ� ��ü
  private JLabel jlb1, jlb2;        //  ��
  private JPanel jp1, jp2;        //  �ǳ�
  private String ip;              //  IP �ּҸ� ������ ����
  private String id;              //  �г���(��ȭ��) ������ ����
  private JButton jbtn;          //  ���� ��ư �غ�
  
  public MultiClient(String argIp, String argId) {
    ip = "localhost";  //  IP �ּ�
    id = argId;  //  ��ȭ��
    jframe = new JFrame("��Ƽ ä�� ver 1.0");
    //  �Ʒ��� �ٴ� �ǳ� �ڵ�
    jp1 = new JPanel();//�Ʒ� �ٴ� �ǳ�
    jp1.setLayout(new BorderLayout());//���������߾� ���̾ƿ�
    jtf = new JTextField(30);  //  30 ����
    jbtn = new JButton("����");//���� ��ư ����
    jp1.add(jbtn, BorderLayout.EAST);
    jp1.add(jtf, BorderLayout.CENTER);
    //  ���ʿ� �ٴ� �ǳ� �ڵ�
    jp2 = new JPanel();//���ʿ� �ٴ� �ǳ�
    jp2.setLayout(new BorderLayout());
    jlb1 = new JLabel("��ȭ�� : [[" + id + "]]");//��ȭ�� [[ȫ�浿]]
    jlb1.setBackground(Color.YELLOW);
    jlb2 = new JLabel("IP �ּ� : " + ip);//IP �ּ� : 127.0.0.1
    jlb2.setBackground(Color.GREEN);
    jp2.add(jlb1, BorderLayout.CENTER);
    jp2.add(jlb2, BorderLayout.EAST);
    //  �����ӿ� ���̴� �ڵ�
    jta = new JTextArea("", 10, 50);  //  �ʱⰪ, ��(����), ����(��)
    jta.setBackground(Color.ORANGE);//���
    JScrollPane jsp = new JScrollPane(jta, 
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    jframe.add(jp1, BorderLayout.SOUTH);
    jframe.add(jp2, BorderLayout.NORTH);
    jframe.add(jsp, BorderLayout.CENTER);
    //  ������ ���̴� �ڵ�
    jtf.addActionListener(this);
    jbtn.addActionListener(this);
    //  X Ŭ���� ó���ϴ� �ڵ� �� ����
    jframe.addWindowListener(new WindowAdapter() {
      //��Ŭ��->Source->Override/Implement Methods->����->OK
      @Override
      public void windowClosing(WindowEvent e) {
        try {
          oos.writeObject(id + "#exit");//ä�� ����
        } catch (Exception ee) {
          ee.printStackTrace();
        }//catch
        System.exit(0);  //  ���α׷� ����
      }//windowClosing
      @Override
      public void windowOpened(WindowEvent e) {
        jtf.requestFocus();  //  jtf �� ��Ŀ���� ���´�.
      }//windowOpened
    });//  ������ �̺�Ʈ ó�� ��
    jta.setEditable(false);//���� X, ä�� ���� �����ֱ⸸ ����
    //  ũ�� ���� �ڵ�
    jframe.pack();//  �ڵ� ũ�� ����
    jframe.setResizable(false);//â ũ�� ���� X
    jframe.setVisible(true);//���̱�
  }//������
  @Override
  public void actionPerformed(ActionEvent e) {  //  �̺�Ʈ ó��
    Object obj = e.getSource();  //  ����Ʈ �߻� ��ġ ���
    String msg = jtf.getText();  //  ä�� ���� �Է� �ޱ�
    if(obj == jtf) {  //  �Է¶����� ���͸� ģ ���
      if(msg == null || msg.length() == 0) {//������ ���� ���
        //  ���â �����ֱ�
        JOptionPane.showMessageDialog(jframe, "���� ������",
            "���", JOptionPane.WARNING_MESSAGE);
      } else {  //  ������ �Է��ϰ� ������ ���
        try {
          oos.writeObject(id + "#" + msg);
        } catch (Exception ee) {
          ee.printStackTrace();
        }//catch
        jtf.setText("");  //  jtf �� �����.
      }//else : ���� O
    } else if(obj == jbtn) {  //  ���� ��ư�� Ŭ���� ��� 
      try {
        oos.writeObject(id + "#exit");
      } catch (Exception ee) {
        ee.printStackTrace();
      }//catch
      System.exit(0);
    }//else if : ���� ��ư
  }//actionPerformed
  public void init() {
    try {
      socket = new Socket(ip, 5000);
      System.out.println("������ ���ӵǾ����ϴ�^^");
      oos = new ObjectOutputStream(socket.getOutputStream());
      ois = new ObjectInputStream(socket.getInputStream());
      Thread t = new Thread(this);
      t.start();  //  ������ ����
    } catch (Exception e) {
      e.printStackTrace();
    }//catch
  }//init
  public static void main(String[] args) {
    JFrame.setDefaultLookAndFeelDecorated(true);//���ڰԲٹ̱�
    MultiClient cc = new MultiClient( "������ ip", "text�� �̸� �޾� �ֱ�(Ȩ ȭ�鿡��)");

    // MultiClient cc = new MultiClient(127.0.0.1, "ȫ�浿"); // <- ���� ������ ���.

    // MultiClient cc = new MultiClient(127.0.0.1, "ȫ�浿"); // <- ���� ������ ���.

    cc.init();  //  ������ ���� �ڵ� ����
  }//main
  //Run->Open Run Dialog->Arguments->127.0.0.1 ȫ�浿 // <-��Ŭ�������� ������ ���
  //Run->Open Run Dialog->Arguments->127.0.0.1 ���� // <-��Ŭ�������� ������ ���
  @Override
  public void run() {
    String message = null;
    String[] receiveMsg = null;
    boolean isStop = false;
    while(! isStop) {
      try {
        message = (String) ois.readObject();//ä�ó���
        receiveMsg = message.split("#");//ȫ�浿#�ȳ�
      } catch (Exception e) {
        e.printStackTrace();
        isStop = true;  //  �ݺ��� ����� ����
      }//catch
      System.out.println(receiveMsg[0]+":"+receiveMsg[1]);
      //  ��) ȫ�浿 : �ȳ�  ���
      if(receiveMsg[1].equals("exit")) {  //  ä�� ����
        if(receiveMsg[0].equals(id)) {  //  �ش� �����
          System.exit(0);
        } else {  //  �� ���� �����
          jta.append(
              receiveMsg[0] + " ���� �����߽��ϴ�\n");
          //  Ŀ���� ���� ä�� ������ �ڸ��� �����ش�.
          jta.setCaretPosition(
              jta.getDocument().getLength());
        }//else : �� �� �����
      } else {  //  exit �� �ƴ� ���
        //ä�� ���� �����ֱ�
        jta.append(receiveMsg[0] + " : " +
            receiveMsg[1] + "\n");//�� : ȫ�浿 : �ȳ�
        //  Ŀ���� ���� ä�� ������ �ڸ��� �����ش�.
        jta.setCaretPosition(
            jta.getDocument().getLength());
      }//else
    }//while
  }//run
}//end 