import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@SuppressWarnings("serial")
public class DeleteInterface extends JFrame {
	List<String> staffList;
	Staff s;
	String[] staffData;;
	String deleteStaff;
	String supervisor;
	private JPanel contentPane;
	public JTable table;

	DeleteInterface() {

	}

	DeleteInterface(List<String> staffList, Staff s) {
		setTitle("Delete Staff");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0)); // Delete Interface

		this.s = s;
		this.staffList = staffList;

		String tableHeadName[] = { "Level", "Name", "Email", "Account", "Supervisor" };
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.setDataVector(new Object[0][], tableHeadName);
		for (int i = 0; i < staffList.size(); i++) {

			String[] str = staffList.get(i).split(",");
			this.s.setLevel(str[0]);
			this.s.setName(str[1]);
			this.s.setEMail(str[2]);
			this.s.setAccount(str[3]);
			this.s.setPassword(str[4]);
			this.s.setSupervisor(str[5]);// send information to Staff
			staffData = new String[] { this.s.getLevel(), this.s.getName(), this.s.getEMail(), this.s.getAccount(),
					this.s.getSupervisor() };
			tableModel.addRow(staffData);
		}

		JTable table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		// Add Table on scrollPane

		JButton btnDelete = new JButton("Delete");
		contentPane.add(btnDelete, BorderLayout.SOUTH);

		/*
		 * Delete Event..
		 */
		btnDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Please Select a row");
				} else if (row == 0) {
					JOptionPane.showMessageDialog(null, "Can't delete Director");
				} else {

					setDeleteStaff(table.getValueAt(row, 1).toString()); // deleted
																			// Staff
																			// name
					ParseStaffList parse = new ParseStaffList();
					parse.changeSupervisor(table.getValueAt(row, 1).toString(), staffList);
					for (int i = 0; i < parse.getRow().size(); i++)
						table.setValueAt("Director", parse.getRow().get(i), 4);
					// assgin new supervisor for those staff's supervisor has
					// been deleted .
					tableModel.removeRow(row);
					staffList.remove(staffList.get(row));
					
				}
			}
		});

		setTable(table);
	}

	public void setDeleteStaff(String s) {
		this.deleteStaff = s;
	}

	public String getDeleteStaff() {
		return deleteStaff;
	}

	public void setSupervisorName(String s) {
		this.supervisor = s;
	}

	public String getSupervisorName() {

		return supervisor;
	}

	public void setTable(JTable t) {
		this.table = t;
	}

	public JTable getTable() {

		return table;
	}
}
