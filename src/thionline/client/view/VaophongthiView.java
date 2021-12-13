package thionline.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import thionline.client.view.MoPhongthiView.Huy;
import thionline.client.view.MoPhongthiView.Xoaphong;
import thionline.controller.Controller;
import thionline.entities.Permission;
import thionline.entities.dethi.Cauhoi;
import thionline.entities.dethi.Traloi;
import thionline.entities.phongthi.PhongthiData;
import thionline.entities.phongthi.PhongthiPackage;
import thionline.proxy.Proxy;

public class VaophongthiView extends JFrame {

	private JPanel contentPane;
	private JTextField tMaphong;

	VaophongthiView current;
	Permission auth;
	Proxy proxy;
	Gson gson = new Gson();
	
	public VaophongthiView(Permission auth, Proxy proxyService) {
		
		this.auth = auth;
		this.proxy = proxyService;
		this.current = this;
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 427, 253);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 393, 64);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nhập mã để vào phòng thi");
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
		panel_2.setBounds(10, 158, 393, 58);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("Nhập");
		btnNewButton.addActionListener(new nhap());
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 15));
		btnNewButton.setBounds(55, 10, 138, 27);
		panel_2.add(btnNewButton);
		
		JButton btnHy = new JButton("Hủy");
		btnHy.addActionListener(new Huy());
		btnHy.setFont(new Font("Arial", Font.PLAIN, 15));
		btnHy.setBounds(203, 10, 138, 27);
		panel_2.add(btnHy);
	}
	
	
	class nhap implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			String maphong = tMaphong.getText();
			
			// dong goi du lieu
			PhongthiData pData = new PhongthiData(maphong, null, null, null, null, 0, 0);
			List<PhongthiData> newListP = new ArrayList<PhongthiData>();
			newListP.add(pData);
			PhongthiPackage pPkg = new PhongthiPackage(auth, newListP);
			
			// gui du lieu
			String request = "LayDeThi_" + gson.toJson(pPkg);
			String response = proxy.sender(request);
			
			if (!new String("null").equals(response)) {
				Controller ctrl = new Controller(response);
				List<Cauhoi> listCauhoi = ctrl.getListCauhoi();
				List<Traloi> ans = new ArrayList<Traloi>();
				Map<String, DethiView> map = new HashMap<String, DethiView>();
				int thoigianthi = ctrl.getThoigianthi();
				
				// set value for list
				for (int i = 0; i <listCauhoi.size(); i ++) {
					ans.add(null);
				}
				
				for (int i = 0; i < listCauhoi.size(); i ++) {
					
					map.put("frame" + i, new DethiView(proxy, i, ans, auth, listCauhoi, thoigianthi));
				}
				
				DethiView dethi = map.get("frame0");
				dethi.setListFrame(map);
				dethi.setPrevFrame(null);
				dethi.setNextFrame(map.get("frame1"));
				
				dethi.setVisible(true);
//				AdminView newAdView = new AdminView(auth, listP, proxy);
//				oldAdminView.dispose();
//				newAdView.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Không thể vào phòng thi");
			}
			
			
		}
	}
	
	class Huy implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			current.dispose();
		}
	}
}
