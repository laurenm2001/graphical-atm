package view;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;

@SuppressWarnings("serial")
public class CreateView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JTextField lastname;
	private JTextField firstname;
	private JLabel errorMessageLabel;
	private JTextField telephone;
	private JTextField address;
	private JTextField city;
	private JTextField state;
	private JTextField zip;


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
	    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		final JComboBox<String> month = new JComboBox<String>(months);
		month.setBounds(200, 240, 95, 30);
	   
		JLabel label = new JLabel("DOB", SwingConstants.CENTER);
		label.setBounds(100, 240, 95, 35);
		label.setLabelFor(month);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
	    month.setVisible(true);
		this.add(label);
		this.add(month);
		Integer[] days = new Integer[40];
		int x = 0;
		for (int i = 1; i<=31; i++) {
			days[x] = i;
			x++;
		}
		final JComboBox<Integer> day = new JComboBox<Integer>(days);
		day.setBounds(280, 240, 95, 30);
		label.setLabelFor(day);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		day.setVisible(true);
		this.add(day);
		
		Integer[] years = new Integer[40];
		int j = 0;
		for (int i = 1990; i<=2018; i++) {
			years[j] = i;
			j++;
		}
		final JComboBox<Integer> year = new JComboBox<Integer>(years);
		year.setBounds(370, 240, 95, 30);
		label.setLabelFor(year);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		year.setVisible(true);
		this.add(year);
	}
	private void inittelephone() {
		JLabel label = new JLabel("Phone", SwingConstants.CENTER);
		label.setBounds(100, 280, 95, 35);
		label.setLabelFor(telephone);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		telephone = new JTextField(30);
		telephone.setBounds(205, 280, 200, 35);
		
		this.add(label);
		this.add(telephone);
	}
	private void initaddress() {
		JLabel label = new JLabel("Street Address", SwingConstants.CENTER);
		label.setBounds(60, 320, 95, 35);
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
	
	private void initstate() {
		JLabel label = new JLabel("State", SwingConstants.CENTER);
		label.setBounds(60, 400, 95, 35);
		label.setLabelFor(state);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		state = new JTextField(30);
		state.setBounds(205, 400, 200, 35);
		
		this.add(label);
		this.add(state);
	}
	private void initzip() {
		JLabel label = new JLabel("ZIP", SwingConstants.CENTER);
		label.setBounds(60, 40, 95, 35);
		label.setLabelFor(zip);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		zip = new JTextField(30);
		zip.setBounds(205, 40, 200, 35);
		
		this.add(label);
		this.add(zip);
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
		
		// TODO
		//
		// this is where you'll setup your action listener, which is responsible for
		// responding to actions the user might take in this view (an action can be a
		// user clicking a button, typing in a textfield, etc.).
		//
		// feel free to use my action listener in LoginView.java as an example.
	}
}