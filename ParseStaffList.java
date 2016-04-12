import java.util.ArrayList;
import java.util.List;

//Parse  Staff List
public class ParseStaffList {
	List<Integer> row = new ArrayList<Integer>();

	/*
	 * if Staff's supervisor has been deleted , set new supervisor as
	 * Director!!!
	 */
	public void changeSupervisor(String deleteStaffName, List<String> staffList) {
		for (int i = 0; i < staffList.size(); i++) {

			String[] str = staffList.get(i).split(",");
			if (str[5].equals(deleteStaffName)) {
				staffList.set(i, str[0] + "," + str[1] + "," + str[2] + "," + str[3] + "," + str[4] + "," + "Director");// change
																														// supervisor!
				row.add(i);
			}

		}

	}
	
	
	/*
	 *   select next Supervisor name .
	 *   
	 *   return Name .
	 */
	public String nextSupervisor(String supervisorName, List<String> staffList){
		for (int i = 0; i < staffList.size(); i++) {
			
			String[] str = staffList.get(i).split(",");
		   if(str[1].equals(supervisorName)){
			   return str[5];//next Supervisor's Name
		   }
		}
		return "Director"; // director has no supervisor.
		
	}

	public List<Integer> getRow() {
		return row;
	}
}