package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.ViewManager;
import data.Database;
import model.BankAccount;

@SuppressWarnings("serial")
public class HomeView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JButton logoutButton;
	private JLabel name;
	private Database db;					// a reference to the database
	//Database Database = new Database();
	private BankAccount account;	
	private JLabel balance;
	private JLabel accountNum;
	private JButton depositButton;
	private JButton withdrawButton;
	private JButton transferButton;
	private JButton closeButton;
	private JButton InformationButton;
	/**
	 * Constructs an instance (or objects) of the HomeView class.
	 * 
	 * @param manager
	 */
	
	public HomeView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the HomeView components.
	 */
	
	private void initialize() {
		this.setLayout(null);
		
		initLogoutButton();
		initname();
		initBalance();
		initAccountNum();
		initDeposit();
		initWithdraw();
		initTransfer();
		initInformation();
		initClose();
		
		this.add(new javax.swing.JLabel("HomeView", javax.swing.SwingConstants.CENTER));
		
		// TODO
		//
		// this is where you should build the HomeView (i.e., all the components that
		// allow the user to interact with the ATM - deposit, withdraw, transfer, etc.).
		//
		// feel free to use my layout in LoginView as an example for laying out and
		// positioning your components.
	}
	private void initLogoutButton() {	
		logoutButton = new JButton("Logout");
		logoutButton.setBounds(390, 0, 100, 20);
		logoutButton.addActionListener(this);
		
		this.add(logoutButton);
	}
	private void initname() {
		
		name = new JLabel("name", SwingConstants.RIGHT);
		name.setBounds(0, 0, 95, 35);
		name.setFont(new Font("DialogInput", Font.BOLD, 14));
		this.add(name);
	}
	
	/*
	 * HomeView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	private void initDeposit() {
		depositButton = new JButton("Deposit");
		depositButton.setBounds(150, 100, 200, 50);
		depositButton.setFont(new Font("DialogInput", Font.BOLD, 20));
		depositButton.addActionListener(this);
		
		this.add(depositButton);
	}
	private void initInformation() {
		InformationButton = new JButton("Information");
		InformationButton.setBounds(150, 160, 200, 50);
		InformationButton.setFont(new Font("DialogInput", Font.BOLD, 20));
		InformationButton.addActionListener(this);
		
		this.add(InformationButton);
	}
	private void initWithdraw() {
		withdrawButton = new JButton("Withdraw");
		withdrawButton.setBounds(150, 220, 200, 50);
		withdrawButton.setFont(new Font("DialogInput", Font.BOLD, 20));
		withdrawButton.addActionListener(this);
		
		this.add(withdrawButton);
	}
	private void initTransfer() {
		transferButton = new JButton("Transfer");
		transferButton.setBounds(150, 280, 200, 50);
		transferButton.setFont(new Font("DialogInput", Font.BOLD, 20));
		transferButton.addActionListener(this);
		
		
		this.add(transferButton);
	}
	private void initClose() {
		closeButton = new JButton("Close Account");
		closeButton.setBounds(150, 340, 200, 50);
		closeButton.setFont(new Font("DialogInput", Font.BOLD, 20));
		closeButton.addActionListener(this);
		
		this.add(closeButton);
	}
	
	private void initBalance() {
		balance = new JLabel("balance", SwingConstants.RIGHT);
		balance.setBounds(100,0,95,35);
		balance.setFont(new Font("DialogInput", Font.BOLD, 14));
		this.add(balance);
	}
	
	private void initAccountNum() {
		accountNum = new JLabel("balance", SwingConstants.RIGHT);
		accountNum.setBounds(200,0,95,35);
		accountNum.setFont(new Font("DialogInput", Font.BOLD, 14));
		this.add(accountNum);
	}
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The HomeView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the HomeView.
	 * 
	 * @param e
	 */
	public void setBankAccount(BankAccount account) {
		this.account = account;
		name.setText(account.getUser().getName()); 
		balance.setText(new DecimalFormat("#.##").format(account.getBalance()) + "");
		accountNum.setText(account.getAccountNumber()+"");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		if(source.equals(logoutButton)) {
			manager.logout();
		}else if(source.equals(depositButton)) {
			manager.sendBankAccount(account, "deposit");
			manager.switchTo(ATM.DEPOSIT_VIEW);
		}else if(source.equals(withdrawButton)) {
			manager.sendBankAccount(account, "withdraw");
			manager.switchTo(ATM.WITHDRAW_VIEW);
		}else if(source.equals(transferButton)) {
			manager.sendBankAccount(account, "transfer");
			manager.switchTo(ATM.TRANSFER_VIEW);
		}else if(source.equals(InformationButton)) {
			manager.sendBankAccount(account, "information");
			manager.switchTo(ATM.INFORMATION_VIEW);
		}else if(source.equals(closeButton)) {
			manager.ClosingAcc(account);
			this.removeAll();			
			this.initialize();
		}
	}

	
}
