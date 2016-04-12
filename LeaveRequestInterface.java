import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;

@SuppressWarnings("serial")
public class LeaveRequestInterface extends JFrame {
    
	private JPanel contentPane;
	JLabel staffNameLabel;
	JLabel label;
	JLabel labelLevel;
	JTable table;
	String dateString;
	String supervisorName;
	String date;
	String applyValue;
	JEditorPane datefrom;
	JEditorPane dateto;
	JButton confirm;
	DefaultTableModel model; // table model
	Calendar cal = new GregorianCalendar(); // Calendar !

	/**
	 * Create the frame.
	 */
	   public  void leaveRequestInterface(JTable t ,int row) {
		setTitle("Leave Request");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 300, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelLevel = new JLabel("Staff :");
		this.labelLevel=labelLevel;
		this.labelLevel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.labelLevel.setFont(new Font("宋体", Font.BOLD, 18));
		this.labelLevel.setBounds(39, 72, 90, 18);
		contentPane.add(labelLevel);

		JLabel DateFrom = new JLabel("From Date :");
		DateFrom.setHorizontalAlignment(SwingConstants.RIGHT);
		DateFrom.setFont(new Font("宋体", Font.BOLD, 18));
		DateFrom.setBounds(14, 138, 115, 18);
		contentPane.add(DateFrom);

		JLabel DateTo = new JLabel("To Date :");
		DateTo.setHorizontalAlignment(SwingConstants.RIGHT);
		DateTo.setFont(new Font("宋体", Font.BOLD, 18));
		DateTo.setBounds(39, 204, 90, 18);
		contentPane.add(DateTo);

		JLabel StaffName = new JLabel("");
		this.staffNameLabel = StaffName;
		this.staffNameLabel.setFont(new Font("宋体", Font.BOLD, 18));
		this.staffNameLabel.setBounds(159, 72, 109, 18);
		contentPane.add(StaffName);

		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			     
				if(datefrom.getText().equals("")||datefrom.getText()==null||dateto.getText().equals("")||dateto.getText()==null)
					JOptionPane.showMessageDialog(null, "Please Select Date");
				else
				{
				JOptionPane.showMessageDialog(null, "Apply Successful!");
				t.setValueAt("yes", row,3); // means staff already applied! 
				t.setValueAt(datefrom.getText(), row, 4);
				t.setValueAt(dateto.getText(), row, 5);
				dispose(); // close window 
				}}
				//SupervisorHandle sh=new SupervisorHandle(getStaffNameLabel().getText().toString());
			
		});  //Apply Button Events;
		btnApply.setBounds(86, 291, 113, 27);
		contentPane.add(btnApply);

		datefrom = new JEditorPane();
		datefrom.setBounds(159, 138, 106, 24);
		contentPane.add(datefrom);

		dateto = new JEditorPane();
		dateto.setBounds(159, 198, 106, 24);
		contentPane.add(dateto);

		// Set Leave start time
		datefrom.addMouseListener((new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 1)

				{
					// dc.setVisible(true);
					diaLog(datefrom);

				}
			}

		}));

		// set end time ;
		dateto.addMouseListener((new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 1)

				{
					// dc.setVisible(true);
					diaLog(dateto);

				}
			}

		}));

	}

	

	public void diaLog(JEditorPane jPane) {  
		/*
		 * Calendar Pane :
		 */

		JDialog dialog = new JDialog();

		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);

		confirm = new JButton("Confirm");// Confirm button

		JButton b1 = new JButton("<-");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				cal.add(Calendar.MONTH, -1);
				updateMonth();
			}
		});

		JButton b2 = new JButton("->");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				cal.add(Calendar.MONTH, +1);
				updateMonth();
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(b1, BorderLayout.WEST);
		panel.add(label, BorderLayout.CENTER);
		panel.add(b2, BorderLayout.EAST);

		String[] columns = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		model = new DefaultTableModel(null, columns);
		table = new JTable(model);
		table.setCellSelectionEnabled(true);
		JScrollPane pane = new JScrollPane(table);

		dialog.getContentPane().add(panel, BorderLayout.NORTH);
		dialog.getContentPane().add(pane, BorderLayout.CENTER);
		dialog.getContentPane().add(confirm, BorderLayout.SOUTH);
		dialog.setSize(300, 250);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);

		this.updateMonth(); // Update label;

		/*
		 * confirm button events;
		 */
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
               // System.out.print(s.getStaffList());
				
				
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				if (row == -1||col==-1) {
					JOptionPane.showMessageDialog(null, "Please Select a date");
				} 
				else 
				{String s = table.getValueAt(row, col).toString();
				String dataString = s + "-" + getString();
				
				if (jPane == datefrom)  //set date
					datefrom.setText(dataString);
				else if (jPane == dateto) // set date
					dateto.setText(dataString);

				dialog.setVisible(false);}

			}

		});

	}

	void updateMonth() {
		cal.set(Calendar.DAY_OF_MONTH, 1);

		String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
		int year = cal.get(Calendar.YEAR);
		// if(year<2016)
		label.setText(month + "-" + year);
		label.setVisible(true);
		setString(label.getText());

		int startDay = cal.get(Calendar.DAY_OF_WEEK);
		int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);

		model.setRowCount(0);
		model.setRowCount(weeks);

		int i = startDay - 1;
		for (int day = 1; day <= numberOfDays; day++) {
			model.setValueAt(day, i / 7, i % 7); // seven days on one row..
			i = i + 1;
		}

	}

	public void setDate(String s) {

		this.date = s;
	}

	public String getDate() {
		return date;
	}

	public void setString(String s) {
		this.dateString = s;

	}

	public String getString() {

		return dateString;
	}

	public JLabel getStaffNameLabel() {
		return staffNameLabel;
	}
	
	public JLabel getLevel(){
		
		return labelLevel;
	}
	public void setApplyValue(String applyValue)
	{
		this.applyValue=applyValue;
	}
	
	public String getApplyValue()
	{
		return applyValue;
	}
	
	
}
