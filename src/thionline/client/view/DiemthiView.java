package thionline.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DesktopIconUI;
import javax.swing.plaf.basic.BasicViewportUI;

import org.apache.poi.xslf.model.geom.CosExpression;

import com.google.gson.Gson;

import thionline.client.view.MainView.Bangdiem;
import thionline.controller.Controller;
import thionline.entities.Permission;
import thionline.entities.dethi.Cauhoi;
import thionline.entities.dethi.CauhoiPackage;
import thionline.entities.diemthi.Diemthi;
import thionline.entities.diemthi.DiemthiPackage;
import thionline.entities.login.Login;
import thionline.entities.login.LoginData;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSeparator;
import javax.management.ObjectInstance;
import javax.swing.DesktopManager;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;

public class DiemthiView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	
	DiemthiView current;
	Permission auth;
	List<Diemthi> diemthi;
	Proxy proxy;
	Gson gson = new Gson(); 
	private JTextField textField;
	
	public DiemthiView(Permission auth, List<Diemthi> diemthi, Proxy proxyService) {
	
		this.auth = auth;
		this.diemthi = diemthi;
		this.proxy = proxyService;
		this.current = this;
				
		int row = diemthi.size();
		int col = diemthi.get(0).getClass().getDeclaredFields().length;
		String[][] data = new String[row][col];
		String[] columnNames = new String[col];
		
		for (int i = 0 ; i < diemthi.size(); i ++) {
			Diemthi p =  diemthi.get(i);
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
		setBounds(100, 100, 685, 473);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(10, 10, 651, 116);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Bảng điểm sinh viên");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 10, 651, 30);
        panel.add(lblNewLabel);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(10, 68, 651, 2);
        panel.add(separator);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(10, 10, 651, 41);
        panel.add(panel_2);
        final JTable jTable = new JTable(data, columnNames);
        jTable.setBounds(30, 40, 200, 300);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(jTable);
        panel.add(sp);
        sp.setBounds(10, 108, 651, 315);
        
        JButton btnNewButton = new JButton("Search");
        btnNewButton.addActionListener(new Search());
        btnNewButton.setBounds(125, 76, 86, 28);
        panel.add(btnNewButton);
        
        textField = new JTextField();
        textField.setBounds(10, 76, 112, 28);
        panel.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Nhập MASV");
        lblNewLabel_1.setBounds(10, 52, 112, 16);
        panel.add(lblNewLabel_1);
        
        JButton btnNewButton_1 = new JButton("Export");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JFileChooser fileChooser = new JFileChooser();
        		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        		int result = fileChooser.showOpenDialog(null);
        		if (result == JFileChooser.APPROVE_OPTION) {
        		    File selectedFile = fileChooser.getSelectedFile();
        		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        		    
        		    if (selectedFile.getName().toLowerCase().endsWith(".xlsx")) {
        		    	Path path = Paths.get(selectedFile.getAbsolutePath());
        		    	
        		    	Controller ctrl = new Controller();
        		    	try {
        		    		ctrl.writeToExcell(jTable, path);
        		    	} catch (FileNotFoundException e1) {
        		    		// TODO Auto-generated catch block
        		    		e1.printStackTrace();
        		    		JOptionPane.showMessageDialog(null, "Không tìm thấy file");
        		    		return;
        		    	} catch (IOException e1) {
        		    		// TODO Auto-generated catch block
        		    		e1.printStackTrace();
        		    		JOptionPane.showMessageDialog(null, "Không thể export, Có lỗi xảy ra");
        		    		return;
        		    	}
        		    	
        		    	JOptionPane.showMessageDialog(null, "Export thành công");
        		    	
        		    } else {
        		    	JOptionPane.showMessageDialog(null, "Vui lòng chọn file excel có định dạng xlsx!");
        		    }
        		  
        		    
        		}
        		
        	}
        });
        btnNewButton_1.setBounds(575, 76, 86, 28);
        panel.add(btnNewButton_1);
        this.setLocationRelativeTo(null);
	}
	
	class Search implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			if (!textField.getText().equals("")) {
				String request = "SearchByMasv_" + textField.getText();
				String response = proxy.sender(request);
				
				if (!new String("null").equals(response)) {
					Controller ctrl = new Controller(response);
					List<Diemthi> listDiemthi = ctrl.getListDiemthi();
					DiemthiView diemthiView = new DiemthiView(auth, listDiemthi, proxy);
					diemthiView.setVisible(true);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sinh viên");
			}
		}
	}
}
