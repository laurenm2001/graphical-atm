 package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import controller.ViewManager;
import data.Database;
import model.BankAccount;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


@SuppressWarnings("serial")
public class InformationView extends JPanel implements ActionListener {
	private ViewManager manager;
	private JTextField fname;
	private JTextField lname;
	private Database db;	
	private BankAccount account;
	private JComboBox statedrop;
	private JTextField accountnum;
	private JTextField address;
	private JTextField city;
	private JTextField state;
	private JTextField zip;
	private JTextField dob;
	private JTextField phone;
	private JPasswordField pin;
	private JButton cancel;
	private JButton enter;
	private JButton save;
	private JButton cancel2;
	private JLabel errorMessageLabel;
	private JLabel labels;
	private JLabel labelv;
	private String selectstate;
	int i = 0;
	int j = 0;
	String[] states = {"AL", "AK", "AZ", "AR", "CA", "CO", "DE", "DC", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", 
			"OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA",
			"WA", "WV", "WI", "WY"};
	
	public InformationView(ViewManager manager) {
		super();
		
		this.manager = manager;
		this.errorMessageLabel = new JLabel("", SwingConstants.CENTER);
		initialize();
	}
	public void updateErrorMessage(String errorMessage) {
		errorMessageLabel.setText(errorMessage);
	}
	private void reinitialize() {
		pin.setEditable(true);
		city.setEditable(true);
		this.remove(state);
		statedrop.setVisible(true);
		phone.setEditable(true);
		address.setEditable(true);
		zip.setEditable(true);
		this.add(save);
		this.add(cancel2);
		this.remove(enter);
		this.remove(cancel);
		
		repaint();
	}
	
	
	private void initialize() {
		this.setLayout(null);
		initname();
		initCancel();	
		initErrorMessageLabel();
		initlastname();
		initEnter();
		initaccountnum();
		initcity();
		initstate();
		initstreet();
		initzip();
		initphone();
		initdob();
		initpin();
		initSave();
		initCancel2();
		initstatedrop();
		
		this.add(new javax.swing.JLabel("InformationView", javax.swing.SwingConstants.CENTER));
	}
	private void initializeagain() {
		pin.setEditable(false);
		city.setEditable(false);
		this.add(state);
		statedrop.setVisible(false);
		//statedrop.setEditable(false);
		phone.setEditable(false);
		address.setEditable(false);
		zip.setEditable(false);
		this.add(enter);
		this.add(cancel);
		this.remove(cancel2);
		this.remove(save);
		
		repaint();
	}
	private void initstatedrop() {
		labels = new JLabel("State", SwingConstants.CENTER);
		labels.setBounds(20, 200, 95, 35);
		labels.setLabelFor(statedrop);
		labels.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		
		statedrop = new JComboBox<String>(states);
		statedrop.setBounds(205, 200, 150, 35);
		labels.setLabelFor(statedrop);
		labels.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		
		this.add(labels);
		this.add(statedrop);
		
		
		//statedrop.setEditable(false);
		statedrop.setVisible(false);
		labels.setVisible(false);
		
	}
	private void initname() {
		
		JLabel label = new JLabel("First Name", SwingConstants.CENTER);
		label.setBounds(20, 40, 150, 35);
		label.setLabelFor(fname);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		fname = new JTextField(30);
		fname.setBounds(205, 40, 200, 35);
		fname.setEditable(false);

		
		this.add(label);
		this.add(fname);
	}
	private void initlastname() {
		
		JLabel label = new JLabel("Last Name", SwingConstants.CENTER);
		label.setBounds(20, 80, 150, 35);
		label.setLabelFor(lname);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		lname = new JTextField(30);
		lname.setBounds(205, 80, 200, 35);
		lname.setEditable(false);

		
		this.add(label);
		this.add(lname);
	}
	private void initaccountnum() {
			
			JLabel label = new JLabel("Account Number", SwingConstants.CENTER);
			label.setBounds(20, 0, 150, 35);
			label.setLabelFor(accountnum);
			label.setFont(new Font("DialogInput", Font.BOLD, 14));
			
			accountnum = new JTextField(30);
			accountnum.setBounds(205, 0, 200, 35);
			accountnum.setEditable(false);
	
			
			this.add(label);
			this.add(accountnum);
		}
	private void initstreet() {
			
			JLabel label = new JLabel("Street Address", SwingConstants.CENTER);
			label.setBounds(20, 120, 150, 35);
			label.setLabelFor(address);
			label.setFont(new Font("DialogInput", Font.BOLD, 14));
			
			address = new JTextField(30);
			address.setBounds(205, 120, 200, 35);
			address.setEditable(false);
	
			
			this.add(label);
			this.add(address);
		}
	private void initcity() {
			
			JLabel label = new JLabel("City", SwingConstants.CENTER);
			label.setBounds(20, 160, 150, 35);
			label.setLabelFor(city);
			label.setFont(new Font("DialogInput", Font.BOLD, 14));
			
			city = new JTextField(30);
			city.setBounds(205, 160, 200, 35);
			city.setEditable(false);
	
			
			this.add(label);
			this.add(city);
		}
	private void initstate() {
		
		labelv = new JLabel("State", SwingConstants.CENTER);
		labelv.setBounds(20, 200, 150, 35);
		labelv.setLabelFor(state);
		labelv.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		state = new JTextField(30);
		state.setBounds(205, 200, 200, 35);
		state.setEditable(false);

		
		this.add(labelv);
		this.add(state);
	}
	private void initzip() {
			
			JLabel label = new JLabel("ZIP Code", SwingConstants.CENTER);
			label.setBounds(20, 240, 150, 35);
			label.setLabelFor(zip);
			label.setFont(new Font("DialogInput", Font.BOLD, 14));
			
			zip = new JTextField(30);
			zip.setBounds(205, 240, 200, 35);
			zip.setEditable(false);
	
			
			this.add(label);
			this.add(zip);
		}
	private void initdob() {
		
		JLabel label = new JLabel("Date of Birth", SwingConstants.CENTER);
		label.setBounds(20, 280, 150, 35);
		label.setLabelFor(dob);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		dob = new JTextField(30);
		dob.setBounds(205, 280, 200, 35);
		dob.setEditable(false);

		
		this.add(label);
		this.add(dob);
	}
	private void initphone() {
			
			JLabel label = new JLabel("Phone", SwingConstants.CENTER);
			label.setBounds(20, 320, 150, 35);
			label.setLabelFor(phone);
			label.setFont(new Font("DialogInput", Font.BOLD, 14));
			
			phone = new JTextField(30);
			phone.setBounds(205, 320, 200, 35);
			phone.setEditable(false);
	
			
			this.add(label);
			this.add(phone);
		}
	private void initpin() {
		JLabel label = new JLabel("PIN", SwingConstants.CENTER);
		label.setBounds(20, 360, 150, 35);
		label.setLabelFor(pin);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		pin = new JPasswordField(30);
		pin.setBounds(205, 360, 200, 35);
		pin.setEditable(false);

		
		this.add(label);
		this.add(pin);
	}
	public void setBankAccount(BankAccount account) {
		this.account = account;
		fname.setText(account.getUser().getFirstName());
		lname.setText(account.getUser().getLastName());
		accountnum.setText(Long.toString(account.getAccountNumber()));
		address.setText(account.getUser().getStreetAddress());
		city.setText(account.getUser().getCity());
		state.setText(account.getUser().getState());
		zip.setText(account.getUser().getZip());
		dob.setText(Integer.toString(account.getUser().getDob()));
		phone.setText(Long.toString(account.getUser().getPhone()));
		pin.setText(Integer.toString(account.getUser().getPin()));
		selectstate = account.getUser().getState();
		for(i = 0; i<states.length; i++) {
			if(states[i].equals(selectstate)) {
				j = i;
			}
		}
		statedrop.setSelectedItem(states[j]);
	}
	private void initCancel() {
		cancel = new JButton("Cancel");
		cancel.setBounds(220, 400, 100, 35);
		cancel.addActionListener(this);
		
		this.add(cancel);
	}
	private void initCancel2() {
		cancel2 = new JButton("Cancel");
		cancel2.setBounds(220, 400, 100, 35);
		cancel2.addActionListener(this);
		
	}
	private void initEnter() {
		enter = new JButton("Edit");
		enter.setBounds(220, 440, 100, 35);
		enter.addActionListener(this);
		
		this.add(enter);
	}
	private void initSave() {
		save = new JButton("Save");
		save.setBounds(220, 440, 100, 35);
		save.addActionListener(this);
	}
	private void initErrorMessageLabel() {
		errorMessageLabel.setBounds(0, 0, 500, 35);
		errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 14));
		errorMessageLabel.setForeground(Color.RED);
		
		this.add(errorMessageLabel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source.equals(cancel)){
			manager.switchTo(ATM.HOME_VIEW);
			this.removeAll();
			this.initialize();
		
		}else if(source.equals(enter)) {
			reinitialize();
			
		}else if(source.equals(cancel2)) {
			initializeagain();
			fname.setText(account.getUser().getFirstName());
			lname.setText(account.getUser().getLastName());
			accountnum.setText(Long.toString(account.getAccountNumber()));
			address.setText(account.getUser().getStreetAddress());
			city.setText(account.getUser().getCity());
			state.setText(account.getUser().getState());
			zip.setText(account.getUser().getZip());
			dob.setText(Integer.toString(account.getUser().getDob()));
			phone.setText(Long.toString(account.getUser().getPhone()));
			pin.setText(Integer.toString(account.getUser().getPin()));
			
			repaint();
		
		}else if(source.equals(save)) {
			
			String newtele = phone.getText();
			long newphone = Long.parseLong(phone.getText());
			String newcity = city.getText();
			String newstate = statedrop.getSelectedItem().toString();
			char[] password = pin.getPassword();
			
			int pinnew = -1;
			try {
				pinnew = Integer.parseInt(new String(password));
			} catch (NumberFormatException ex) {
				// ignore
			}
			String newpost = zip.getText();
			String newaddress = address.getText();
			
			if(newtele.equals("") ||newcity.equals("")||newstate.equals("")||newpost.equals("") ||  pinnew == -1) {
				updateErrorMessage("Please enter all info");
			}else {
			
				account.getUser().setCity(newcity);
				account.getUser().setPhone(newphone);
				account.getUser().setState(newstate);
				account.getUser().setPin(account.getUser().getPin(), pinnew);
				account.getUser().setStreetAddress(newaddress);
				account.getUser().setZip(newpost);
				
				manager.updateAcc(account);
				state.setText(account.getUser().getState());
				initializeagain();
				this.add(state);
				statedrop.setVisible(false);;
			
				this.add(enter);
				this.add(cancel);
				this.remove(cancel2);
				this.remove(save);
			}
		
		}
	}
}



