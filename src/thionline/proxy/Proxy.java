package thionline.proxy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.gson.Gson;


public class Proxy {

	Socket sock;
	Gson gson = new Gson();
	DataInputStream din = null;
	DataOutputStream dout = null;
	ReceiverThread receiver;
	
	public Proxy(Socket sock) {
		this.sock = sock;
		setupSocket();
		
	}
	
	public void setupSocket() {
		try {
			this.din = new DataInputStream(sock.getInputStream());
			this.dout = new DataOutputStream(sock.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		receiver = new ReceiverThread();
//		receiver.start();
	}
	
	public String sender(String data) {
		try {
			dout.writeUTF(data);
			String res = din.readUTF();
			System.out.println("client obtained request...");
			System.out.println("client: " + res);
			return res;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	class ReceiverThread extends Thread {
		public void run() {
			while (!interrupted()) {
				try {
					String res = din.readUTF();
					System.out.println("receiver obtain request");
					if (res.contains("toReceiverThread")) {
						// goi controller
					}
				} catch (Exception e) {}
			}
		}
	}
	
	public void close() {
		if (sock != null) {
			try {
				sock.close();
			} catch(Exception e) {}
		}
	}
}
