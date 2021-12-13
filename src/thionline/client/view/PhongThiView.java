package thionline.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DesktopIconUI;

import org.apache.poi.xslf.model.geom.CosExpression;

import com.google.gson.Gson;

import thionline.controller.Controller;
import thionline.entities.Permission;
import thionline.entities.dethi.Cauhoi;
import thionline.entities.dethi.CauhoiPackage;
import thionline.entities.login.Login;
import thionline.entities.login.LoginData;
import thionline.entities.phongthi.PhongthiData;
import thionline.entities.phongthi.PhongthiPackage;
import thionline.entities.sinhvien.Sinhvien;
import thionline.entities.sinhvien.SinhvienPackage;
import thionline.proxy.Proxy;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSeparator;
import javax.management.ObjectInstance;
import javax.swing.DesktopManager;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PhongThiView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	
	PhongThiView current;
	Permission auth;
	List<PhongthiData> pData;
	Proxy proxy;
	Gson gson = new Gson(); 
	
	public PhongThiView(Permission auth, List<PhongthiData> pData, Proxy proxyService) {
	
		this.auth = auth;
		this.pData = pData;
		this.proxy = proxyService;
		this.current = this;
				
		int row = pData.size();
		int col = pData.get(0).getClass().getDeclaredFields().length;
		String[][] data = new String[row][col];
		String[] columnNames = new String[col];
		
		for (int i = 0 ; i < pData.size(); i ++) {
			PhongthiData p =  pData.get(i);
			Method[] methods = p.getClass().getMethods();
			Field[] field = p.getClass().getDeclaredFields();
			Controller ctrl = new Controller();
			int j = 0;
			for (Field f: field) {
				String rowData = ctrl.runGetter(f, p);
				data[i][j] = rowData;
				
				if (j <= columnNames.length - 1) {
					columnNames[j] = f.getName();
				}
				
				j ++;
			}
		}
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 524);
        JTable jTable = new JTable(data, columnNames);
        jTable.setBounds(30, 40, 200, 300);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(jTable);
        sp.setBounds(10, 136, 651, 347);
        getContentPane().add(sp);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(10, 10, 651, 116);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Danh sách phòng thi");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 10, 651, 30);
        panel.add(lblNewLabel);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(10, 68, 651, 2);
        panel.add(separator);
        
        JButton btnNewButton = new JButton("Tạo");
        btnNewButton.addActionListener(new Taophong());
        btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
        btnNewButton.setBounds(10, 80, 115, 30);
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Khóa");
        btnNewButton_1.addActionListener(new Khoaphong());
        btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 14));
        btnNewButton_1.setBounds(385, 80, 115, 30);
        panel.add(btnNewButton_1);
        
        JButton btnKhaPhng = new JButton("Xóa ");
        btnKhaPhng.addActionListener(new Xoaphong());
        btnKhaPhng.setFont(new Font("Arial", Font.PLAIN, 14));
        btnKhaPhng.setBounds(135, 80, 115, 30);
        panel.add(btnKhaPhng);
        
        JButton btnNewButton_1_1 = new JButton("Mở");
        btnNewButton_1_1.addActionListener(new Mophong());
        btnNewButton_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
        btnNewButton_1_1.setBounds(260, 80, 115, 30);
        panel.add(btnNewButton_1_1);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(10, 68, 651, 56);
        panel.add(panel_1);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(10, 10, 651, 48);
        panel.add(panel_2);
        this.setLocationRelativeTo(null);
	}
	
	
	class Taophong implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			TaoPhongthiView taoPthi = new TaoPhongthiView(auth, pData, proxy, current);
			taoPthi.setVisible(true);
		}
	 }
	
	class Xoaphong implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			XoaphongthiView xoaPthi = new XoaphongthiView(auth, pData, proxy, current);
			xoaPthi.setVisible(true);
		}
	}
	
	class Mophong implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			MoPhongthiView moPthi = new MoPhongthiView(auth, pData, proxy, current);
			moPthi.setVisible(true);
		}
	}
	
	class Khoaphong implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			KhoaPhongthiView khoaPthi = new KhoaPhongthiView(auth, pData, proxy, current);
			khoaPthi.setVisible(true);
		}
	}
	
	
}
