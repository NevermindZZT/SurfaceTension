package sufaceTension;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DataUI extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6214484802539800056L;

	private JLabel[] dataLabel = new JLabel[24];
	private JLabel[] data2Label = new JLabel[12];
	private JLabel studentLabel = new JLabel("", JLabel.CENTER);
	
	public DataUI(int serialNumber) {
		JFrame dataFrame = new JFrame("机器号：" + String.valueOf(serialNumber));
		JButton refreshButton = new JButton("刷新数据");
		JPanel logoPanel = new JPanel();
		JPanel dataPanel = new JPanel();
		JPanel data2Panel = new JPanel();
		JPanel data2Container = new JPanel();
		JLabel data2Header = new JLabel("定标数据：");
		
		studentLabel.setFont(new Font("微软雅黑", Font.PLAIN, 48));
		studentLabel.setPreferredSize(new Dimension(768, 120));
		
		for (int i = 0; i < dataLabel.length; i++) {
			dataLabel[i] = new JLabel();
			dataLabel[i].setBorder(BorderFactory.createLineBorder(Color.black));
			dataLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
			dataLabel[i].setFont(new Font("微软雅黑", Font.PLAIN, 32));
		}
		dataLabel[0].setText("编号");
		dataLabel[0].setOpaque(true);
		dataLabel[0].setBackground(new Color(196, 215, 233));
		dataLabel[6].setText("数据");
		dataLabel[6].setOpaque(true);
		dataLabel[6].setBackground(new Color(196, 215, 233));
		dataLabel[12].setText("编号");
		dataLabel[12].setOpaque(true);
		dataLabel[12].setBackground(new Color(196, 215, 233));
		dataLabel[18].setText("数据");
		dataLabel[18].setOpaque(true);
		dataLabel[18].setBackground(new Color(196, 215, 233));
		
		for (int i = 0; i < 5; i++) {
			dataLabel[i + 1].setText(String.valueOf(i + 1));
			dataLabel[i + 1].setOpaque(true);
			dataLabel[i + 1].setBackground(new Color(228, 228, 228));
			dataLabel[i + 13].setText(String.valueOf(i + 6));
			dataLabel[i + 13].setOpaque(true);
			dataLabel[i + 13].setBackground(new Color(228, 228, 228));
		}
		

		for (int i = 0; i < data2Label.length; i++) {
			data2Label[i] = new JLabel();
			data2Label[i].setBorder(BorderFactory.createLineBorder(Color.black));
			data2Label[i].setHorizontalAlignment(SwingConstants.CENTER);
			data2Label[i].setFont(new Font("微软雅黑", Font.PLAIN, 24));
		}
		
		for (int i = 0; i < 6; i++) {
			data2Label[i].setOpaque(true);
			data2Label[i].setBackground(new Color(196, 215, 233));
		}
		data2Label[0].setText("0");
		data2Label[1].setText("500");
		data2Label[2].setText("1000");
		data2Label[3].setText("1500");
		data2Label[4].setText("2000");
		data2Label[5].setText("2500");
		
		data2Header.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		data2Container.setLayout(new BorderLayout());
		data2Container.add(data2Header, BorderLayout.NORTH);
		data2Container.add(data2Panel, BorderLayout.CENTER);
		data2Container.add(new JLabel("中南大学物理与电子学院", JLabel.RIGHT), BorderLayout.SOUTH);
		
		refreshButton.setPreferredSize(new Dimension(100, 40));
		refreshButton.setBackground(new Color(220, 220, 220));
		refreshButton.setBorderPainted(false);
		refreshButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				TcpServer.clients.get(serialNumber).print("rcv-1-" + String.valueOf(serialNumber) + "\r\n");
				try {
					Thread.sleep(1000);
					setStudentNumber(DataManage.dataManages[serialNumber - 1].getSutdentNumber());
					setData(DataManage.dataManages[serialNumber - 1].getEmpiricalData());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "更新数据失败，请重试！");
				}
			}
		});
		
		logoPanel.setLayout(new BorderLayout());
		logoPanel.add(studentLabel, BorderLayout.NORTH);
		logoPanel.add(refreshButton, BorderLayout.EAST);
		
		dataPanel.setLayout(new GridLayout(6, 4));
		for (int i = 0; i < dataLabel.length; i++) {
			dataPanel.add(dataLabel[i / 4 + (6 * (i % 4))]);
		}
		
		data2Panel.setLayout(new GridLayout(2, 6));
		for (int i = 0; i < data2Label.length; i++) {
			data2Panel.add(data2Label[i]);
		}
		
		dataFrame.setLayout(new BorderLayout());
		dataFrame.add(logoPanel, BorderLayout.NORTH);
		dataFrame.add(dataPanel, BorderLayout.CENTER);
		dataFrame.add(data2Container, BorderLayout.SOUTH);
		dataFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		dataFrame.setSize(1366, 768);
		dataFrame.setLocationRelativeTo(null);
		dataFrame.setExtendedState(MAXIMIZED_BOTH);
		dataFrame.setVisible(true);
		
	}
	
	
	public void setStudentNumber(String studentNumber) {
		studentLabel.setText("学号：" + studentNumber);
	}
	
	public void setData(int[] data) {
		for (int i = 0; i < data.length; i++) {
			if (i < 6) {
				data2Label[i + 6].setText(String.valueOf(data[i]));
			} else if (i < 11) {
				dataLabel[i + 1].setText(String.valueOf(data[i]));
			} else if (i < 16) {
				dataLabel[i + 8].setText(String.valueOf(data[i]));
			}
		}
	}
	
	public static void main(String[] args) {
		new DataUI(1);
	}
	
}
