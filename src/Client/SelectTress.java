package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
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

import requests.AddCourseClass;
import requests.Course;
import requests.CourseTypeList;
import requests.ParseMapFile;
import requests.SelectedCourse;
import requests.User;

import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.BoxLayout;
import javax.swing.JTable;

public class SelectTress extends JFrame {
	String base="src\\cache";
	//String basePath="F:\\Workspaces\\Test\\src\\cache\\course";
	String basePath="src\\cache\\course";
	//public static User user;
	private List<SelectedCourse> selectedList;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static DefaultMutableTreeNode traverseFolder(String path) throws IOException
	{
		DefaultMutableTreeNode retNode=new DefaultMutableTreeNode(new File(path).getName());
		File file = new File(path);
		if(file.exists())
		{
			File[] files = file.listFiles();
			if(files.length==0)
			{
				if(file.isDirectory())
				{
					DefaultMutableTreeNode dn = new DefaultMutableTreeNode(file.getName(),false);
					return dn;
				}
			}else
			{
				for(File file2 :files)
				{
					if(file2.isDirectory()==true)
					{
						DefaultMutableTreeNode file2Node = new DefaultMutableTreeNode(file2.getName());
						String mapFile=file2.getAbsolutePath();
						ParseMapFile parseMapFile =new ParseMapFile(mapFile);
						List<CourseTypeList>list=parseMapFile.getList();
						for(CourseTypeList course : list)
						{
							DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(course);
							file2Node.add(tempNode);
						}
						retNode.add(file2Node);
					}
				}
			}
		}
		return retNode;
	}
	
	
	
	/**
	 * Create the frame.
	 * @throws Throwable 
	 */
	public SelectTress(List<SelectedCourse> list) throws Throwable {
		this.selectedList=list;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "name_512061964185745");
		DefaultMutableTreeNode node = traverseFolder(basePath);
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
						String str=base;
						for(int i=0;i<selPath.getPathCount()-1;++i)
						{
							str+="\\"+selPath.getPath()[i];
						}
						str+="\\"+((CourseTypeList)model.getUserObject()).key+".json";
						try {
							AddCourseClass addCourseClass=AddCourseClass.ParseCourseClass(str);
							new SelectTable(addCourseClass.classes,addCourseClass.addclass,selectedList);
						} catch (Throwable e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//SelectTress.this.dispose();
					}
				}
				
			}
		});
		scrollPane.setViewportView(tree);
		this.setVisible(true);
	}
}
