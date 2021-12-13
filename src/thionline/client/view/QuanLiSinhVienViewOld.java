//package thionline.client.view;
//
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.swing.JButton;
//import javax.swing.JFileChooser;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JSeparator;
//import javax.swing.JTable;
//import javax.swing.SwingConstants;
//import javax.swing.border.EmptyBorder;
//
//import org.apache.poi.poifs.property.Parent;
//
//import com.google.gson.Gson;
//
//import thionline.client.view.AdminView.Khoaphong;
//import thionline.client.view.AdminView.Mophong;
//import thionline.client.view.AdminView.SinhVien;
//import thionline.client.view.AdminView.Taophong;
//import thionline.client.view.AdminView.Xoaphong;
//import thionline.controller.Controller;
//import thionline.entities.Permission;
//import thionline.entities.phongthi.PhongthiData;
//import thionline.entities.sinhvien.Sinhvien;
//import thionline.proxy.Proxy;
//
//public class QuanLiSinhVienViewOld extends JFrame {
//
//	private JPanel contentPane;
//	
//	QuanLiSinhVienViewOld current;
//	Permission auth;
//	List<Sinhvien> listSv;
//	Proxy proxy;
//	Gson gson = new Gson();
//	
//	public QuanLiSinhVienViewOld(Permission auth, List<Sinhvien> listSv, Proxy proxy) {
//		
//		this.auth = auth;
//		this.listSv = listSv;
//		this.proxy = proxy;
//		this.current = this;
//		
//		
//		int row = listSv.size();
//		int col = listSv.get(0).getClass().getDeclaredFields().length;
//		String[][] data = new String[row][col];
//		String[] columnNames = new String[col];
//		
//		for (int i = 0 ; i < listSv.size(); i ++) {
//			Sinhvien sv =  listSv.get(i);
//			Method[] methods = sv.getClass().getMethods();
//			Field[] field = sv.getClass().getDeclaredFields();
//			Controller ctrl = new Controller();
//			int j = 0;
//			for (Field f: field) {
//				String rowData = ctrl.runGetter(f, sv);
//				data[i][j] = rowData;
//				
//				if (j <= columnNames.length - 1) {
//					columnNames[j] = f.getName();
//				}
//				
//				j ++;
//			}
//		}
//		
////		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 685, 524);
//        JTable jTable = new JTable(data, columnNames);
//        jTable.setBounds(30, 40, 200, 300);
//        // adding it to JScrollPane
//        JScrollPane sp = new JScrollPane(jTable);
//        sp.setBounds(10, 136, 651, 347);
//        getContentPane().add(sp);
//        
//        JPanel panel = new JPanel();
//        panel.setBounds(10, 10, 651, 116);
//        getContentPane().add(panel);
//        panel.setLayout(null);
//        
//        JLabel lblNewLabel = new JLabel("Quản lí sinh viên");
//        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
//        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        lblNewLabel.setBounds(10, 10, 651, 30);
//        panel.add(lblNewLabel);
//        
//        JSeparator separator = new JSeparator();
//        separator.setBounds(10, 68, 651, 2);
//        panel.add(separator);
//        
//        JButton btnNewButton = new JButton("Thực hiện");
////        btnNewButton.addActionListener(new Taophong());
//        btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
//        btnNewButton.setBounds(546, 80, 115, 30);
//        panel.add(btnNewButton);
//        
//        JButton btnNewButton_1 = new JButton("Sửa");
////        btnNewButton_1.addActionListener(new Khoaphong());
//        btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 14));
//        btnNewButton_1.setBounds(260, 80, 115, 30);
//        panel.add(btnNewButton_1);
//        
//        JButton btnKhaPhng = new JButton("Xóa");
////        btnKhaPhng.addActionListener(new Xoaphong());
//        btnKhaPhng.setFont(new Font("Arial", Font.PLAIN, 14));
//        btnKhaPhng.setBounds(135, 80, 115, 30);
//        panel.add(btnKhaPhng);
//        
//        
//        JLabel lblNewLabel_1 = new JLabel("Hành động");
//        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
//        lblNewLabel_1.setBounds(10, 50, 101, 13);
//        panel.add(lblNewLabel_1);
//        
//        JButton btnNewButton_1_2 = new JButton("Thêm");
//        btnNewButton_1_2.addActionListener(new Them());
//        btnNewButton_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
//        btnNewButton_1_2.setBounds(10, 80, 115, 30);
//        panel.add(btnNewButton_1_2);
//        this.setLocationRelativeTo(null);
//	}
//
//	class Them implements ActionListener {
//		public void actionPerformed( ActionEvent e ) {
//			JFileChooser fileChooser = new JFileChooser();
//    		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
//    		int result = fileChooser.showOpenDialog(null);
//    		if (result == JFileChooser.APPROVE_OPTION) {
//    		    File selectedFile = fileChooser.getSelectedFile();
//    		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
//    		    
//    		    Controller ctrl = new Controller();
//    		    List<Sinhvien> newListSv = new ArrayList<Sinhvien>();
//    		    
//    		    try {
//    	            newListSv = ctrl.readBooksFromExcelFile(selectedFile.getAbsolutePath());
//    	        }
//    	        catch (IOException e1) {e1.printStackTrace();}
//    		    
//    		    ThemSinhvienView themsv = new ThemSinhvienView(auth, newListSv, proxy, current);
//    		    themsv.setVisible(true);
//    		    
//    		}
//		}
//	}
//}
