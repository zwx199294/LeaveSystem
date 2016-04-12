import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class HandleInterface extends JDialog {
	public HandleInterface() {
	}
	
	private final JPanel contentPanel = new JPanel();
	String staffName;
	String supervisorName;
	String result;
	JLabel supervisorNameLabel;
	JLabel dataTimeLabel;
	JLabel staffNameLabel;
	/**
	 * Create the dialog.
	 */
	public void handleInterface(List<String> stafflist,JTable table , int row){
		//row is the staff who applyed for a leave .
		setTitle("Handle Leave Request");
		setBounds(100, 100, 450, 300);
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
		this.setLocation(w, h);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel Supervisor = new JLabel("Supervisor :");
			Supervisor.setFont(new Font("宋体", Font.BOLD, 15));
			Supervisor.setHorizontalAlignment(SwingConstants.RIGHT);
			Supervisor.setBounds(14, 32, 135, 18);
			contentPanel.add(Supervisor);
		}
		{
		    JLabel SupervisorName = new JLabel("");
		    SupervisorName.setFont(new Font("宋体", Font.PLAIN, 15));
			SupervisorName.setBounds(181, 32, 115, 18);
			this.supervisorNameLabel=SupervisorName;
			contentPanel.add(SupervisorName);
		}
		
		JLabel RequestStaff = new JLabel("");
		RequestStaff.setFont(new Font("宋体", Font.PLAIN, 15));
		RequestStaff.setBackground(Color.BLUE);
		RequestStaff.setBounds(181, 95, 237, 18);
		this.staffNameLabel=RequestStaff;
		contentPanel.add(RequestStaff);
		
		JLabel Date = new JLabel("Date :");
		Date.setHorizontalAlignment(SwingConstants.RIGHT);
		Date.setFont(new Font("宋体", Font.BOLD, 15));
		Date.setBounds(43, 152, 72, 18);
		contentPanel.add(Date);
		
		JLabel DateTime = new JLabel("");
		DateTime.setFont(new Font("宋体", Font.PLAIN, 15));
		DateTime.setHorizontalAlignment(SwingConstants.LEFT);
		DateTime.setBounds(119, 152, 274, 18);
		this.dataTimeLabel=DateTime;
		contentPanel.add(DateTime);
		{
			JLabel lblStaff = new JLabel("Staff :");
			lblStaff.setFont(new Font("宋体", Font.BOLD, 15));
			lblStaff.setHorizontalAlignment(SwingConstants.RIGHT);
			lblStaff.setBounds(77, 95, 72, 18);
			contentPanel.add(lblStaff);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
				/*
				 * Endorse Button  :    Call  StaffRequest.java   ( Chain of Responsibility)
				 */
				JButton okButton = new JButton("Endorse");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ParseStaffList parse=new ParseStaffList();
						/*
						 * use chain of responsibility...
						 */
						if(supervisorNameLabel.getText().equals("Director"))
						{
							result="Endorsed";
							table.setValueAt(result, row, 6);
							JOptionPane.showMessageDialog(null, "Endorsed!", "Endorsed!", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
						else{
						String nextSupervisor = parse.nextSupervisor(supervisorNameLabel.getText(), stafflist);
						SupervisorHandler nowHandler = new SupervisorHandler(supervisorNameLabel.getText());
						SupervisorHandler nextHandler=new SupervisorHandler(nextSupervisor);
						nowHandler.setSupervisor(nextHandler);
						nowHandler.handleRequest(true); //send boolean value to Handle COR!
						supervisorNameLabel.setText(nextSupervisor);}
						
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			
			{   
				/*
				 * Decline Button  :    Call  StaffRequest.java   ( Chain of Responsibility)
				 */
				JButton cancelButton = new JButton("Decline");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					 SupervisorHandler nowHandler = new SupervisorHandler(supervisorNameLabel.getText());
			         //send false value to handle function in  StaffHandler.java
			         nowHandler.handleRequest(false);
			         result="Declined";
					 table.setValueAt(result, row, 6);
					 dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public JLabel getStaffLabel(){
		
		return staffNameLabel;
	}
	
    public JLabel getDateLabel(){
		
		return dataTimeLabel;
	}
    
    public JLabel getSupervisorLabel()
    {
    	return supervisorNameLabel;
    }

    public String getResult(){
    	return result;
    }//  Request result : endorsed or declined 

}
