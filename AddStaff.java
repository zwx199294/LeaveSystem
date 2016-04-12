import java.util.List;

//Add Staff function
public class AddStaff {
	List<String> staffList; // staff list
	Staff s; // variable staff
	List<Staff> staffArray;

	AddStaff() {

	}

	public void addStaff(Staff s, List<String> staffList) {

		this.s = s;
		this.staffList = staffList;

	}

	public List<String> getStaffList() {

		return staffList;

	} // return Staff List .

	public Staff getStaff() {
		return s;
	}// Staff List

	public void setStaffArray(List<Staff> sArray) {
		this.staffArray = sArray;
	}

	public List<Staff> getStaffArray() {
		return staffArray;
	}

}
