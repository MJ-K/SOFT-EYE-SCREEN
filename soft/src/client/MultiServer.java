package client;
import java.awt.*;
import java.io.*;    //  입출력이 일어난다.
import java.net.*;  //  네트워크 프로그램.
import java.util.*;  //  ArrayList 사용(클라이언트를 담는 역할)
import javax.swing.*;

public class MultiServer extends JFrame {
  private ArrayList<MultiServerThread> list;
  private Socket socket;
  JTextArea ta;
  JTextField tf;
  public MultiServer() {
    //  화면 디자인 코드
    setTitle("채팅 서버 ver 1.0");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ta = new JTextArea();
    add(new JScrollPane(ta));
    tf = new JTextField();
    tf.setEditable(false);
    add(tf, BorderLayout.SOUTH);
    setSize(300, 300);
    setVisible(true);
    //  채팅 관련 코드
    list = new ArrayList<MultiServerThread>();
    try {
      ServerSocket serverSocket = new ServerSocket(5000);
      MultiServerThread mst = null;//한 사용자 담당할 채팅 객체
      boolean isStop = false;  //  깃발 값
      tf.setText("서버 정상 실행중입니다^^\n");
      while(! isStop) {
        socket = serverSocket.accept();//클라이언트별 소켓 생성
        mst = new MultiServerThread();//채팅 객체 생성
        list.add(mst);//ArrayList에 채팅 객체 하나 담는다.
        mst.start();//쓰레드 시작
      }//while
    } catch (IOException e) {
      e.printStackTrace();
    }//catch
  }//생성자
  public static void main(String[] args) {
    new MultiServer();
  }//main

  //  내부 클래스
  class MultiServerThread extends Thread {
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    @Override
    public void run() {
      boolean isStop = false;  //  flag value(깃발 값)
      try {
        ois = new ObjectInputStream(socket.getInputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
        String message = null;  //  채팅 내용을 저장할 변수
        while(! isStop) {
          message = (String) ois.readObject();//클라이언트 입력 받기
          String[] str = message.split("#");//홍길동#방가방가
          if(str[1].equals("exit")) { // 홍길동#exit, 종료하겠다는 뜻
            broadCasting(message);//모든 사용자에게 내용 전달
            isStop = true;  //  종료
          } else {
            broadCasting(message);//모든 사용자에게 채팅 내용 전달
          }//else
        }//while
        list.remove(this);//홍길동을 뺀다.
        ta.append(socket.getInetAddress() + 
            " IP 주소의 사용자께서 종료하셨습니다.\n");
        tf.setText("남은 사용자 수 : " + list.size());
      } catch (Exception e) {
        list.remove(this);//장길산을 뺀다.
        ta.append(socket.getInetAddress() + 
            " IP 주소의 사용자께서 비정상 종료하셨습니다.");
        tf.setText("남은 사용자 수 : " + list.size());
      }//catch
    }//run
    public void broadCasting(String message) {//모두에게 전송
      for (MultiServerThread ct : list) {
        ct.send(message);
      }//for
    }//broadCasting
    public void send(String message) {  //  한 사용자에게 전송
      try {
        oos.writeObject(message);
      } catch (IOException e) {
        e.printStackTrace();
      }//catch
    }//send
  }//내부 클래스
}//end