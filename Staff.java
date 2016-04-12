import java.util.List;

//SuperClass 
public  class Staff {
	String level;
	String name;
	String eMail;
	String account;
	String password;
	String supervisor;
	public List<String> staffList;
	public List<Staff> staffArray;

	public void setLevel(String level) {
		this.level = level;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	public void setAccount(String account) {
		this.account = account;

	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getLevel() {

		return level;
	}

	public String getName() {

		return name;
	}

	public String getEMail() {

		return eMail;

	}

	public String getAccount() {

		return account;

	}

	public String getPassWord() {

		return password;

	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setStaffList(List<String> staffList) {

		this.staffList = staffList;
	}

	public List<String> getStaffList() {
		return staffList;
	}

	public void setStaffArray(List<Staff> staffArray) {
		this.staffArray = staffArray;
	}

	public List<Staff> getStaffArray() {
		return staffArray;
	}
}
