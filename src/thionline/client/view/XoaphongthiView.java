package thionline.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import thionline.controller.Controller;
import thionline.entities.Permission;
import thionline.entities.phongthi.PhongthiData;
import thionline.entities.phongthi.PhongthiPackage;
import thionline.proxy.Proxy;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class XoaphongthiView extends JFrame {

	private JPanel contentPane;
	private JTextField tMaphong;

	XoaphongthiView current;
	PhongThiView oldAdminView;
	Permission auth;
	List<PhongthiData> ListP;
	Proxy proxy;
	Gson gson = new Gson();
	
	public XoaphongthiView(Permission auth, List<PhongthiData> ListP, Proxy proxyService, PhongThiView oldAdView) {
		
		this.auth = auth;
		this.ListP = ListP;
		this.proxy = proxyService;
		this.current = this;
		this.oldAdminView = oldAdView;
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 427, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 393, 64);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nhập mã phòng thi");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 373, 30);
		panel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 52, 373, 2);
		panel.add(separator);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 84, 393, 64);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Mã phòng");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(55, 10, 283, 13);
		panel_1.add(lblNewLabel_1);
		
		tMaphong = new JTextField();
		tMaphong.setBounds(55, 25, 283, 31);
		panel_1.add(tMaphong);
		tMaphong.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 158, 393, 53);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("Xóa");
		btnNewButton.addActionListener(new Xoaphong());
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 15));
		btnNewButton.setBounds(55, 10, 134, 27);
		panel_2.add(btnNewButton);
		
		JButton btnHy = new JButton("Hủy");
		btnHy.addActionListener(new Huy());
		btnHy.setFont(new Font("Arial", Font.PLAIN, 15));
		btnHy.setBounds(206, 10, 134, 27);
		panel_2.add(btnHy);
	}
	
	class Xoaphong implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			String maphong = tMaphong.getText();
			Controller ctrl = new Controller();
			if (ctrl.checkExistMaPhong(ListP, maphong)) {
				// dong goi du lieu
				PhongthiData pData = new PhongthiData(maphong, null, null, null, null, 0, 0);
				List<PhongthiData> newListP = new ArrayList<PhongthiData>();
				newListP.add(pData);
				PhongthiPackage pPkg = new PhongthiPackage(auth, newListP);
				
				// gui du lieu
				String request = "XoaPhongThi_" + gson.toJson(pPkg);
				String response = proxy.sender(request);
				
				if (new String("null").equals(response)) {
					JOptionPane.showMessageDialog(null, "Xóa phòng thi thành công!");
					Controller ctrl2 = new Controller(response);
					List<PhongthiData> listP = ctrl2.getListPhongthi();
					
					PhongThiView newAdView = new PhongThiView(auth, listP, proxy);
					current.dispose();
					oldAdminView.dispose();
					newAdView.setVisible(true);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Mã phòng không tồn tại!");
			}
		}
	}
	
	class Huy implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			current.dispose();
		}
	}
}
