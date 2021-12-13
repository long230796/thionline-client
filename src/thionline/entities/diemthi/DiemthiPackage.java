package thionline.entities.diemthi;

import java.util.List;

import thionline.entities.Permission;

public class DiemthiPackage {
	Permission permission;
	List<Diemthi> listDiemthi;
	public DiemthiPackage(Permission permission, List<Diemthi> listDiemthi) {
		super();
		this.permission = permission;
		this.listDiemthi = listDiemthi;
	}
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public List<Diemthi> getListDiemthi() {
		return listDiemthi;
	}
	public void setListDiemthi(List<Diemthi> listDiemthi) {
		this.listDiemthi = listDiemthi;
	}
	
	
}
