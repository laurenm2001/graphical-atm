package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;
import data.Database;
import model.BankAccount;

@SuppressWarnings("serial")
public class TransferView extends JPanel implements ActionListener {
	private ViewManager manager;
	private JLabel name;
	private Database db;	
	private JLabel balance;
	private JLabel accountNum;
	private BankAccount account;
	private JTextField amount;
	private JTextField transfer;
	private JButton cancel;
	private JButton enter;
	
	public TransferView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	private void initialize() {
		this.setLayout(null);
		initname();
		initBalance();
		initAccountNum();
		initAmount();
		initCancel();
		initEnter();
		initAccountRecieve();
		this.add(new javax.swing.JLabel("WithdrawView", javax.swing.SwingConstants.CENTER));
	}
	private void initAccountRecieve() {
		JLabel label = new JLabel("Account to Recieve", SwingConstants.CENTER);
		label.setBounds(20, 140, 150, 35);
		label.setLabelFor(amount);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		amount = new JTextField(30);
		amount.setBounds(205, 140, 200, 35);
		
		this.add(label);
		this.add(amount);
	}
	private void initAmount() {
		JLabel label = new JLabel("Amount to Transfer", SwingConstants.CENTER);
		label.setBounds(20, 180, 150, 35);
		label.setLabelFor(transfer);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		transfer = new JTextField(30);
		transfer.setBounds(205, 180, 200, 35);
		
		this.add(label);
		this.add(transfer);
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
		balance.setText(account.getBalance() + "");
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
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source.equals(cancel)){
			manager.switchTo(ATM.HOME_VIEW);
			this.removeAll();
			this.initialize();
		
		}
	}
}
