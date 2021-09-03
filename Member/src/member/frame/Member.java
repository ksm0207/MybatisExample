package member.frame;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Reader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import member.vo.MemberVO;

import java.util.List;
import java.util.LinkedHashMap;

public class Member extends JFrame {

	SqlSessionFactory factory;
	
	private JFrame frame;
	private JButton search_btn;
	private JButton insert_btn;
	private JButton total_btn;
	private JTable table;
	
	String[] column = {"사번","이름","입사일","이메일","직종","부서코드"};
	String[][] row;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Member window = new Member();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Member() {
		frame = new JFrame();
		frame.setBounds(100, 100, 496, 539);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		total_btn = new JButton("전체보기");
		panel.add(total_btn);
		
		search_btn = new JButton("사원 검색");
		panel.add(search_btn);
		
		insert_btn = new JButton("사원 추가");
		panel.add(insert_btn);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		db_con();
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				
				System.exit(0);
			}
		});
		
		total_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("전체보기");
				
				SqlSession session = factory.openSession();
				
				List<MemberVO> list = session.selectList("member.all");
				
				row = new String[list.size()][column.length];
				
				int i=0;
				for(MemberVO vo : list) {
					
					row[i][0] = vo.getEmployee_id();
				}
				
				System.out.println(session);
				
				
			}
		});
	}
	
	private void db_con() {
		try {
			Reader reader = Resources.getResourceAsReader("member/config/config.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
			setTitle("DB Connection !");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	

}
