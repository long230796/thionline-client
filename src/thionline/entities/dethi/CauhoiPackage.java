package thionline.entities.dethi;

import java.util.List;

import thionline.entities.Permission;

public class CauhoiPackage {
	Permission permission;
	List<Cauhoi> listCauhoi;
	int thoigianthi;
	
	public CauhoiPackage(Permission permission, List<Cauhoi> listCauhoi, int thoigianthi) {
		super();
		this.permission = permission;
		this.listCauhoi = listCauhoi;
		this.thoigianthi = thoigianthi;
	}
	
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public List<Cauhoi> getListCauhoi() {
		return listCauhoi;
	}
	public void setListCauhoi(List<Cauhoi> listCauhoi) {
		this.listCauhoi = listCauhoi;
	}
	public int getThoigianthi() {
		return thoigianthi;
	}
	public void setThoigianthi(int thoigianthi) {
		this.thoigianthi = thoigianthi;
	}
	
	
	
	
}
