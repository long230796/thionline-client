package thionline.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import thionline.client.view.LoginView;
import thionline.proxy.Proxy;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class App extends JFrame {

	private JPanel contentPane;
	private JTextField textIp;
	App current;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		
		this.current = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 270);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nhập địa chỉ server ");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 426, 44);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ip address");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(90, 76, 89, 19);
		contentPane.add(lblNewLabel_1);
		
		textIp = new JTextField();
		textIp.setBounds(90, 98, 246, 31);
		contentPane.add(textIp);
		textIp.setColumns(10);
		
		JButton btnNewButton = new JButton("Nhập");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ip = textIp.getText();
				try {
					Socket sock = new Socket(ip, 9090);
					Proxy proxy = new Proxy(sock);
					
					LoginView lg = new LoginView(proxy);
					lg.setVisible(true);
					current.setVisible(false);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Không thể kết nối đến server, connect refuse!");
				}
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 19));
		btnNewButton.setBounds(173, 170, 89, 31);
		contentPane.add(btnNewButton);
	}

}
