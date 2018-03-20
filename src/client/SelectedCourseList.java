package client;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import org.apache.http.client.ClientProtocolException;
import requests.Course;
import requests.Requests;
import requests.SelectedCourse;
import requests.User;
import start.staticValue;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class SelectedCourseList extends JFrame {

	public static boolean isStrat=false;
	private static JPanel contentPane;
	private static JTable table;
	private static List<SelectedCourse>selectedList;
	Requests requests = null;
	Thread thread = null;
	/**
	 * Create the frame.
	 */
	public static void refresh()
	{
		table.setModel(new SelectedCourseTableModel(selectedList));
		table.repaint();
	}
	public SelectedCourseList() throws ClientProtocolException, IOException {
		this.selectedList=staticValue.selectedList;
		thread =new Thread(requests);
		User user = staticValue.user;
		requests = new Requests(user,selectedList);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Timer timer = new Timer();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 460, 249);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new SelectedCourseTableModel(selectedList));
		scrollPane.setViewportView(table);
		
		JButton deleteButton = new JButton("delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedID=table.getSelectedRow();
				selectedList.remove(selectedID);
				SelectedCourseList.refresh();
			}
		});
		deleteButton.setBounds(250, 269, 105, 33);
		contentPane.add(deleteButton);
		
		JButton exitButton = new JButton("exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBounds(365, 269, 105, 33);
		contentPane.add(exitButton);
		JButton startButton = new JButton("start");
		JButton pauseButton = new JButton("pause");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isStrat==false)
				{
					System.out.println(user.getUsername()+"\n"+user.getPassword());
					startButton.setEnabled(false);
					pauseButton.setEnabled(true);
					deleteButton.setEnabled(false);
					isStrat=true;
					//todo
					requests.list=selectedList;
					thread=new Thread(requests);
					thread.start();
					//refresh
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							SelectedCourseList.refresh();
						}
					}, 3000,1000);
				}
			}
		});
		startButton.setBounds(20, 269, 105, 33);
		contentPane.add(startButton);
		
		pauseButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(isStrat==true)
				{
					isStrat=false;
					thread.interrupt();
					pauseButton.setEnabled(false);
					startButton.setEnabled(true);
					deleteButton.setEnabled(true);
					timer.cancel();
					//todo
				}
			}
		});
		pauseButton.setEnabled(false);
		pauseButton.setBounds(135, 269, 105, 33);
		contentPane.add(pauseButton);
		this.setVisible(true);
	}
}
class SelectedCourseTableModel implements TableModel
{
	public List<SelectedCourse>list;
	public SelectedCourseTableModel(List<SelectedCourse>arrayList) {
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
		if(arg0==4)
			return double.class;
		else if(arg0==6)
			return int.class;
		else 
			return String.class;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
	//	return Course.getC();
		return 7;
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
		case 4:return "学分";
		case 5:return "选课状态";
		case 6:return "选课次数";
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
		Course course=list.get(arg0).course;
		int loop=list.get(arg0).loop;
		String status=list.get(arg0).message;
		switch(arg1)
		{
			case 0:return course.课程名称;
			case 1:return course.上课老师;
			case 2:return course.上课时间;
			case 3:return course.上课地点;
			case 4:return course.学分;
			case 5:return status;
			case 6:return loop;
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

