package sufaceTension;

public class DataManage {
	
	public static DataManage[] dataManages = new DataManage[30];
	
	private int serialNumber;
	private String studentNumber;
	private int[] empiricalData;
	
	public DataManage() {
		empiricalData = new int[16];
	}
	
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public int getSerialNumber() {
		return this.serialNumber;
	}
	
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getSutdentNumber() {
		return this.studentNumber;
	}
	
	public void updateEmpiricalData(int[] empiricalData){
		for (int i = 0; i < empiricalData.length - 2; i++) {
			this.empiricalData[i] = empiricalData[i + 2];
		}
	}
	
	public int[] getEmpiricalData() {
		return this.empiricalData;
	}
	
}
