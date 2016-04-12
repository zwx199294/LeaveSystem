import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class NewStaffInterface extends JFrame {

	private JPanel contentPane;
	private JTextField textName;
	private JTextField textEmail;
	private JTextField textAccount;
	private JPasswordField textPassword;
	@SuppressWarnings("rawtypes")
	public  JComboBox comboBoxSupervisor;
	public JTable table;
	public String[] staffData;
	public Staff s = new Staff();
	public AddStaff add = new AddStaff();
	
	//DeleteInterface deleteStaff = new DeleteInterface();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public NewStaffInterface() {

		s.staffList = new ArrayList<String>();
		s.staffArray = new ArrayList<Staff>();

		setTitle("New Staffs");
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Level = new JLabel("Level");
		Level.setHorizontalAlignment(SwingConstants.CENTER);
		Level.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 18));
		Level.setBounds(78, 24, 159, 18);
		contentPane.add(Level);

		JLabel Name = new JLabel("Name");
		Name.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 18));
		Name.setHorizontalAlignment(SwingConstants.CENTER);
		Name.setBounds(78, 81, 159, 18);
		contentPane.add(Name);

		JLabel email = new JLabel("Email");
		email.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 18));
		email.setHorizontalAlignment(SwingConstants.CENTER);
		email.setBounds(78, 136, 159, 18);
		contentPane.add(email);

		JLabel account = new JLabel("Account");
		account.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 18));
		account.setHorizontalAlignment(SwingConstants.CENTER);
		account.setBounds(78, 194, 159, 18);
		contentPane.add(account);

		JLabel password = new JLabel("Password");
		password.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 18));
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setBounds(78, 255, 159, 18);
		contentPane.add(password);

		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(243, 80, 139, 24);
		contentPane.add(textName);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(243, 135, 139, 24);
		contentPane.add(textEmail);

		textAccount = new JTextField();
		textAccount.setColumns(10);
		textAccount.setBounds(243, 193, 139, 24);
		contentPane.add(textAccount);

		textPassword = new JPasswordField();
		textPassword.setColumns(10);
		textPassword.setBounds(243, 254, 139, 24);
		contentPane.add(textPassword);

		JLabel supervisor = new JLabel("Supervisor");
		supervisor.setHorizontalAlignment(SwingConstants.CENTER);
		supervisor.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 18));
		supervisor.setBounds(78, 320, 159, 18);
		supervisor.setVisible(false);
		contentPane.add(supervisor);
		
		comboBoxSupervisor = new JComboBox();
		comboBoxSupervisor.setBounds(243, 319, 100, 24);
		comboBoxSupervisor.setVisible(false);
		contentPane.add(comboBoxSupervisor);// Supervisor box..

		/*
		 * comboBox listener
		 */

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Director" }));
		comboBox.setBounds(243, 23, 100, 24);
		contentPane.add(comboBox);
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString() == "Director") {
					//comboBox.setSelectedIndex(1);
					comboBoxSupervisor.setVisible(false);
					

				} 
			}

		});

		/*
		 * Button Click Event
		 */
		JButton btnAddStaff = new JButton("Submit");
		btnAddStaff.addActionListener(new ActionListener() {
            
			public void actionPerformed(ActionEvent e) {
				   // p.parseName(s, s.staffList, textName.getText());
				    //s.setName(textName.getText());
				 if(textName.getText().equals("")||textName.getText()== null)
					JOptionPane.showMessageDialog(null, "plese write a name");
				 
				 
				 
				 else if (comboBox.getSelectedItem()=="Staff") {// p.parseName(s,s.staffList,textName.getText());
					comboBoxSupervisor.addItem(textName.getText());
					s.staffList.add(comboBox.getSelectedItem().toString() + "," + textName.getText() + ","
							+ textEmail.getText() + "," + textAccount.getText() + "," + textPassword.getPassword().toString() + ","
							+ comboBoxSupervisor.getSelectedItem());
					s.staffArray.add(s);
					JOptionPane.showMessageDialog(null, "Succcessful!");

				} 
				 
				 else if (comboBox.getSelectedItem()=="Director") {
					comboBoxSupervisor.addItem("Director");
					s.staffList.add(comboBox.getSelectedItem().toString() + "," + textName.getText() + ","
							+ textEmail.getText() + "," + textAccount.getText() + "," + textPassword.getPassword().toString() + ","
							+ " ");
					supervisor.setVisible(true);
					comboBoxSupervisor.setVisible(true);
					comboBox.addItem("Staff");
					comboBox.removeItemAt(0);
					s.staffArray.add(s);
					JOptionPane.showMessageDialog(null, "Succcessful!");
				}
				
					
					
			    add.addStaff(s, s.staffList);
				s.setStaffArray(s.staffArray); //List<Stirng>   Staff's information  array 
				s.setStaffList(s.staffList); // List<Staff> 
				
               
                textName.setText("");
                textEmail.setText("");
                textAccount.setText("");
                textPassword.setText("");//Clear Textbox...
				

			}

		});
		btnAddStaff.setBounds(178, 387, 113, 27);
		contentPane.add(btnAddStaff);
		
	}
	

}
