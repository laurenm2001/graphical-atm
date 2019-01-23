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
public class WithdrawView extends JPanel implements ActionListener {
	private ViewManager manager;
	private JLabel name;
	private Database db;	
	private JLabel balance;
	private JLabel accountNum;
	private BankAccount account;
	private JTextField amount;
	private JButton cancel;
	private JButton enter;
	private int checker;
	private JLabel errorMessageLabel;

	
	public WithdrawView(ViewManager manager) {
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

		this.add(new javax.swing.JLabel("WithdrawView", javax.swing.SwingConstants.CENTER));
	}
	private void initAmount() {
		JLabel label = new JLabel("Amount to Withdraw", SwingConstants.CENTER);
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
		balance.setBounds(80,0,95,35);
		balance.setFont(new Font("DialogInput", Font.BOLD, 14));
		this.add(balance);
	}
	
	private void initAccountNum() {
		accountNum = new JLabel("AccountNum", SwingConstants.RIGHT);
		accountNum.setBounds(180,0,95,35);
		accountNum.setFont(new Font("DialogInput", Font.BOLD, 14));
		this.add(accountNum);
	}
	public void setBankAccount(BankAccount account) {
		this.account = account;
		name.setText(account.getUser().getName()); 

		balance.setText(new DecimalFormat("#.##").format(account.getBalance()) + "");

	//	balance.setText(account.getBalance() + "");

		accountNum.setText(account.getAccountNumber()+"");
	}
	private void initCancel() {
		cancel = new JButton("Cancel");
		cancel.setBounds(220, 230, 100, 35);
		cancel.addActionListener(this);
		
		this.add(cancel);
	}
	private void initEnter() {
		enter = new JButton("Enter");
		enter.setBounds(220, 260, 100, 35);
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
			manager.switchTo(ATM.HOME_VIEW);
			this.removeAll();
			this.initialize();

		}else if(source.equals(enter)) {
			String amountentered = amount.getText();
			for(int i = 0; i<amountentered.length(); i++) {
				if(Character.isDigit(amountentered.charAt(i)) == false && amountentered.charAt(i)!= '.') {
					checker = 1;
					break;
				}else {
					checker = 0;
				}
			}
			if(amountentered.equals("")) {
				updateErrorMessage("Please enter a valid amount");
			}else if(checker ==1) {
				updateErrorMessage("Please enter a valid amount");
			}
			else {
				double amountenter = Double.parseDouble(amountentered);
				if(amountenter < 0.01 || amountenter > account.getBalance() || checker == 1) {
					updateErrorMessage("Please enter a valid amount");
				}else {
					account.withdraw(amountenter);
					manager.updateAcc(account);
					manager.sendBankAccount(account, "home");
					manager.switchTo(ATM.HOME_VIEW);
					this.removeAll();
					this.initialize();
				}
			}
			
		}
	}
}
