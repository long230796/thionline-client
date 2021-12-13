package thionline.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import thionline.client.supportUI.MultiLineLabel;
import thionline.client.supportUI.MultiLineLabelUI;
import thionline.controller.Controller;
import thionline.entities.Permission;
import thionline.entities.dethi.Cauhoi;
import thionline.entities.dethi.Ketquathi;
import thionline.entities.dethi.Traloi;
import thionline.proxy.Proxy;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class DethiView extends JFrame {

	private JPanel contentPane;
	private boolean timeOver = false;
	private boolean cauA = false, cauB = false, cauC = false, cauD = false;
	final java.text.SimpleDateFormat sdf;
	final JLabel timeLable;
	final long TIMES;
	final 
	int thutu;
	int thoigianthi;
	Permission auth;
	Cauhoi cauhoi;
	DethiView prevView;
	DethiView nextView;
	DethiView current;
	Proxy proxy;
	List<Traloi> listTraloi;
	List<Cauhoi> listCauhoi;
	Map<String, DethiView> listFrame;
	
	Gson gson = new Gson();
	

	
	public DethiView(Proxy proxy, int thutu, List<Traloi> listTraloi, Permission auth, List<Cauhoi> listCauhoi, int thoigianthi) {
		
		this.cauhoi = listCauhoi.get(thutu);
		this.proxy = proxy;
		this.current = this;
		this.thutu = thutu;
		this.listTraloi = listTraloi;
		this.auth = auth;
		this.listCauhoi = listCauhoi;
		this.thoigianthi = thoigianthi;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		MultiLineLabel  Question = new MultiLineLabel("Câu " + (thutu+1) + ": " + cauhoi.getNoiDung());
		Question.setVerticalAlignment(SwingConstants.TOP);
		Question.setFont(new Font("Arial", Font.PLAIN, 15));
		Question.setUI(MultiLineLabelUI.labelUI);
		
		JRadioButton rdbtnA = new JRadioButton(cauhoi.getCauA());
		rdbtnA.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					cauA = true;
				} else {
					cauA = false;
				}
			}
		});
		
		JRadioButton rdbtnB = new JRadioButton(cauhoi.getCauB());
		rdbtnB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					cauB = true;
				} else {
					cauB = false;
				}
			}
		});
		
		JRadioButton rdbtnC = new JRadioButton(cauhoi.getCauC());
		rdbtnC.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					cauC = true;
				} else {
					cauC = false;
				}
			}
		});
		
		JRadioButton rdbtnD = new JRadioButton(cauhoi.getCauD());
		rdbtnD.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					cauD = true;
				} else {
					cauD = false;
				}
			}
		});
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new NextAction());
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new BackAction());
		
		
		TIMES = thoigianthi * 60000;
	    sdf = new java.text.SimpleDateFormat("mm : ss");
	    timeLable = new JLabel(sdf.format(new Date(TIMES)),JLabel.CENTER);
	    int x = 0;
	    countDown();
	   
		
		timeLable.setHorizontalAlignment(SwingConstants.CENTER);
		timeLable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnNopBai = new JButton("Nộp bài");
		btnNopBai.addActionListener(new NopBai());
		btnNopBai.setFont(new Font("Tahoma", Font.PLAIN, 10));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(59)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(rdbtnA, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
						.addComponent(rdbtnB, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(rdbtnC, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(rdbtnD, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(42, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(157, Short.MAX_VALUE)
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addGap(80)
					.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(161))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(217)
					.addComponent(timeLable, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
					.addComponent(btnNopBai)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addComponent(Question, GroupLayout.PREFERRED_SIZE, 481, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(timeLable, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNopBai))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Question, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnA)
					.addGap(18)
					.addComponent(rdbtnB)
					.addGap(18)
					.addComponent(rdbtnC)
					.addGap(18)
					.addComponent(rdbtnD)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNext)
						.addComponent(btnBack))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		
//		pack();
		contentPane.setLayout(gl_contentPane);
		
		ButtonGroup btnGrp = new ButtonGroup();
		btnGrp.add(rdbtnA);
		btnGrp.add(rdbtnB);
		btnGrp.add(rdbtnC);
		btnGrp.add(rdbtnD);
		
		
	}
	
	private void countDown() {
		ActionListener al = new ActionListener(){
			long x = TIMES - 1000;
			public void actionPerformed(ActionEvent ae){
				timeLable.setText(sdf.format(new Date(x)));
				x -= 1000;
				if (x < 0) {
					((Timer)ae.getSource()).stop();
					timeOver = true;
				}
			}
		};
		new javax.swing.Timer(1000, al).start();
	}
	
	public void setListFrame(Map<String, DethiView> listFrame) {
		this.listFrame = listFrame;
	}
	
	public void setPrevFrame(DethiView prev) {
		this.prevView = prev;
	}
	
	public void setNextFrame(DethiView next) {
		this.nextView = next;
	}
	
	class NextAction implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			if (!timeOver) {
				Traloi ans = new Traloi(cauhoi.getMaCau(), "null");
				if (cauA) {
					ans = new Traloi(cauhoi.getMaCau(), "A");
				}
				if (cauB) {
					ans = new Traloi(cauhoi.getMaCau(), "B");
				}
				if (cauC) {
					ans = new Traloi(cauhoi.getMaCau(), "C");
				}
				if (cauD) {
					ans = new Traloi(cauhoi.getMaCau(), "D");
				}
				
				// add traloi to list
				listTraloi.set(thutu, ans);
				System.out.println(gson.toJson(listTraloi));
				
				// goi frame tiep theo
				DethiView nextFrame = listFrame.get("frame" + (thutu+2));
				nextView.setListFrame(listFrame);
				nextView.setPrevFrame(current);
				nextView.setNextFrame(nextFrame);
				
				try {
					current.setVisible(false);
					nextView.setVisible(true);					
				} catch (Exception e2) {}
				
				
				
			} else {
				JOptionPane.showMessageDialog(null, "Đã hết thời gian");
			}
		} 
	}
	
	class BackAction implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			if (!timeOver) {
				if (prevView != null) {
					current.setVisible(false);
					prevView.setVisible(true);
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Đã hết thời gian");
			}
		}
	}
	
	class NopBai implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			int dialogResult = JOptionPane.showConfirmDialog (null, "Bạn nó chắc muốn nộp bài không?", "Warning", 0);
			if(dialogResult == JOptionPane.YES_OPTION){
				// gọi controller truyền vào danh sách trả lời để tính điểm sau đó show điểm và đóng gói cùng với auth gửi về server lưu
				Controller ctrl = new Controller();
				float diemTong = ctrl.tinhDiem(listTraloi, listCauhoi);
				Ketquathi ketqua = new Ketquathi(auth, diemTong);
				
				String request = "LuuDiemThi_" + gson.toJson(ketqua);
				String response = proxy.sender(request);
				if (!new String("null").equals(response)) {
					JOptionPane.showMessageDialog(null, "Điểm của bạn là: " + diemTong);
					JOptionPane.showMessageDialog(null, "Bạn đã hoàn thành bài thi, vui lòng thoát khỏi hệ thống! ");
//					proxy.sender("Quit");
//					proxy.close();
					current.dispose();
				}
				
				// sau khi Nộp xong thì ngắt kết nối trên client và server
			}
			
		}
	}
}
