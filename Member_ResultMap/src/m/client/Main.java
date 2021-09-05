package m.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Reader;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import m.vo.DepartmentsVO;
import java.util.List;
import java.util.Map;


public class Main extends JFrame {

	private String path = "m/config/config.xml";
	private SqlSessionFactory factory;
	private DepartmentsVO dvo;
	
	private List<DepartmentsVO> dvo_list;
	
	private JPanel contentPane;
	private JTable table;
	
	private String[] column = {"부서코드","부서명","지역코드","지역명"};
	private String[][] row;
	

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
		 
		db_con(path);
		
		setBounds(100, 100, 450, 600);
		setTitle("ResultMap Test");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("컨트롤");
		menuBar.add(mnNewMenu);
		
		JMenuItem total_item = new JMenuItem("전체");
		mnNewMenu.add(total_item);
		
		JMenuItem search_item = new JMenuItem("검색");
		mnNewMenu.add(search_item);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem exit_item = new JMenuItem("종료");
		mnNewMenu.add(exit_item);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				
				System.exit(0);
				
			}
		});
		
		
		total_item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("전체 버튼");
				SqlSession session = factory.openSession();
				
				dvo_list = session.selectList("dept_loc.all");
				
				totalTableView();
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				int click = e.getClickCount();
				
				if(click == 2) {
					
					int row = table.getSelectedRow();
					System.out.println(row);
					
					dvo = dvo_list.get(row);
					
					new Info(Main.this, dvo).getTableData();
					
				}
					
			}
		});
	}
	
	private void db_con(String p) {
		try {
			Reader read = Resources.getResourceAsReader("m/config/config.xml");
			factory = new SqlSessionFactoryBuilder().build(read);
			read.close();
			System.out.println("DB 연결 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void totalTableView() {
		
	
		row = new String[dvo_list.size()][column.length];
		
		int i=0;
		
		for(DepartmentsVO dvo : dvo_list) {
			
			row[i][0] = dvo.getDepartment_id();
			row[i][1] = dvo.getDepartment_name();
			row[i][2] = dvo.getLvo().getLocation_id();
			row[i][3] = dvo.getLvo().getCity();
			
			i++;
		}
		
		table.setModel(new DefaultTableModel(row, column));
	}
	
	public void update(Map<String,String> map) {
		
		SqlSession session = factory.openSession();
		
		int update = session.update("dept_loc.modify", map);
		
		if (update > 0) {
			JOptionPane.showMessageDialog(Main.this, "Update Succsess");
		}else {
			JOptionPane.showMessageDialog(Main.this, "Update Fail");
		}
		
	}

}
