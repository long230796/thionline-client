package thionline.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.google.gson.Gson;

import thionline.controller.Controller;
import thionline.entities.Permission;
import thionline.entities.dethi.Cauhoi;
import thionline.entities.sinhvien.Sinhvien;
import thionline.entities.sinhvien.SinhvienPackage;
import thionline.proxy.Proxy;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

public class QuanLiSinhVienView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
	QuanLiSinhVienView current;
	Permission auth;
	List<Sinhvien> listSv;
	Proxy proxy;
	Gson gson = new Gson();
	private JTable table;
	private JTable table_1;
	private JTextField textField_6;
	private JTextField textField_7;

	String mssv;
	private JTextField textField_8;
	
	public QuanLiSinhVienView(Permission auth, List<Sinhvien> listSv, Proxy proxy) {
		
		this.auth = auth;
		this.listSv = listSv;
		this.proxy = proxy;
		this.current = this;
		
		
		int row = listSv.size();
		final int col = listSv.get(0).getClass().getDeclaredFields().length;
		String[][] data = new String[row][col];
		String[] columnNames = new String[col];
		
		for (int i = 0 ; i < listSv.size(); i ++) {
			Sinhvien sv =  listSv.get(i);
			Method[] methods = sv.getClass().getMethods();
			Field[] field = sv.getClass().getDeclaredFields();
			Controller ctrl = new Controller();
			int j = 0;
			for (Field f: field) {
				String rowData = ctrl.runGetter(f, sv);
				data[i][j] = rowData;
				
				if (j <= columnNames.length - 1) {
					columnNames[j] = f.getName();
				}
				
				j ++;
			}
		}
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 662);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 727, 52);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản lí sinh viên");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 10, 717, 32);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 72, 727, 209);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin sinh viên");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setBounds(10, 10, 153, 15);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Họ");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_2.setBounds(44, 38, 91, 22);
		panel_1.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(145, 35, 182, 25);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Tên");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(403, 38, 80, 22);
		panel_1.add(lblNewLabel_2_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(483, 35, 182, 25);
		panel_1.add(textField_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Ngày sinh");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_2_2.setBounds(44, 70, 91, 22);
		panel_1.add(lblNewLabel_2_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(145, 67, 182, 25);
		panel_1.add(textField_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Địa chỉ");
		lblNewLabel_2_2_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_2_2_1.setBounds(44, 102, 91, 22);
		panel_1.add(lblNewLabel_2_2_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(145, 99, 182, 25);
		panel_1.add(textField_3);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Username");
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_2_1_1.setBounds(403, 70, 70, 22);
		panel_1.add(lblNewLabel_2_1_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(483, 67, 182, 25);
		panel_1.add(textField_4);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Password");
		lblNewLabel_2_1_1_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_2_1_1_1.setBounds(403, 102, 70, 22);
		panel_1.add(lblNewLabel_2_1_1_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(483, 99, 182, 25);
		panel_1.add(textField_5);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Mã vai trò");
		lblNewLabel_2_2_1_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_2_2_1_1.setBounds(44, 134, 91, 22);
		panel_1.add(lblNewLabel_2_2_1_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(145, 131, 182, 25);
		panel_1.add(textField_6);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Mã trạng thái");
		lblNewLabel_2_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_2_1_1_1_1.setBounds(403, 134, 70, 22);
		panel_1.add(lblNewLabel_2_1_1_1_1);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(483, 131, 182, 25);
		panel_1.add(textField_7);
		
		JLabel lblNewLabel_2_2_1_1_1 = new JLabel("Thi");
		lblNewLabel_2_2_1_1_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_2_2_1_1_1.setBounds(44, 171, 91, 22);
		panel_1.add(lblNewLabel_2_2_1_1_1);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(145, 168, 182, 25);
		panel_1.add(textField_8);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 293, 727, 308);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 252, 707, 47);
		panel_2.add(panel_3);
		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.addActionListener(new Them());
		
		JButton btnNewButton_1 = new JButton("Xóa");
		btnNewButton_1.addActionListener(new XoaSinhvien());
		
		JButton btnNewButton_2 = new JButton("Sửa");
		btnNewButton_2.addActionListener(new SuaSinhvien());
		
		JButton btnNewButton_3 = new JButton("Ghi/Lưu");
		
		JButton btnNewButton_4 = new JButton("Không");
		
		JButton btnNewButton_5 = new JButton("Thoát");
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap(56, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(51))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap(5, Short.MAX_VALUE)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_3)
						.addComponent(btnNewButton_4)
						.addComponent(btnNewButton_5))
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		JLabel lblNewLabel_3 = new JLabel("Bảng sinh viên");
		lblNewLabel_3.setForeground(new Color(0, 0, 255));
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 10, 125, 15);
		panel_2.add(lblNewLabel_3);
		
		
		final JTable jTable = new JTable(data, columnNames);
		jTable.setBounds(10, 34, 707, 226);
		// adding it to JScrollPane
		JScrollPane sp = new JScrollPane(jTable);
		sp.setBounds(10, 35, 707, 207);
		panel_2.add(sp);
		
		
		jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	mssv = jTable.getValueAt(jTable.getSelectedRow(), 0).toString();
	        	textField.setText(jTable.getValueAt(jTable.getSelectedRow(), 1).toString());
	        	textField_1.setText(jTable.getValueAt(jTable.getSelectedRow(), 2).toString());
	        	textField_2.setText(jTable.getValueAt(jTable.getSelectedRow(), 3).toString());
	        	textField_3.setText(jTable.getValueAt(jTable.getSelectedRow(), 4).toString());
	        	textField_4.setText(jTable.getValueAt(jTable.getSelectedRow(), 5).toString());
	        	textField_5.setText(jTable.getValueAt(jTable.getSelectedRow(), 6).toString());
	        	textField_6.setText(jTable.getValueAt(jTable.getSelectedRow(), 7).toString());
	        	textField_7.setText(jTable.getValueAt(jTable.getSelectedRow(), 8).toString());
	        	textField_8.setText(jTable.getValueAt(jTable.getSelectedRow(), 9).toString());
	        		
	        	
	        }
	    });

        
	}
	
	class Them implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			JFileChooser fileChooser = new JFileChooser();
    		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    		int result = fileChooser.showOpenDialog(null);
    		if (result == JFileChooser.APPROVE_OPTION) {
    		    File selectedFile = fileChooser.getSelectedFile();
    		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
    		    
    		    Controller ctrl = new Controller();
    		    List<Sinhvien> newListSv = new ArrayList<Sinhvien>();
    		    
    		    try {
    	            newListSv = ctrl.readBooksFromExcelFile(selectedFile.getAbsolutePath());
    	        }
    	        catch (IOException e1) {e1.printStackTrace();}
    		    
    		    ThemSinhvienView themsv = new ThemSinhvienView(auth, newListSv, proxy, current);
    		    themsv.setVisible(true);
    		    
    		}
		}
	}
	
	class XoaSinhvien implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			if (mssv != null) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "Bạn có muốn xóa không?", "Warning", 0);
				if(dialogResult == JOptionPane.YES_OPTION){
					Sinhvien sv = new Sinhvien(mssv, "", "", "", "", "", "", 0, 0, "");
					List<Sinhvien> listSv = new ArrayList<Sinhvien>();
					listSv.add(sv);
					SinhvienPackage svPkg = new SinhvienPackage(auth, listSv);
					
					String request = "XoaSinhvien_" + gson.toJson(svPkg);
					String response = proxy.sender(request);
					
					if (!new String("null").equals(response)) {
						JOptionPane.showMessageDialog(null, "Xóa sinh viên thành công!");
						Controller ctrl = new Controller(response);
						List<Sinhvien> svUpdate = ctrl.getListSinhvien();
						QuanLiSinhVienView qlsv = new QuanLiSinhVienView(auth, svUpdate, proxy);
						current.dispose();
						qlsv.setVisible(true);
					}
					
				}
			}
		}
	}
	
	class SuaSinhvien implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			if (mssv != null) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "Bạn có muốn sửa không?", "Warning", 0);
				if(dialogResult == JOptionPane.YES_OPTION){
					Sinhvien sv = new Sinhvien(mssv, textField.getText(), textField_1.getText(), textField_2.getText(), textField_3.getText(), textField_4.getText(), textField_5.getText(), Integer.valueOf(textField_6.getText()), Integer.valueOf(textField_7.getText()), textField_8.getText());
					List<Sinhvien> listSv = new ArrayList<Sinhvien>();
					listSv.add(sv);
					SinhvienPackage svPkg = new SinhvienPackage(auth, listSv);
					
					String request = "SuaSinhvien_" + gson.toJson(svPkg);
					String response = proxy.sender(request);
					
					if (!new String("null").equals(response)) {
						JOptionPane.showMessageDialog(null, "Sửa sinh viên thành công!");
						Controller ctrl = new Controller(response);
						List<Sinhvien> svUpdate = ctrl.getListSinhvien();
						QuanLiSinhVienView qlsv = new QuanLiSinhVienView(auth, svUpdate, proxy);
						current.dispose();
						qlsv.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "có lỗi xảy ra, vui lòng xem lại thông tin sinh viên");
					}
					
					
				} 
					
			}
			else {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn sinh viên");
			}
			
		}
	}
}
