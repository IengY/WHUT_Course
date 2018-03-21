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
import requests.ServerRequests;
import requests.User;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

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
		setBounds(100, 100, 375, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String[] list={"使用本地数据","从教务处获取数据","从服务器获取缓存数据"};
		JComboBox comboBox = new JComboBox(list);
		comboBox.setBounds(57, 148, 190, 25);
		
		contentPane.add(comboBox);
		JButton loginButton = new JButton("\u4FDD\u5B58");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user= new User(Main.this.usernameText.getText(), new String(Main.this.passwordField.getPassword()));
				//AddCourse addCourse = new AddCourse(user);
				staticValue.user=user;
				int index=comboBox.getSelectedIndex();
				switch(index)
				{
				case 0:break;
				case 1:try {
						ServerRequests.getJwcCourseToDB();
					} catch (IOException | SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}break;
				case 2:try {
						ServerRequests.getCacheCourseToDB();
					} catch (IOException | SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}break;
				}

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
		loginButton.setBounds(257, 24, 86, 149);
		contentPane.add(loginButton);
		
		JLabel lblNewLabel = new JLabel("\u8D26\u53F7");
		lblNewLabel.setBounds(10, 24, 37, 27);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u5BC6\u7801");
		label.setBounds(10, 89, 37, 27);
		contentPane.add(label);
		
		usernameText = new JTextField();
		usernameText.setBounds(57, 24, 190, 27);
		contentPane.add(usernameText);
		usernameText.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(57, 89, 190, 27);
		contentPane.add(passwordField);
		
		JLabel label_1 = new JLabel("\u6765\u6E90");
		label_1.setBounds(10, 146, 37, 27);
		contentPane.add(label_1);
	}
}
