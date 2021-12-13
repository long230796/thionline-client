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
//import com.google.gson.Gson;
//
//import thionline.client.view.QuanLiSinhVienViewOld.Them;
//import thionline.controller.Controller;
//import thionline.entities.Permission;
//import thionline.entities.dethi.Cauhoi;
//import thionline.proxy.Proxy;
//
//public class QuanLiBodeViewOld extends JFrame {
//
//	private JPanel contentPane;
//	
//	QuanLiBodeView current;
//	Permission auth;
//	List<Cauhoi> listCauhoi;
//	Proxy proxy;
//	Gson gson = new Gson();
//	
//	public QuanLiBodeViewOld(Permission auth, List<Cauhoi> listCauhoi, Proxy proxy) {
//		
//		this.auth = auth;
//		this.listCauhoi = listCauhoi;
//		this.proxy = proxy;
//		this.current = this;
//		
//		
//		int row = listCauhoi.size();
//		int col = listCauhoi.get(0).getClass().getDeclaredFields().length;
//		String[][] data = new String[row][col];
//		String[] columnNames = new String[col];
//		
//		for (int i = 0 ; i < listCauhoi.size(); i ++) {
//			Cauhoi quest =  listCauhoi.get(i);
//			Method[] methods = quest.getClass().getMethods();
//			Field[] field = quest.getClass().getDeclaredFields();
//			Controller ctrl = new Controller();
//			int j = 0;
//			for (Field f: field) {
//				String rowData = ctrl.runGetter(f, quest);
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
//		setBounds(100, 100, 1200, 526);
//        
//        JPanel panel = new JPanel();
//        panel.setBounds(10, 10, 651, 116);
//        getContentPane().add(panel);
//        panel.setLayout(null);
//        
//        JLabel lblNewLabel = new JLabel("Quản lí đề thi");
//        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
//        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        lblNewLabel.setBounds(10, 10, 1165, 30);
//        panel.add(lblNewLabel);
//        
//        JSeparator separator = new JSeparator();
//        separator.setBounds(10, 68, 1165, 2);
//        panel.add(separator);
//        
//        JButton btnNewButton_1 = new JButton("Sửa");
////        btnNewButton_1.addActionListener(new Khoaphong());
//        btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 14));
//        btnNewButton_1.setBounds(260, 92, 115, 30);
//        panel.add(btnNewButton_1);
//        
//        JButton btnKhaPhng = new JButton("Xóa");
////        btnKhaPhng.addActionListener(new Xoaphong());
//        btnKhaPhng.setFont(new Font("Arial", Font.PLAIN, 14));
//        btnKhaPhng.setBounds(135, 92, 115, 30);
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
//        btnNewButton_1_2.setBounds(10, 92, 115, 30);
//        panel.add(btnNewButton_1_2);
//        JTable jTable = new JTable(data, columnNames);
//        jTable.setBounds(30, 40, 200, 300);
//        // adding it to JScrollPane
//        JScrollPane sp = new JScrollPane(jTable);
//        panel.add(sp);
//        sp.setBounds(10, 132, 1165, 347);
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
//    		    List<Cauhoi> newListCauhoi = new ArrayList<Cauhoi>();
//    		    
//    		    try {
//    		    	newListCauhoi = ctrl.readBodeFromExcelFile(selectedFile.getAbsolutePath());
//    	        }
//    	        catch (IOException e1) {e1.printStackTrace();}
//    		    
//    		    ThemBodeView themBode = new ThemBodeView(auth, newListCauhoi, proxy, current);
//    		    themBode.setVisible(true);
////    		    
//    		}
//		}
//	}
//}
