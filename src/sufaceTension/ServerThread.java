package sufaceTension;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ServerThread extends Thread {
	
	private Socket socket;
	private BufferedReader br = null;
	private PrintStream ps = null;
	private String stuNumber;
	
	public ServerThread (Socket socket) throws IOException{
		this.socket = socket;
	}
	
	public void run() {
		try {
			String line = null;
			int[] msg = null;
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));	//��ȡ��Ӧ������
			ps = new PrintStream(socket.getOutputStream());								//��ȡ��Ӧ�����

			ps.print("rcv-0-0\r\n");
			
			while ((line = br.readLine()) != null) {
				if (line.startsWith("send-")) {
					msg = getRealMsg(line);
					
					if (msg[0] == 0) {
						if (DataManage.dataManages[msg[1] - 1] == null) {
							DataManage.dataManages[msg[1] - 1] = new DataManage();
						}
						DataManage.dataManages[msg[1] - 1].setStudentNumber(this.stuNumber);
						MainUI.mainUI.infoPanel[msg[1] - 1].setStuNumber(this.stuNumber);
					} else if (msg[0] == 3) {
						if (TcpServer.clients.containsKey(msg[1]) == false) {
							TcpServer.clients.put(msg[1], ps);
						} else {
							TcpServer.clients.remove(msg[1]);
							TcpServer.clients.put(msg[1], ps);
						}
					} else if (msg[0] == 1) {
						if (DataManage.dataManages[msg[1] - 1] == null) {
							DataManage.dataManages[msg[1] - 1] = new DataManage();
							DataManage.dataManages[msg[1] - 1].setSerialNumber(msg[1]);
						}
						DataManage.dataManages[msg[1] - 1].updateEmpiricalData(msg);
					}
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, String.valueOf(TcpServer.clients.getKeyByValue(ps))
					+ "�Ż�������δ֪���������γ����޷�����������������");
			MainUI.mainUI.infoPanel[TcpServer.clients.getKeyByValue(ps) - 1].setStuNumber("δǩ��");
			TcpServer.clients.removeByValue(ps);
			try {
				if (br != null) {
					br.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "���ԶϿ�����ʧ�ܣ��ֶ��ټ�!");
			}
		}
		
	}
	
	public int[] getRealMsg(String line){
		int[] realMsg = new int[18];
		line = line.substring(5, line.length());
		
		String[] nums = line.split("\\D+");
		for (int i = 0; i < nums.length; i++) {
			if (i == 2 && realMsg[0] == 0) {
				this.stuNumber = nums[2];
				realMsg[i] = 0;
			}
			else
				realMsg[i] = Integer.parseInt(nums[i]);
		}

		return realMsg;
	}
	
}
