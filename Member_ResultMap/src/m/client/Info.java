package m.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import m.vo.DepartmentsVO;

public class Info extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField deptno_tf;
	private JTextField d_name_tf;
	private JTextField city_no;
	private JTextField city_name;
	private JButton okButton;
	private JButton cancelButton;
	
	
	
	Main main;
	DepartmentsVO dvo;
	
	

	/**
	 * 부서코드 부서명 도시코드 도시명 가져오기
	 */
	
	public Info(Main main , DepartmentsVO dvo) {
		
		this.main = main;
		this.dvo = dvo;
		
	
		
		System.out.println(dvo.getDepartment_id());
		System.out.println(dvo.getDepartment_name());
		System.out.println(dvo.getLvo().getLocation_id());
		System.out.println(dvo.getLvo().getCity());
		
		
		
		setTitle("Info");
		setBounds(100, 100, 211, 363);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(5, 1, 0, 0));
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			contentPanel.add(panel);
			{
				JLabel lblNewLabel = new JLabel("부서코드 :");
				panel.add(lblNewLabel);
			}
			{
				deptno_tf = new JTextField();
				deptno_tf.setEditable(false);
				panel.add(deptno_tf);
				deptno_tf.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			contentPanel.add(panel);
			{
				JLabel lblNewLabel_1 = new JLabel("부서명 :");
				panel.add(lblNewLabel_1);
			}
			{
				d_name_tf = new JTextField();
				panel.add(d_name_tf);
				d_name_tf.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			contentPanel.add(panel);
			{
				JLabel lblNewLabel_2 = new JLabel("도시코드 :");
				panel.add(lblNewLabel_2);

			}
			{
				city_no = new JTextField();
				panel.add(city_no);
				city_no.setColumns(10);
				city_no.setEditable(false);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				JLabel lab = new JLabel("도시명 :");
				panel.add(lab);
			}
			{
				city_name = new JTextField();
				city_name.setColumns(10);
				panel.add(city_name);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		setVisible(true);
		getTableData();
		
		/* Event */
		
	}
	
	public void getTableData () {
		deptno_tf.setText(dvo.getDepartment_id());
		d_name_tf.setText(dvo.getDepartment_name());
		city_no.setText(dvo.getLvo().getLocation_id());
		city_name.setText(dvo.getLvo().getCity());
	}
}
