package controller;

import java.awt.CardLayout;
import java.awt.Container;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import data.Database;
import model.BankAccount;
import view.ATM;
import view.LoginView;

public class ViewManager {
	
	private Container views;				// the collection of all views in the application
	private Database db;					// a reference to the database
	private BankAccount account;			// the user's bank account
	private BankAccount destination;		// an account to which the user can transfer funds
	
	/**
	 * Constructs an instance (or object) of the ViewManager class.
	 * 
	 * @param layout
	 * @param container
	 */
	
	public ViewManager(Container views) {
		this.views = views;
		this.db = new Database();
	}
	
	///////////////////// INSTANCE METHODS ////////////////////////////////////////////
	
	/**
	 * Routes a login request from the LoginView to the Database.
	 * 
	 * @param accountNumber
	 * @param pin
	 */
	
	public void updateAcc(BankAccount account) {
		db.updateAccount(account);
	}
	
	public void ClosingAcc(BankAccount account) {
		int choice = JOptionPane.showConfirmDialog(
				views,
				"Are you sure you want to close your account?",
				"Shutdown ATM",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
			);
			
			if (choice == 0) {
				db.closeAccount(account);
				switchTo(ATM.LOGIN_VIEW);
			}
		
		
	}
	public boolean updateTransAcc(BankAccount account) {
		if(db.updateAccount(account)  == true){
			return true;
		}else {
			return false;
		}
		
	}

	public BankAccount getAccount(long accountNumber) {
		return db.getAccount(accountNumber);
	}
	
	public long getMax() throws SQLException{
		return db.getMaxAccountNumber();
	}

	public void login(String accountNumber, char[] pin) {
		try {
			account = db.getAccount(Long.valueOf(accountNumber), Integer.valueOf(new String(pin)));
			
			if (account == null || account.getStatus() == 'N') {
				LoginView lv = ((LoginView) views.getComponents()[ATM.LOGIN_VIEW_INDEX]);
				lv.updateErrorMessage("Invalid account number and/or PIN.");
			} else {
				sendBankAccount(account, "home");
				sendBankAccount(account, "withdraw");
				sendBankAccount(account, "transfer");
				sendBankAccount(account, "deposit");
				sendBankAccount(account, "information");
				switchTo(ATM.HOME_VIEW);
				
				LoginView lv = ((LoginView) views.getComponents()[ATM.LOGIN_VIEW_INDEX]);
				lv.updateErrorMessage("");
			}
		} catch (NumberFormatException e) {
			// ignore
		}
	}
	public void logout() {			
			int choice = JOptionPane.showConfirmDialog(
				views,
				"Are you sure?",
				"Shutdown ATM",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
			);
			
			if (choice == 0) {
				account = null;
				switchTo(ATM.LOGIN_VIEW);
		} 
	}
	public void newnum(BankAccount newacc) {
		JOptionPane.showMessageDialog(views, "Your account number: " + newacc.getAccountNumber());
		account = null;
		switchTo(ATM.LOGIN_VIEW);
	}
	
	/**
	 * Switches the active (or visible) view upon request.
	 * 
	 * @param view
	 */
	
	public void switchTo(String view) {
		((CardLayout) views.getLayout()).show(views, view);
	}
	
	/**
	 * Routes a shutdown request to the database before exiting the application. This
	 * allows the database to clean up any open resources it used.
	 */
	
	public void shutdown() {
		try {			
			int choice = JOptionPane.showConfirmDialog(
				views,
				"Are you sure?",
				"Shutdown ATM",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
			);
			
			if (choice == 0) {
				db.shutdown();
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*public void closing(BankAccount account) {			
		
	} 
}*/
	
	public void sendBankAccount(BankAccount account, String view) {
		switch (view) {
		case "home":
			view.HomeView hv = ((view.HomeView) views.getComponents()[ATM.HOME_VIEW_INDEX]);
			hv.setBankAccount(account);
			break;
		case "deposit":
			view.DepositView dv = ((view.DepositView) views.getComponents()[ATM.DEPOSIT_VIEW_INDEX]);
			dv.setBankAccount(account);
			break;
		case "withdraw":
			view.WithdrawView wv = ((view.WithdrawView) views.getComponents()[ATM.WITHDRAW_VIEW_INDEX]);
			wv.setBankAccount(account);
			break;
		case "transfer":
			view.TransferView tv = ((view.TransferView) views.getComponents()[ATM.TRANSFER_VIEW_INDEX]);
			tv.setBankAccount(account);
			break;
		case "information":
			view.InformationView iv = ((view.InformationView) views.getComponents()[ATM.INFORMATION_VIEW_INDEX]);
			iv.setBankAccount(account);
			break;
		}
	}
	
}
