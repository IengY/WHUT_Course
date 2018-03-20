package start;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.AlipayLogin;
import client.SelectTress;
import client.SelectedCourseList;
import requests.SelectedCourse;
import requests.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField usernameText;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton loginButton = new JButton("\u767B\u9646");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user= new User(Main.this.usernameText.getText(), new String(Main.this.passwordField.getPassword()));
				//AddCourse addCourse = new AddCourse(user);
				staticValue.user=user;
				try {
					new SelectTress();
					new SelectedCourseList();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Main.this.dispose();
			}
		});
		loginButton.setBounds(33, 163, 76, 46);
		contentPane.add(loginButton);
		
		JButton signButton = new JButton("\u6CE8\u518C");
		signButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		signButton.setBounds(172, 163, 84, 46);
		contentPane.add(signButton);
		
		JButton alipayLoginButton = new JButton("\u652F\u4ED8\u5B9D\u767B\u9646");
		alipayLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlipayLogin alipayLogin= new AlipayLogin();
			}
		});
		alipayLoginButton.setBounds(306, 163, 108, 46);
		contentPane.add(alipayLoginButton);
		
		JLabel lblNewLabel = new JLabel("\u8D26\u53F7");
		lblNewLabel.setBounds(66, 34, 84, 27);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u5BC6\u7801");
		label.setBounds(66, 99, 84, 27);
		contentPane.add(label);
		
		usernameText = new JTextField();
		usernameText.setBounds(160, 37, 190, 27);
		contentPane.add(usernameText);
		usernameText.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(160, 102, 190, 27);
		contentPane.add(passwordField);
	}
}
