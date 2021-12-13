package thionline.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import thionline.controller.Controller;
import thionline.entities.Permission;
import thionline.entities.phongthi.PhongthiData;
import thionline.entities.sinhvien.Sinhvien;
import thionline.entities.sinhvien.SinhvienPackage;
import thionline.proxy.Proxy;

public class ThemSinhvienView extends JFrame {

	private JPanel contentPane;
	
	ThemSinhvienView current;
	QuanLiSinhVienView oldQlsv;
	Permission auth;
	List<Sinhvien> listSv;
	Proxy proxy;
	Gson gson = new Gson();
	
	public ThemSinhvienView(Permission auth, List<Sinhvien> listSv, Proxy proxy, QuanLiSinhVienView oldQlsv) {
		
		this.auth = auth;
		this.listSv = listSv;
		this.proxy = proxy;
		this.oldQlsv = oldQlsv;
		this.current = this;
		
		int row = listSv.size();
		int col = listSv.get(0).getClass().getDeclaredFields().length;
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
		
		setBounds(100, 100, 685, 524);
        JTable jTable = new JTable(data, columnNames);
        jTable.setBounds(30, 40, 200, 300);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(jTable);
        sp.setBounds(10, 136, 651, 347);
        getContentPane().add(sp);
        
        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 651, 116);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Thêm sinh viên");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 10, 651, 30);
        panel.add(lblNewLabel);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(10, 68, 651, 2);
        panel.add(separator);
        
        JButton btnNewButton = new JButton("Thực hiện");
        btnNewButton.addActionListener(new Thuchien());
//        btnNewButton.addActionListener(new Taophong());
        btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
        btnNewButton.setBounds(10, 80, 115, 30);
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Hủy");
        btnNewButton_1.addActionListener(new Huy());
//        btnNewButton_1.addActionListener(new Khoaphong());
        btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 14));
        btnNewButton_1.setBounds(135, 80, 115, 30);
        panel.add(btnNewButton_1);
        
        
        JLabel lblNewLabel_1 = new JLabel("Hành động");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(10, 50, 101, 13);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Preview");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_2.setBounds(611, 119, 50, 13);
        panel.add(lblNewLabel_2);
        this.setLocationRelativeTo(null);
	}
	
	class Thuchien implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			int dialogResult = JOptionPane.showConfirmDialog (null, "Bạn có muốn thêm sinh viên không?", "Warning", 0);
			if(dialogResult == JOptionPane.YES_OPTION){
				
				SinhvienPackage svPkg = new SinhvienPackage(auth, listSv);
				String request = "LuuSinhVien_" + gson.toJson(svPkg);
				String response = proxy.sender(request);
				
				if (!new String("null").equals(response)) {
					JOptionPane.showMessageDialog(null, "Thêm sinh viên thành công");
					Controller ctrl = new Controller(response);
					List<Sinhvien> ListSv = ctrl.getListSinhvien();
					
					QuanLiSinhVienView qlsv = new QuanLiSinhVienView(auth, ListSv, proxy);
					current.dispose();
					oldQlsv.dispose();
					qlsv.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
				}
				
			}
		}
	}
	
	class Huy implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			current.dispose();
		}
	}
}
