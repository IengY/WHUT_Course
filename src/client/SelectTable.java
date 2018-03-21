package client;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import requests.CourseList;
import requests.Course;
import requests.Requests;
import requests.SelectedCourse;
import requests.User;
import start.staticValue;

import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class SelectTable extends JFrame {
	//String baseAddUrl;
	private JPanel contentPane;
	private JTable table;
	List<Course> CourseList;
	/**
	 * Create the frame.
	 */
	public SelectTable(List<Course> list) {
		this.CourseList=list;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 748, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseListener() {
			
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
					if(SelectedCourseList.isStrat==true)
					{
						JOptionPane.showMessageDialog(null, "若要添加课程请先暂停选课");
						return;
					}
					int selected=table.getSelectedRow();
					String addid=(String) table.getValueAt(selected, 12);
				//	String addUrl=baseAddUrl.replace("{suid_obj}", addid);
					CourseTableModel courseModel=(CourseTableModel) table.getModel();
					Course course=courseModel.list.get(selected);
					SelectedCourse selectedCourse =new SelectedCourse();
					selectedCourse.course=course;
					selectedCourse.addUrl=addid;
					staticValue.selectedList.add(selectedCourse);
					SelectedCourseList.refresh();
				}
			}
		});
		table.setModel(new CourseTableModel(CourseList));
		scrollPane.setViewportView(table);
		this.setVisible(true);
	}

}
class CourseTableModel implements TableModel
{
	public List<Course>list;
	public CourseTableModel(List<Course>arrayList) {
		// TODO Auto-generated constructor stub
		this.list=arrayList;
	}
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		// TODO Auto-generated method stub
		if(arg0==9)
			return double.class;
		else if(arg0>=5&&arg0<=7)
			return int.class;
		else 
			return String.class;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return Course.getC();
	}

	@Override
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		switch(arg0)
		{
		case 0:return "课程名称";
		case 1:return "上课老师";
		case 2:return "上课时间";
		case 3:return "上课地点";
		case 4:return "起止周";
		case 5:return "容量";
		case 6:return "选上";
		case 7:return "本轮已选";
		case 8:return "选课方式";
		case 9:return "学分";
		case 10:return "备注";
		case 11:return "双语等级";
		case 12:return "addUrl";
		}
		return null;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		Course course=list.get(arg0);
		switch(arg1)
		{
			case 0:return course.课程名称;
			case 1:return course.上课时间;
			case 2:return course.上课时间;
			case 3:return course.上课地点;
			case 4:return course.起止周;
			case 5:return course.容量;
			case 6:return course.选上;
			case 7:return course.本轮已选;
			case 8:return course.选课方式;
			case 9:return course.学分;
			case 10:return course.备注;
			case 11:return course.双语等级;
			case 12:return course.addAction;
		}
		return "NULL";
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
}
