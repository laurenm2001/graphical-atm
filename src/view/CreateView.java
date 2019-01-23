package view;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;
import data.Database;
import model.BankAccount;
import model.User;

@SuppressWarnings("serial")
public class CreateView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JTextField lastname;
	private JTextField firstname;
	private JLabel errorMessageLabel;
	private JTextField telephone1;
	private JTextField telephone2;
	private JTextField telephone3;
	private JTextField address;
	private JTextField city;
	private JComboBox state;
	private JTextField zip;
	private JButton createaccountbutton;
	private JPasswordField passwordfield;
	private JComboBox month;
	private JComboBox day;
	private JComboBox year;
	private JButton cancel;


	/**
	 * Constructs an instance (or object) of the CreateView class.
	 * 
	 * @param manager
	 */
	
	public CreateView(ViewManager manager) {
		super();
		
		this.manager = manager;
		this.errorMessageLabel = new JLabel("", SwingConstants.CENTER);
		initialize();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the CreateView components.
	 */
	public void updateErrorMessage(String errorMessage) {
		errorMessageLabel.setText(errorMessage);
	}
	private void initialize() {
		this.setLayout(null);
		
		initFirstName();
		initLastName();
		initbirthday();
		inittelephone();
		initaddress();
		initcity();
		initstate();
		initzip();
		initCreateAccount();
		initPinField();
		initCancel();
		initErrorMessageLabel();
		
	}
	private void initLastName() {
		JLabel label = new JLabel("Last Name", SwingConstants.CENTER);
		label.setBounds(100, 180, 95, 35);
		label.setLabelFor(lastname);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		lastname = new JTextField(30);
		lastname.setBounds(205, 180, 200, 35);
		
		this.add(label);
		this.add(lastname);
	}
	
	private void initFirstName() {
		JLabel label = new JLabel("First Name", SwingConstants.CENTER);
		label.setBounds(100, 140, 95, 35);
		label.setLabelFor(firstname);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		firstname = new JTextField(30);
		firstname.setBounds(205, 140, 200, 35);
		
		this.add(label);
		this.add(firstname);
	}
	private void initbirthday() {
		JLabel label = new JLabel("DOB (day/mon/year)", SwingConstants.CENTER);
		label.setBounds(50, 240, 150, 35);
		label.setLabelFor(month);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		Integer[] months = new Integer[40];
		int c = 0;
		for (int i = 1; i<=12; i++) {
			months[c] = i;
			c++;
		}
		month = new JComboBox<Integer>(months);
		month.setBounds(200, 240, 70, 30);
	   
		
	    month.setVisible(true);
		this.add(label);
		this.add(month);
		Integer[] days = new Integer[40];
		int x = 0;
		for (int i = 1; i<=31; i++) {
			days[x] = i;
			x++;
		}
		day = new JComboBox<Integer>(days);
		day.setBounds(280, 240, 95, 30);
		label.setLabelFor(day);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		day.setVisible(true);
		this.add(day);
		
		Integer[] years = new Integer[120];
		
		int j = 0;
		for (int i = 2019; i>=1900; i--) {
			years[j] = i;
			j++;
		}
		year = new JComboBox<Integer>(years);
		year.setBounds(380, 240, 95, 30);
		label.setLabelFor(year);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		year.setVisible(true);
		this.add(year);
	}
	//make 3 segments
	private void inittelephone() {
		JLabel label = new JLabel("Phone", SwingConstants.CENTER);
		label.setBounds(100, 280, 95, 35);
		label.setLabelFor(telephone1);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		telephone1 = new JTextField(30);
		telephone1.setBounds(205, 280, 40, 35);
		telephone2 = new JTextField(30);
		telephone2.setBounds(255, 280, 40, 35);
		telephone3 = new JTextField(30);
		telephone3.setBounds(305, 280, 60, 35);
		this.add(label);
		this.add(telephone1);
		this.add(telephone3);
		this.add(telephone2);
	}
	private void initaddress() {
		JLabel label = new JLabel("Street Address", SwingConstants.CENTER);
		label.setBounds(40, 320, 130, 35);
		label.setLabelFor(address);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		address = new JTextField(30);
		address.setBounds(205, 320, 200, 35);
		
		this.add(label);
		this.add(address);
	}
	private void initcity() {
		JLabel label = new JLabel("City", SwingConstants.CENTER);
		label.setBounds(60, 360, 95, 35);
		label.setLabelFor(city);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		city = new JTextField(30);
		city.setBounds(205, 360, 200, 35);
		
		this.add(label);
		this.add(city);
	}
	//change to dropdown
	private void initstate() {
		JLabel label = new JLabel("State", SwingConstants.CENTER);
		label.setBounds(60, 400, 95, 35);
		label.setLabelFor(state);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		String[] states = {"AL", "AK", "AZ", "AR", "CA", "CO", "DE", "DC", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", 
				"OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA",
				"WA", "WV", "WI", "WY"};
		state = new JComboBox<String>(states);
		state.setBounds(280, 400, 95, 30);
		label.setLabelFor(state);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		state.setVisible(true);
		
		this.add(label);
		this.add(state);
	}
	private void initzip() {
		JLabel label = new JLabel("ZIP", SwingConstants.CENTER);
		label.setBounds(60, 440, 95, 35);
		label.setLabelFor(zip);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		zip = new JTextField(30);
		zip.setBounds(205, 440, 200, 35);
		
		this.add(label);
		this.add(zip);
	}
	private void initPinField() {
		JLabel label = new JLabel("PIN", SwingConstants.RIGHT);
		label.setBounds(100, 80, 95, 35);
		label.setLabelFor(passwordfield);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		passwordfield = new JPasswordField(20);
		passwordfield.setBounds(205, 80, 200, 35);
		
		this.add(label);
		this.add(passwordfield);
	}
	private void initCreateAccount() {
		createaccountbutton = new JButton("Create your Account");
		createaccountbutton.setBounds(100, 0, 200, 35);
		createaccountbutton.addActionListener(this);
		
		this.add(createaccountbutton);
	}
	private void initCancel() {
		cancel = new JButton("Cancel");
		cancel.setBounds(0, 0, 100, 35);
		cancel.addActionListener(this);
		
		this.add(cancel);
	}
	private void initErrorMessageLabel() {
		errorMessageLabel.setBounds(140, 0, 500, 35);
		errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 14));
		errorMessageLabel.setForeground(Color.RED);
		
		this.add(errorMessageLabel);
	}
	

	/*
	 * CreateView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The CreateView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the CreateView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(createaccountbutton)) {
			Database Database = new Database();
			String fnamenew = firstname.getText();
			String lnamenew = lastname.getText();
			String telephonenew = telephone1.getText() + telephone2.getText() + telephone3.getText();
			int checker = 0;
			for(int i = 0; i<telephonenew.length(); i++) {
				if(Character.isDigit(telephonenew.charAt(i)) == false) {
					checker = 1;
					break;
				}else {
					checker = 0;
				}
			}
			String addressnew = address.getText();
			String citynew = city.getText();
			String zipnew = zip.getText();
			int zipcheck = 0;
			for(int i = 0; i<zipnew.length(); i++) {
				if(Character.isDigit(zipnew.charAt(i)) == false) {
					zipcheck = 1;
					break;
				}
			}
			char[] password = passwordfield.getPassword();
			
			int pinnew = -1;
			try {
				pinnew = Integer.parseInt(new String(password));
			} catch (NumberFormatException ex) {
				// ignore
			}
			int newpin = pinnew;
			int count = 0;
			while(newpin > 0) {
				newpin = newpin / 10;
				count = count + 1; 
			}
						
			String statenew = state.getSelectedItem().toString();
			String monthnew = month.getSelectedItem().toString();
			String daynew = day.getSelectedItem().toString();
			String yearnew = year.getSelectedItem().toString();
			String dobnew = yearnew + monthnew + daynew;
			long accnew = 0;
			try {
				accnew = manager.getMax() + 1;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(fnamenew.equals("") ||lnamenew.equals("")||telephonenew.equals("")||addressnew.equals("")
					|| citynew.equals("") || zipnew.equals("") || pinnew == -1 ||
					statenew.equals("") || monthnew.equals("") || daynew.equals("")) {
				updateErrorMessage("Please enter all info");
			}else if (count != 4 || checker == 1 || pinnew == -1 || telephone1.getText().length() != 3 || telephone2.getText().length() != 3 || telephone3.getText().length() != 4 || zipnew.length() != 5 || zipcheck == 1) {
				updateErrorMessage("Please enter correctly");
			}else {
			User hold = new User(pinnew, Integer.parseInt(dobnew), Long.parseLong(telephonenew),fnamenew, lnamenew, addressnew, citynew, statenew, zipnew);
			BankAccount newacc = new BankAccount('Y',accnew , 0, hold);
			Database.insertAccount(newacc);
			
			manager.newnum(newacc);
			// manager.switchTo(ATM.LOGIN_VIEW);
			}
		}else if(source.equals(cancel)){
			manager.switchTo(ATM.LOGIN_VIEW);
			this.removeAll();
			this.initialize();
		}
	}
}
