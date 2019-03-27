package client;
import java.awt.*;
import java.io.*;    //  ������� �Ͼ��.
import java.net.*;  //  ��Ʈ��ũ ���α׷�.
import java.util.*;  //  ArrayList ���(Ŭ���̾�Ʈ�� ��� ����)
import javax.swing.*;

public class MultiServer extends JFrame {
  private ArrayList<MultiServerThread> list;
  private Socket socket;
  JTextArea ta;
  JTextField tf;
  public MultiServer() {
    //  ȭ�� ������ �ڵ�
    setTitle("ä�� ���� ver 1.0");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ta = new JTextArea();
    add(new JScrollPane(ta));
    tf = new JTextField();
    tf.setEditable(false);
    add(tf, BorderLayout.SOUTH);
    setSize(300, 300);
    setVisible(true);
    //  ä�� ���� �ڵ�
    list = new ArrayList<MultiServerThread>();
    try {
      ServerSocket serverSocket = new ServerSocket(5000);
      MultiServerThread mst = null;//�� ����� ����� ä�� ��ü
      boolean isStop = false;  //  ��� ��
      tf.setText("���� ���� �������Դϴ�^^\n");
      while(! isStop) {
        socket = serverSocket.accept();//Ŭ���̾�Ʈ�� ���� ����
        mst = new MultiServerThread();//ä�� ��ü ����
        list.add(mst);//ArrayList�� ä�� ��ü �ϳ� ��´�.
        mst.start();//������ ����
      }//while
    } catch (IOException e) {
      e.printStackTrace();
    }//catch
  }//������
  public static void main(String[] args) {
    new MultiServer();
  }//main

  //  ���� Ŭ����
  class MultiServerThread extends Thread {
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    @Override
    public void run() {
      boolean isStop = false;  //  flag value(��� ��)
      try {
        ois = new ObjectInputStream(socket.getInputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
        String message = null;  //  ä�� ������ ������ ����
        while(! isStop) {
          message = (String) ois.readObject();//Ŭ���̾�Ʈ �Է� �ޱ�
          String[] str = message.split("#");//ȫ�浿#�氡�氡
          if(str[1].equals("exit")) { // ȫ�浿#exit, �����ϰڴٴ� ��
            broadCasting(message);//��� ����ڿ��� ���� ����
            isStop = true;  //  ����
          } else {
            broadCasting(message);//��� ����ڿ��� ä�� ���� ����
          }//else
        }//while
        list.remove(this);//ȫ�浿�� ����.
        ta.append(socket.getInetAddress() + 
            " IP �ּ��� ����ڲ��� �����ϼ̽��ϴ�.\n");
        tf.setText("���� ����� �� : " + list.size());
      } catch (Exception e) {
        list.remove(this);//������ ����.
        ta.append(socket.getInetAddress() + 
            " IP �ּ��� ����ڲ��� ������ �����ϼ̽��ϴ�.");
        tf.setText("���� ����� �� : " + list.size());
      }//catch
    }//run
    public void broadCasting(String message) {//��ο��� ����
      for (MultiServerThread ct : list) {
        ct.send(message);
      }//for
    }//broadCasting
    public void send(String message) {  //  �� ����ڿ��� ����
      try {
        oos.writeObject(message);
      } catch (IOException e) {
        e.printStackTrace();
      }//catch
    }//send
  }//���� Ŭ����
}//end