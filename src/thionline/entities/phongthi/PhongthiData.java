package thionline.entities.phongthi;

public class PhongthiData {
	String maphong;
	String tenphong;
	String trangthai;
	String ngaytao;
	String hinhthuc;
	int socauhoi;
	int thoigian;
	public PhongthiData(String maphong, String tenphong, String trangthai, String ngaytao, String hinhthuc,
			int socauhoi, int thoigian) {
		super();
		this.maphong = maphong;
		this.tenphong = tenphong;
		this.trangthai = trangthai;
		this.ngaytao = ngaytao;
		this.hinhthuc = hinhthuc;
		this.socauhoi = socauhoi;
		this.thoigian = thoigian;
	}
	public String getMaphong() {
		return maphong;
	}
	public void setMaphong(String maphong) {
		this.maphong = maphong;
	}
	public String getTenphong() {
		return tenphong;
	}
	public void setTenphong(String tenphong) {
		this.tenphong = tenphong;
	}
	public String getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}
	public String getNgaytao() {
		return ngaytao;
	}
	public void setNgaytao(String ngaytao) {
		this.ngaytao = ngaytao;
	}
	public String getHinhthuc() {
		return hinhthuc;
	}
	public void setHinhthuc(String hinhthuc) {
		this.hinhthuc = hinhthuc;
	}
	public int getSocauhoi() {
		return socauhoi;
	}
	public void setSocauhoi(int socauhoi) {
		this.socauhoi = socauhoi;
	}
	public int getThoigian() {
		return thoigian;
	}
	public void setThoigian(int thoigian) {
		this.thoigian = thoigian;
	}
	
	
	
}
