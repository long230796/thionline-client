package thionline.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Image;

import com.jgoodies.forms.layout.FormLayout;
import com.google.gson.Gson;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.Insets;
import java.nio.file.Path;
import java.util.List;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import thionline.controller.Controller;
import thionline.entities.Permission;
import thionline.entities.login.Login;
import thionline.entities.login.LoginData;
import thionline.entities.phongthi.PhongthiData;
import thionline.proxy.Proxy;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField TextTk;
	private JTextField textMk;
	LoginView current;
	Proxy proxy = null;
	Gson gson = new Gson();
	
	public LoginView(Proxy proxyService) {
		
		this.proxy = proxyService;
		this.current = this;
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 400, 398);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 10, 380, 84);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Đăng nhập vào hệ thống");
		lblNewLabel.setBackground(SystemColor.menu);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 104, 380, 200);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tài khoản");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(50, 31, 280, 20);
		panel_3.add(lblNewLabel_1);
		
		TextTk = new JTextField();
		TextTk.setBounds(50, 52, 280, 35);
		panel_3.add(TextTk);
		TextTk.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(50, 97, 280, 20);
		panel_3.add(lblNewLabel_1_1);
		
		textMk = new JPasswordField();
		textMk.setColumns(10);
		textMk.setBounds(50, 116, 280, 35);
		panel_3.add(textMk);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 314, 380, 74);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JButton btnNewButton = new JButton("Đăng nhập");
		btnNewButton.addActionListener(new LoginHandler());
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNewButton.setBounds(50, 10, 280, 35);
		panel_4.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(420, 10, 380, 398);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(25, 62, 330, 255);
		panel_1.add(lblNewLabel_2);
		ImageIcon MyImage =	new ImageIcon("C:\\Users\\long2\\OneDrive\\Desktop\\eclipse2021-workspace\\thionline-client\\src\\sources\\login.png");
		Image img = MyImage.getImage();
		Image newImg = img.getScaledInstance(lblNewLabel_2.getWidth(), lblNewLabel_2.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		lblNewLabel_2.setIcon(image);
		
		// setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				proxy.close();
				System.exit(0);
			}
		});
		
	}
	
	class LoginHandler implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			String username = TextTk.getText();
			String password = textMk.getText();
			try{
				LoginData data = new LoginData(username, password);
				Permission per = new Permission("User", "null");
				Login lg = new Login(per, data);
				
				String request = "Login_" + gson.toJson(lg);
				String response = proxy.sender(request);
				
				// set permission
				Controller ctrl = new Controller(response);
				Permission auth = ctrl.setPermisstion();
				
				
				// check permission
				if (new String("ADMIN").equals(auth.getPermisstion())) {
					List<PhongthiData> pData = ctrl.getListPhongthi();
					MainView mView = new MainView(auth, pData, proxy);
					current.dispose();
					mView.setVisible(true);
				} else {
					VaophongthiView vaoPthi = new VaophongthiView(auth, proxy);
					vaoPthi.setVisible(true);
				}
				
				
				
				
			
			}catch (Exception exp){
//				JOptionPane.showMessageDialog(null, "Tài khoản mật khẩu không chính xác!");
				System.out.println( exp ); 
			}
		}
	 }
	
}
