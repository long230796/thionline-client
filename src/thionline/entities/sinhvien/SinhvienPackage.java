package thionline.entities.sinhvien;

import java.util.List;

import thionline.entities.Permission;

public class SinhvienPackage {
	Permission permission;
	List<Sinhvien> listSinhvien;
	
	public SinhvienPackage(Permission permission, List<Sinhvien> listSinhvien) {
		super();
		this.permission = permission;
		this.listSinhvien = listSinhvien;
	}
	
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public List<Sinhvien> getListSinhvien() {
		return listSinhvien;
	}
	public void setListSinhvien(List<Sinhvien> listSinhvien) {
		this.listSinhvien = listSinhvien;
	}
	
	
}
