package chat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


//Ŭ���̾�Ʈ�� ���� ���۵� ���ڿ��� �޾Ƽ� �ٸ� Ŭ���̾�Ʈ���� ���ڿ���
//�����ִ� ������
class EchoThread extends Thread{
	Socket socket;
	ArrayList<Socket> vec;
	public EchoThread(Socket socket, ArrayList<Socket> vec){
		this.socket = socket;
		this.vec = vec;
	}
	public void run(){
		BufferedReader br = null;
		try{
			br = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			String str =null;
			while(true){
				//Ŭ���̾�Ʈ�� ���� ���ڿ� �ޱ�
				str=br.readLine();
				//��밡 ������ ������ break;
				if(str==null){
					//���Ϳ��� ���ֱ�
					vec.remove(socket);
					break;
				}
				//����� ���ϵ��� ���ؼ� �ٸ� Ŭ���̾�Ʈ���� ���ڿ� �����ֱ�
				sendMsg(str);				
			}
			
		}catch(IOException ie){
			System.out.println(ie.getMessage());
		}finally{
			try{
				if(br != null) {br.close();}
				if(socket != null&&!socket.isClosed()) {socket.close();}
			}catch(IOException ie){
				System.out.println(ie.getMessage());
			}
		}
	}
	
	//���۹��� ���ڿ� �ٸ� Ŭ���̾�Ʈ�鿡�� �����ִ� �޼���
	public void sendMsg(String str){
		try{
			for(Socket socket:vec){
				//for�� ���� ������ socket�� �����͸� ���� Ŭ���̾�Ʈ�� ��츦 �����ϰ� 
				//������ socket�鿡�Ը� �����͸� ������.
				if(socket != this.socket){
					PrintWriter pw = 
						new PrintWriter(socket.getOutputStream(), true);
					pw.println(str);
					pw.flush();
					//��,���⼭ ���� ���ϵ��� ���ǰ͵��̱� ������ ���⼭ ������ �ȵȴ�.
				}
			}
		}catch(IOException ie){
			System.out.println(ie.getMessage());
		}
	}
}


public class MultichatServer {
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket socket =null;
		//Ŭ���̾�Ʈ�� ����� ���ϵ��� �迭ó�� ������ ���Ͱ�ü ����
		ArrayList<Socket> vec = new ArrayList<Socket>();
		try{
			server= new ServerSocket(52100);
			while(true){
				System.out.println("���Ӵ����..");
				socket = server.accept();
				//Ŭ���̾�Ʈ�� ����� ������ ���Ϳ� ���
				vec.add(socket);
				//������ ����
				new EchoThread(socket, vec).start();
			}
		}catch(IOException ie){
			System.out.println(ie.getMessage());
		}
	}
}
