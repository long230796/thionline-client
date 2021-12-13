package thionline.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import javax.swing.JButton;
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

import thionline.client.view.ThemSinhvienView.Huy;
import thionline.client.view.ThemSinhvienView.Thuchien;
import thionline.controller.Controller;
import thionline.entities.Permission;
import thionline.entities.dethi.Cauhoi;
import thionline.entities.dethi.CauhoiPackage;
import thionline.proxy.Proxy;

public class ThemBodeView extends JFrame {

	private JPanel contentPane;
	
	ThemBodeView current;
	QuanLiBodeView oldQlbd;
	Permission auth;
	List<Cauhoi> listCauhoi;
	Proxy proxy;
	Gson gson = new Gson();
	
	public ThemBodeView(Permission auth, List<Cauhoi> listCauhoi, Proxy proxy, QuanLiBodeView oldQlbd) {
		
		this.auth = auth;
		this.listCauhoi = listCauhoi;
		this.proxy = proxy;
		this.oldQlbd = oldQlbd;
		this.current = this;
		
		int row = listCauhoi.size();
		int col = listCauhoi.get(0).getClass().getDeclaredFields().length;
		String[][] data = new String[row][col];
		String[] columnNames = new String[col];
		
		for (int i = 0 ; i < listCauhoi.size(); i ++) {
			Cauhoi sv =  listCauhoi.get(i);
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
        
        JLabel lblNewLabel = new JLabel("Thêm bộ đề");
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
			int dialogResult = JOptionPane.showConfirmDialog (null, "Bạn có muốn thêm bộ đề mới không?", "Warning", 0);
			if(dialogResult == JOptionPane.YES_OPTION){
				
				CauhoiPackage cauhoiPkg = new CauhoiPackage(auth, listCauhoi, 0);
				String request = "LuuBode_" + gson.toJson(cauhoiPkg);
				String response = proxy.sender(request);
				
				if (!new String("null").equals(response)) {
					JOptionPane.showMessageDialog(null, "Thêm bộ đề thành công");
					Controller ctrl = new Controller(response);
					List<Cauhoi> listCauhoi = ctrl.getListCauhoi();
					
					QuanLiBodeView qlbd = new QuanLiBodeView(auth, listCauhoi, proxy);
					current.dispose();
					oldQlbd.dispose();
					qlbd.setVisible(true);
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
