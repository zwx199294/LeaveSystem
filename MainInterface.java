import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Toolkit;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MainInterface extends JFrame {
	// initialize
	private JPanel contentPane;
	// DeleteInterface deleteStaff=new DeleteInterface();
	NewStaffInterface newstaff = new NewStaffInterface();
	DeleteInterface deleteStaff = new DeleteInterface();
	ManageInterface manageStaff= new ManageInterface();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainInterface frame = new MainInterface();
					frame.setVisible(true);
					frame.setResizable(false);
					int w = (Toolkit.getDefaultToolkit().getScreenSize().width - frame.getSize().width) / 2;
					int h = (Toolkit.getDefaultToolkit().getScreenSize().height - frame.getSize().height) / 2;
					frame.setLocation(w, h);// window pop on screen center .
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainInterface() {
		setTitle("Leave System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 51, 51));

		setContentPane(contentPane);

		contentPane.setLayout(null);

		JButton btnNewStaff = new JButton("New");
		/*
		 * new Button
		 */
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		/*
		 * delete Button
		 */
		
		JButton btnManage = new JButton("Manage");
		btnManage.setEnabled(false);
		/*
		 * Manage Button  
		 */
		
		
		
		btnNewStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newstaff.setVisible(true);
				btnDelete.setEnabled(true);
				btnManage.setEnabled(true);
				newstaff.comboBoxSupervisor.removeItem(deleteStaff.getDeleteStaff());
				
				//System.out.print(deleteStaff.getSupervisorValue());
				int w = (Toolkit.getDefaultToolkit().getScreenSize().width - newstaff.getSize().width) / 2;
				int h = (Toolkit.getDefaultToolkit().getScreenSize().height - newstaff.getSize().height) / 2;
				newstaff.setLocation(w, h);// window pop on screen center .
				newstaff.setResizable(false);
				
			}
			
		});
		btnNewStaff.setBounds(49, 41, 145, 71);
		contentPane.add(btnNewStaff);
		
		
		//Manage button Listener
		btnManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				manageStaff.manageInterface(newstaff.s.getStaffList(), newstaff.s.getStaffArray());
				manageStaff.setVisible(true);
				int w = (Toolkit.getDefaultToolkit().getScreenSize().width - manageStaff.getSize().width) / 2;
				int h = (Toolkit.getDefaultToolkit().getScreenSize().height - manageStaff.getSize().height) / 2;
                manageStaff.setLocation(w, h);// window pop on screen center
			}
		});
		
		btnManage.setBounds(149, 145, 145, 71);
		contentPane.add(btnManage);
		
		
		

		/*
		 * deleteButton
		 */
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				deleteStaff = new DeleteInterface(newstaff.s.getStaffList(), newstaff.add.getStaff());
				deleteStaff.setVisible(true);
				//System.out.print(newstaff.add.getStaffArray().get(1).getName());
				int w = (Toolkit.getDefaultToolkit().getScreenSize().width - deleteStaff.getSize().width) / 2;
				int h = (Toolkit.getDefaultToolkit().getScreenSize().height - deleteStaff.getSize().height) / 2;
				deleteStaff.setLocation(w, h);// window pop on screen center

			}
		});// delete button event

		btnDelete.setBounds(246, 41, 145, 71);
		contentPane.add(btnDelete);

	
	}
}
