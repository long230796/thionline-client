package thionline.entities.sinhvien;

public class Sinhvien {
	String mssv;
	String ho;
	String ten;
	String ngaysinh;
	String diachi;
	String username;
	String password;
	int mavaitro;
	int matrangthai;
	String thi;
	
	public Sinhvien() {}

	public Sinhvien(String mssv, String ho, String ten, String ngaysinh, String diachi, String username,
			String password, int mavaitro, int matrangthai, String thi) {
		super();
		this.mssv = mssv;
		this.ho = ho;
		this.ten = ten;
		this.ngaysinh = ngaysinh;
		this.diachi = diachi;
		this.username = username;
		this.password = password;
		this.mavaitro = mavaitro;
		this.matrangthai = matrangthai;
		this.thi = thi;
	}



	public String getMssv() {
		return mssv;
	}

	public void setMssv(String mssv) {
		this.mssv = mssv;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMavaitro() {
		return mavaitro;
	}

	public void setMavaitro(int mavaitro) {
		this.mavaitro = mavaitro;
	}

	public int getMatrangthai() {
		return matrangthai;
	}

	public void setMatrangthai(int matrangthai) {
		this.matrangthai = matrangthai;
	}

	public String getThi() {
		return thi;
	}

	public void setThi(String thi) {
		this.thi = thi;
	}
	
	
	
	
	
	
}
