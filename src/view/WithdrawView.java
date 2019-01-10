package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;

@SuppressWarnings("serial")
public class WithdrawView extends JPanel implements ActionListener {

	private ViewManager manager;			// manages interactions between the views, model, and database
	private JButton loginButton;			// button that redirects users to the HomeView (if credentials match)
	private JButton createButton;			// button that directs users to the CreateView
	private JButton powerButton;			// button that powers off the ATM
	private JTextField accountField;		// textfield where the user enters his or her account number
	private JPasswordField pinField;		// textfield where the user enters his or her PIN
	private JLabel errorMessageLabel;		// label for potential error messages
	public DepositView(ViewManager manager) {
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
		this.add(new javax.swing.JLabel("DepositView", javax.swing.SwingConstants.CENTER));
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}