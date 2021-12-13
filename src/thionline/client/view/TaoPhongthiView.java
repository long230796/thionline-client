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
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class TaoPhongthiView extends JFrame {

	private JPanel contentPane;
	private JTextField tMaphong;
	private JTextField tTenphong;
	private JTextField tSocau;
	private JTextField tTime;
	private JTextField tTrangthai;
	
	TaoPhongthiView current;
	PhongThiView oldAdminView;
	Permission auth;
	List<PhongthiData> pData;
	Proxy proxy;
	Gson gson = new Gson();

	
	public TaoPhongthiView(Permission auth, List<PhongthiData> pData, Proxy proxyService, PhongThiView oldAdView) {
		
		this.auth = auth;
		this.pData = pData;
		this.proxy = proxyService;
		this.current = this;
		this.oldAdminView = oldAdView;
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 439, 68);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tạo phòng thi mới");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 419, 26);
		panel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 56, 419, 2);
		panel.add(separator);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 88, 440, 359);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Mã phòng");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(65, 10, 310, 13);
		panel_1.add(lblNewLabel_1);
		

		tMaphong = new JTextField();
		tMaphong.setFont(new Font("Arial", Font.PLAIN, 15));
		tMaphong.setBounds(65, 25, 310, 32);
//		tMaphong.setText("alo");
//		tMaphong.setEditable(false);
		panel_1.add(tMaphong);
		tMaphong.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên phòng");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(65, 78, 310, 13);
		panel_1.add(lblNewLabel_1_1);
		
		tTenphong = new JTextField();
		tTenphong.setFont(new Font("Arial", Font.PLAIN, 15));
		tTenphong.setColumns(10);
		tTenphong.setBounds(65, 93, 310, 32);
		panel_1.add(tTenphong);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Số câu hỏi");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(65, 146, 310, 13);
		panel_1.add(lblNewLabel_1_1_1);
		
		tSocau = new JTextField();
		tSocau.setFont(new Font("Arial", Font.PLAIN, 15));
		tSocau.setColumns(10);
		tSocau.setBounds(65, 161, 310, 32);
		panel_1.add(tSocau);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Thời gian");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1.setBounds(65, 214, 310, 13);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		tTime = new JTextField();
		tTime.setFont(new Font("Arial", Font.PLAIN, 15));
		tTime.setColumns(10);
		tTime.setBounds(65, 229, 310, 32);
		panel_1.add(tTime);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Trạng thái");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1_1.setBounds(65, 282, 310, 13);
		panel_1.add(lblNewLabel_1_1_1_1_1);
		
		tTrangthai = new JTextField();
		tTrangthai.setFont(new Font("Arial", Font.PLAIN, 15));
		tTrangthai.setColumns(10);
		tTrangthai.setBounds(65, 297, 310, 32);
		panel_1.add(tTrangthai);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 457, 440, 68);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("Tạo");
		btnNewButton.addActionListener(new Taophong());
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 17));
		btnNewButton.setBounds(65, 10, 148, 35);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Hủy");
		btnNewButton_1.addActionListener(new Huytaophong());
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 17));
		btnNewButton_1.setBounds(216, 10, 157, 35);
		panel_2.add(btnNewButton_1);
	}
	
	public boolean kiemtra(String maphong, String tenphong, String trangthai, int socauhoi, int thoigianthi) {
		boolean correct = true;
		
		// kiem tra du lieu
		Controller ctrl = new Controller();
		if (ctrl.checkExistMaPhong(pData, maphong)) {
			JOptionPane.showMessageDialog(null, "Mã phòng đã tồn tại");
			correct = false;
		}
		try {
			socauhoi = Integer.parseInt(tSocau.getText());
			thoigianthi = Integer.parseInt(tTime.getText());
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Số câu hỏi hoặc thời gian phải là số nguyên");
			correct = false;
		}
		if (!new String("mo").equals(trangthai.toLowerCase()) && !new String("dong").equals(trangthai.toLowerCase())) {
			JOptionPane.showMessageDialog(null, "Trạng thái phải mở hoặc đóng");
			correct = false;
		}
		
		return correct;
	}
	
	
	class Taophong implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			// lay du lieu
			String maphong = tMaphong.getText();
			String tenphong = tTenphong.getText();
			String trangthai = tTrangthai.getText();
			int socauhoi = 0;
			int thoigianthi = 0;
			
			if (kiemtra(maphong, tenphong, trangthai, socauhoi, thoigianthi)) {
				// dong goi du lieu
				socauhoi = Integer.parseInt(tSocau.getText());
				thoigianthi = Integer.parseInt(tTime.getText());
				PhongthiData phongMoi = new PhongthiData(maphong, tenphong, trangthai, null, null, socauhoi, thoigianthi);
				List<PhongthiData> listP = new ArrayList<PhongthiData>();
				listP.add(phongMoi);
				PhongthiPackage pPkg = new PhongthiPackage(auth, listP);
				
				// gui du lieu
				String request = "TaoPhongThi_" + gson.toJson(pPkg);
				String response = proxy.sender(request);
				if (response != null) {
					JOptionPane.showMessageDialog(null, "Tao phong thi thanh cong");
					Controller ctrl = new Controller(response);
					List<PhongthiData> pData = ctrl.getListPhongthi();
					
					PhongThiView newAdView = new PhongThiView(auth, pData, proxy);
					current.dispose();
					oldAdminView.dispose();
					newAdView.setVisible(true);
				}
				
			}
			
		}
	 }
	
	class Huytaophong implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			current.dispose();
		}
	}


	
}
