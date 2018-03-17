package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import requests.ParseTimetable;
import requests.TimetableCourse;

import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;

public class Timetable extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String json="[\r\n" + 
							"		{\r\n" + 
							"			\"name\": \"\\u901a\\u7528\\u5b66\\u672f\\u82f1\\u8bed\", \r\n" + 
							"			\"id\": {\"row\": 1, \"col\": 1}, \r\n" + 
							"			\"start_time\": 1, \"end_time\": 16, \r\n" + 
							"			\"location\": \"\\u65b04-301\", \r\n" + 
							"			\"teacher\": \"\\u4e07\\u5b5c\\u8001\\u5e08\",\r\n" + 
							"			 \"isDivide\": 0\r\n" + 
							"		},\r\n" + 
							"		{\r\n" + 
							"			\"name\": \"\\u901a\\u7528\\u5b66\\u672f\\u82f1\\u8bed\", \r\n" + 
							"			\"id\": {\"row\": 1, \"col\": 1}, \r\n" + 
							"			\"start_time\": 1, \"end_time\": 16, \r\n" + 
							"			\"location\": \"\\u65b04-301\", \r\n" + 
							"			\"teacher\": \"\\u4e07\\u5b5c\\u8001\\u5e08\",\r\n" + 
							"			 \"isDivide\": 0\r\n" + 
							"		}\r\n" + 
							"]";
					List<TimetableCourse>list=ParseTimetable.parseTimetable(json);
					Timetable frame = new Timetable(list);
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
	public Timetable(List<TimetableCourse>list) 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 764, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		table = new JTable();
		for(int i=1;i<table.getColumnCount();++i)
		{
			table.getColumnModel().getColumn(i).setPreferredWidth(80);
		}
		for(int i=0;i<5;++i)
		{
			table.setRowHeight(50);
		}
		table.setModel(new TimeTableModel(list));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		scrollPane.setViewportView(table);
		this.setVisible(true);
	}
}
class TimeTableModel implements TableModel
{
	List<TimetableCourse>list;
	Vector<List<List<TimetableCourse>>>vector;
	public TimeTableModel(List<TimetableCourse>list) {
		// TODO Auto-generated constructor stub
		this.list=list;
		//init vector
		vector=new Vector();
		for(int i=0;i<7;++i)
		{
			List<List<TimetableCourse>>tempList;
			tempList=new ArrayList();
			for(int j=0;j<5;++j)
			{
				tempList.add(new ArrayList());
			}
			vector.add(tempList);
		}
		for(int i=0;i<list.size();++i)
		{
			TimetableCourse course=list.get(i);
			int c=course.getC();
			int r=course.getR();
			vector.get(c).get(r).add(course);
		}
		//init end
	}
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		switch(arg0)
		{
		case 0:return "周一";
		case 1:return "周二";
		case 2:return "周三";
		case 3:return "周四";
		case 4:return "周五";
		case 5:return "周六";
		case 6:return "周日";
		}
		return null;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		List<TimetableCourse>timetableCoursesList=vector.get(arg1).get(arg0);
		String showStr="<html>";
		for(TimetableCourse course:timetableCoursesList)
		{
			showStr+=course.toString()+"<br>";
		}
		showStr+="</html>";
		return showStr;
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
