package thionline.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import thionline.controller.Controller;
import thionline.entities.Permission;
import thionline.entities.dethi.Cauhoi;
import thionline.entities.dethi.CauhoiPackage;
import thionline.entities.diemthi.Diemthi;
import thionline.entities.diemthi.DiemthiPackage;
import thionline.entities.phongthi.PhongthiData;
import thionline.entities.sinhvien.Sinhvien;
import thionline.entities.sinhvien.SinhvienPackage;
import thionline.proxy.Proxy;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class MainView extends JFrame {

	private JPanel contentPane;
	MainView current;
	Permission auth;
	List<PhongthiData> pData;
	Proxy proxy;
	Gson gson = new Gson(); 

	public MainView(Permission auth, List<PhongthiData> pData, Proxy proxyService) {
		
		this.auth = auth;
		this.pData = pData;
		this.proxy = proxyService;
		this.current = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1298, 533);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.background"));
		panel.setBounds(10, 10, 1268, 86);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Phần mềm quản lí thi online");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 1252, 51);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 106, 309, 380);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 10, 289, 307);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(10, 10, 269, 287);
		panel_2.add(lblNewLabel_2);
		ImageIcon MyImage1 = new ImageIcon("C:\\Users\\long2\\OneDrive\\Desktop\\eclipse2021-workspace\\thionline-client\\src\\sources\\phongthi.png");
		Image img1 = MyImage1.getImage();
		Image newImg1 = img1.getScaledInstance(lblNewLabel_2.getWidth(), lblNewLabel_2.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image1 = new ImageIcon(newImg1);
		lblNewLabel_2.setIcon(image1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.menu);
		panel_3.setBounds(10, 327, 289, 43);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnNewButton = new JButton("Quản lí phòng thi");
		btnNewButton.addActionListener(new Qlpt());
		btnNewButton.setBounds(75, 10, 140, 21);
		panel_3.add(btnNewButton);
		
		
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(329, 106, 309, 380);
		contentPane.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.WHITE);
		panel_2_1.setBounds(10, 10, 289, 307);
		panel_1_1.add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("New label");
		lblNewLabel_2_1.setBounds(10, 10, 269, 287);
		panel_2_1.add(lblNewLabel_2_1);
		ImageIcon MyImage2 = new ImageIcon("C:\\Users\\long2\\OneDrive\\Desktop\\eclipse2021-workspace\\thionline-client\\src\\sources\\testing.jpg");
		Image img2 = MyImage2.getImage();
		Image newImg2 = img2.getScaledInstance(lblNewLabel_2_1.getWidth(), lblNewLabel_2_1.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image2 = new ImageIcon(newImg2);
		lblNewLabel_2_1.setIcon(image2);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBackground(SystemColor.menu);
		panel_3_1.setBounds(10, 327, 289, 43);
		panel_1_1.add(panel_3_1);
		panel_3_1.setLayout(null);
		
		JButton btnQunL = new JButton("Quản lí đề thi");
		btnQunL.addActionListener(new Dethi());
		btnQunL.setBounds(77, 10, 140, 21);
		panel_3_1.add(btnQunL);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBounds(648, 106, 309, 380);
		contentPane.add(panel_1_2);
		panel_1_2.setLayout(null);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setBackground(Color.WHITE);
		panel_2_2.setBounds(10, 10, 289, 307);
		panel_1_2.add(panel_2_2);
		panel_2_2.setLayout(null);
		
		JLabel lblNewLabel_2_2 = new JLabel("New label");
		lblNewLabel_2_2.setBounds(10, 10, 269, 287);
		panel_2_2.add(lblNewLabel_2_2);
		ImageIcon MyImage3 = new ImageIcon("C:\\Users\\long2\\OneDrive\\Desktop\\eclipse2021-workspace\\thionline-client\\src\\sources\\sinhvien.png");
		Image img3 = MyImage3.getImage();
		Image newImg3 = img3.getScaledInstance(lblNewLabel_2_2.getWidth(), lblNewLabel_2_2.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image3 = new ImageIcon(newImg3);
		lblNewLabel_2_2.setIcon(image3);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setBackground(SystemColor.menu);
		panel_3_2.setBounds(10, 327, 289, 43);
		panel_1_2.add(panel_3_2);
		panel_3_2.setLayout(null);
		
		JButton btnQunLSinh = new JButton("Quản lí sinh viên");
		btnQunLSinh.addActionListener(new SinhVien());
		btnQunLSinh.setBounds(76, 10, 140, 21);
		panel_3_2.add(btnQunLSinh);
		
		JPanel panel_1_2_1 = new JPanel();
		panel_1_2_1.setLayout(null);
		panel_1_2_1.setBounds(969, 106, 309, 380);
		contentPane.add(panel_1_2_1);
		
		JPanel panel_2_2_1 = new JPanel();
		panel_2_2_1.setLayout(null);
		panel_2_2_1.setBackground(Color.WHITE);
		panel_2_2_1.setBounds(10, 10, 289, 307);
		panel_1_2_1.add(panel_2_2_1);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("New label");
		lblNewLabel_2_2_1.setBounds(10, 10, 269, 287);
		panel_2_2_1.add(lblNewLabel_2_2_1);
		ImageIcon MyImage4 = new ImageIcon("C:\\Users\\long2\\OneDrive\\Desktop\\eclipse2021-workspace\\thionline-client\\src\\sources\\bangdiem.jpg");
		Image img4 = MyImage4.getImage();
		Image newImg4 = img4.getScaledInstance(lblNewLabel_2_2_1.getWidth(), lblNewLabel_2_2_1.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image4 = new ImageIcon(newImg4);
		lblNewLabel_2_2_1.setIcon(image4);
		
		JPanel panel_3_2_1 = new JPanel();
		panel_3_2_1.setLayout(null);
		panel_3_2_1.setBackground(SystemColor.menu);
		panel_3_2_1.setBounds(10, 327, 289, 43);
		panel_1_2_1.add(panel_3_2_1);
		
		JButton btnQunLSinh_1 = new JButton("Quản lí bảng điểm");
		btnQunLSinh_1.addActionListener(new Bangdiem());
		btnQunLSinh_1.setBounds(76, 10, 140, 21);
		panel_3_2_1.add(btnQunLSinh_1);
	}
	
	class Qlpt implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			PhongThiView adView = new PhongThiView(auth, pData, proxy);
			adView.setVisible(true);
		}
	 }
	
	class Bangdiem implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			DiemthiPackage diemthiPkg = new DiemthiPackage(auth, null);
			String request = "LayDiemThi_" + gson.toJson(diemthiPkg);
			String response = proxy.sender(request);
			
			if (!new String("null").equals(response)) {
				Controller ctrl = new Controller(response);
				List<Diemthi> listDiemthi = ctrl.getListDiemthi();
				DiemthiView diemthiView = new DiemthiView(auth, listDiemthi, proxy);
				diemthiView.setVisible(true);
			}
		}
	}
	
	class SinhVien implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			SinhvienPackage svPkg = new SinhvienPackage(auth, null);
			String request = "LaySinhVien_" + gson.toJson(svPkg);
			String response = proxy.sender(request);
			
			if (!new String("null").equals(response)) {
				Controller ctrl = new Controller(response);
				List<Sinhvien> listSv = ctrl.getListSinhvien();
				QuanLiSinhVienView qlsv = new QuanLiSinhVienView(auth, listSv, proxy);
				qlsv.setVisible(true);
			}
		}
	}
	
	class Dethi implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			CauhoiPackage questPkg = new CauhoiPackage(auth, null, 0);
			String request = "LayTatCaDeThi_" + gson.toJson(questPkg);
			String response = proxy.sender(request);
			
			if (!new String("null").equals(response)) {
				Controller ctrl = new Controller(response);
				List<Cauhoi> listCauhoi = ctrl.getListCauhoi();
				QuanLiBodeView qldethi = new QuanLiBodeView(auth, listCauhoi, proxy);
				qldethi.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại!");
			}
		}
	}
}
