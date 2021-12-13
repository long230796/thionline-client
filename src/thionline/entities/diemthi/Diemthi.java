package thionline.entities.diemthi;

public class Diemthi {
	
	String masv;
	String lan;
	String ngaythi;
	String diem;
	
	public Diemthi(String masv, String lan, String ngaythi, String diem) {
		this.masv = masv;
		this.lan = lan;
		this.ngaythi = ngaythi;
		this.diem = diem;
	}
	public String getMasv() {
		return masv;
	}
	public void setMasv(String masv) {
		this.masv = masv;
	}
	public String getLan() {
		return lan;
	}
	public void setLan(String lan) {
		this.lan = lan;
	}
	public String getNgaythi() {
		return ngaythi;
	}
	public void setNgaythi(String ngaythi) {
		this.ngaythi = ngaythi;
	}
	public String getDiem() {
		return diem;
	}
	public void setDiem(String diem) {
		this.diem = diem;
	}

}
