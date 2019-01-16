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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;
import data.Database;
import model.BankAccount;

@SuppressWarnings("serial")
public class DepositView extends JPanel implements ActionListener {
	private ViewManager manager;
	private JLabel name;
	private Database db;	
	private JLabel balance;
	private JLabel accountNum;
	private BankAccount account;
	private JTextField amount;
	private JButton cancel;
	private JButton enter;
	private JLabel errorMessageLabel;
	
	public DepositView(ViewManager manager) {
		super();
		
		this.manager = manager;
		this.errorMessageLabel = new JLabel("", SwingConstants.CENTER);
		initialize();
	}
	
	public void updateErrorMessage(String errorMessage) {
		errorMessageLabel.setText(errorMessage);
	}
	private void initialize() {
		this.setLayout(null);
		initname();
		initBalance();
		initAccountNum();
		initAmount();
		initCancel();
		initEnter();
		initErrorMessageLabel();
		this.add(new javax.swing.JLabel("DepositView", javax.swing.SwingConstants.CENTER));
	}
	private void initAmount() {
		JLabel label = new JLabel("Amount to Deposit", SwingConstants.CENTER);
		label.setBounds(20, 180, 150, 35);
		label.setLabelFor(amount);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		amount = new JTextField(30);
		amount.setBounds(205, 180, 200, 35);
		
		this.add(label);
		this.add(amount);
	}
	private void initname() {
		
		name = new JLabel("name", SwingConstants.RIGHT);
		name.setBounds(0, 0, 95, 35);
		name.setFont(new Font("DialogInput", Font.BOLD, 14));
		this.add(name);
	}
	private void initBalance() {
		balance = new JLabel("balance", SwingConstants.RIGHT);
		balance.setBounds(100,0,95,35);
		balance.setFont(new Font("DialogInput", Font.BOLD, 14));
		this.add(balance);
	}
	
	private void initAccountNum() {
		accountNum = new JLabel("AccountNum", SwingConstants.RIGHT);
		accountNum.setBounds(200,0,95,35);
		accountNum.setFont(new Font("DialogInput", Font.BOLD, 14));
		this.add(accountNum);
	}
	public void setBankAccount(BankAccount account) {
		this.account = account;
		name.setText(account.getUser().getName()); 
		balance.setText(new DecimalFormat("#.##").format(account.getBalance()) + "");
		accountNum.setText(account.getAccountNumber()+"");
	}
	private void initCancel() {
		cancel = new JButton("Cancel");
		cancel.setBounds(220, 270, 150, 35);
		cancel.addActionListener(this);
		
		this.add(cancel);
	}
	private void initEnter() {
<<<<<<< HEAD
		enter = new JButton("Deposit that money!");
		enter.setBounds(220, 230, 150, 35);
=======
		enter = new JButton("Enter");
		enter.setBounds(220, 260, 100, 35);
>>>>>>> b137147601b2b1871a765eaef31a261509e47e5d
		enter.addActionListener(this);
		
		this.add(enter);
	}
	private void initErrorMessageLabel() {
		errorMessageLabel.setBounds(140, 0, 500, 35);
		errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 14));
		errorMessageLabel.setForeground(Color.RED);
		
		this.add(errorMessageLabel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source.equals(cancel)){
			manager.sendBankAccount(account, "home");
			manager.switchTo(ATM.HOME_VIEW);
			this.removeAll();
			this.initialize();
		
		}else if(source.equals(enter)) {
			String amountentered = amount.getText();
			double amountenter = Double.parseDouble(amountentered);
			if(amountentered == "") {
				updateErrorMessage("Please enter a valid amount");
			}else {
				account.deposit(amountenter);
				manager.updateAcc(account);
				manager.sendBankAccount(account, "home");
				manager.switchTo(ATM.HOME_VIEW);
			}
			this.removeAll();
			this.initialize();
		}
	}
}
