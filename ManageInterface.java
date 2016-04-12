import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ManageInterface extends JFrame {

	private JPanel contentPane;
	private JPanel btnPane;
	@SuppressWarnings("unused")
	private JButton btnApply;
	@SuppressWarnings("unused")
	private JButton btnHandle;
	int rowNumber;
	String applyVaule;
	public JTable table;
	List<String> s;
	List<Staff> staff;

	String[] staffData;;
	LeaveRequestInterface leavePane;
	HandleInterface handleInterface;
	/**
	 * Create the frame.
	 */

	ManageInterface() {
		setTitle("Request List");

	}

	public void manageInterface(List<String> s, List<Staff> staff) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		this.s = s;

		/*
		 * Staff Table!!!!
		 */
		
		String tableHeadName[] = { "Level", "Name", "Supervisor", "Applied","From Date","To Date" , "Result" };
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.setDataVector(new Object[0][], tableHeadName);

		// Add Table on scrollPane
		// Add Table on scrollPane
		JTable table = new JTable(tableModel);
		this.table=table;
		JScrollPane scrollPane = new JScrollPane(this.table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		this.table.setRowSelectionAllowed(true);
		
		for (int i = 1; i < s.size(); i++) {

			String[] str = s.get(i).split(",");
			staff.get(i).setLevel(str[0]);
			staff.get(i).setName(str[1]);
			staff.get(i).setEMail(str[2]);
			staff.get(i).setAccount(str[3]);
			staff.get(i).setPassword(str[4]);
			staff.get(i).setSupervisor(str[5]);// send information to Staff
			staffData = new String[] { staff.get(i).getLevel(), staff.get(i).getName(),staff.get(i).getSupervisor(),
					"", "","","" };
			tableModel.addRow(staffData);

		}
		
		JButton btnApply = new JButton("Apply");//Apply button
		this.btnApply=btnApply;
		
		JButton btnHandle = new JButton("Handle"); //Handle button
		
		this.btnHandle=btnHandle;
		
		


		btnPane = new JPanel();
   
		/*Apply button events*/
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				int row = table.getSelectedRow();
				if(row==-1)
					JOptionPane.showMessageDialog(null, "Please Select a Staff!");
				//else if(row==0)
					//JOptionPane.showMessageDialog(null, "Director can't apply!");
				else if(table.getValueAt(row, 3)=="yes")
				{
					JOptionPane.showMessageDialog(null, "Staff already applyed!");
				}
				else{
				leavePane = new LeaveRequestInterface();
				leavePane.leaveRequestInterface(table ,row);
				int w = (Toolkit.getDefaultToolkit().getScreenSize().width - leavePane.getSize().width) / 2;
				int h = (Toolkit.getDefaultToolkit().getScreenSize().height - leavePane.getSize().height) / 2;
				leavePane.setLocation(w, h);
				leavePane.setVisible(true);
				
				setRowNumber(row);
				leavePane.getStaffNameLabel().setText(table.getValueAt(row, 1).toString());
				
				
				}
			}
		});
		btnApply.setBounds(59, 213, 113, 27);
		btnPane.add(btnApply);

		
		/*
		 * Handle button events
		 */
		btnHandle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row==-1)
				{
					JOptionPane.showMessageDialog(null, "Please Select a Request!");
				}
				else if(table.getValueAt(row,6)=="Endorsed"||table.getValueAt(row,6)=="Declined")
				{
	
			   JOptionPane.showMessageDialog(null, "The request has been processed .");
				}
				else if(table.getValueAt(row,3)=="yes")
				{
					handleInterface =new HandleInterface();
					handleInterface.handleInterface(s,table ,row);
					handleInterface.getStaffLabel().setText(table.getValueAt(row,1).toString());
					handleInterface.getSupervisorLabel().setText(table.getValueAt(row,2).toString());
					handleInterface.getDateLabel().setText(table.getValueAt(row,4).toString()+" to "+table.getValueAt(row,5).toString());
					handleInterface.setVisible(true);
				}
				
				else 
					JOptionPane.showMessageDialog(null, "This Staff don't have request ! ");

			}
		});
		btnHandle.setBounds(232, 213, 113, 27);
		btnPane.add(btnHandle);

		contentPane.add(btnPane, BorderLayout.SOUTH);

	}
	
	public void setRowNumber(int row)
	{
		this.rowNumber=row;
	}
	
	public int getRowNumber()
	{
		return rowNumber;
	}
	
	public JTable getTabel()
	{
		return table;
	}
	
}
