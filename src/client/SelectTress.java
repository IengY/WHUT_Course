package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import requests.CourseList;
import requests.Course;
import requests.SelectedCourse;
import requests.User;
import sqliteDatabase.CourseSQLiteJDBC;
import start.staticValue;

import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.BoxLayout;
import javax.swing.JTable;

public class SelectTress extends JFrame {
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static DefaultMutableTreeNode traverseFolder(String table) throws IOException, SQLException
	{
		
		DefaultMutableTreeNode retNode=new DefaultMutableTreeNode(staticValue.getTableName(table));
		List<String>CourseList=CourseSQLiteJDBC.getCourseList(table);
		for(String course:CourseList)
		{
			DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(course,false);
			retNode.add(defaultMutableTreeNode);
		}
		return retNode;
	}
	public static DefaultMutableTreeNode createRootNode() throws SQLException, IOException
	{
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("Ñ¡¿ÎÐÅÏ¢");
		List<String>tableList=CourseSQLiteJDBC.getTypeList();
		for(String node:tableList)
		{
			DefaultMutableTreeNode oneNode=traverseFolder(node);
			root.add(oneNode);
		}
		return root;
	}
	
	
	/**
	 * Create the frame.
	 * @throws Throwable 
	 */
	public SelectTress() throws Throwable {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "name_512061964185745");
		DefaultMutableTreeNode node = createRootNode();
		DefaultTreeModel defaultTreeModel = new DefaultTreeModel(node);
		JTree tree = new JTree(defaultTreeModel);
		tree.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int count=arg0.getClickCount();
				if(count==2)
				{
					JTree tree = (JTree) arg0.getSource();
		            int selRow = tree.getRowForLocation(arg0.getX(), arg0.getY());
		            TreePath selPath = tree.getPathForLocation(arg0.getX(), arg0.getY());
		            DefaultMutableTreeNode model=(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		            if(model.isLeaf()==true)
					{
		            	String table=staticValue.getTable(selPath.getPath()[1].toString());
		            	String courseName=selPath.getPath()[2].toString();
						try {
							new SelectTable(CourseSQLiteJDBC.getCourse(courseName, table));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		scrollPane.setViewportView(tree);
		this.setVisible(true);
	}
}
