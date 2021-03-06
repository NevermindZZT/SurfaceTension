package sufaceTension;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8921808549301716988L;

	private String stuNumber;
	private JLabel stuLabel = new JLabel();
	
	public InfoPanel(int serialNumber) {
		super();
		
		JLabel serialLabel = new JLabel(String.valueOf(serialNumber), JLabel.CENTER);
		
		serialLabel.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		
		stuLabel.setHorizontalAlignment(JLabel.CENTER);
		stuLabel.setVerticalAlignment(JLabel.CENTER);
		stuLabel.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		
		setStuNumber("δǩ��");
		
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.add(serialLabel, BorderLayout.NORTH);
		this.add(stuLabel, BorderLayout.CENTER);
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (MainUI.mainUI.infoPanel[serialNumber - 1].getStuNumber().equals("δǩ��")) {
					JOptionPane.showMessageDialog(null, "�û���δǩ����");
				} else if (TcpServer.clients.get(serialNumber) == null) {
					JOptionPane.showMessageDialog(null, "�û����ѶϿ����ӣ�");
				} else {
					DataUI dataUI = new DataUI(serialNumber);
					dataUI.setStudentNumber(DataManage.dataManages[serialNumber - 1].getSutdentNumber());
					dataUI.setData(DataManage.dataManages[serialNumber - 1].getEmpiricalData());
				}
			}
		});
	}
	
	public void setStuNumber(String stuNumber) {
		this.stuNumber = stuNumber;
		stuLabel.setText(stuNumber);
	}
	
	public String getStuNumber() {
		return stuNumber;
	}
	
}
