package thionline.entities.phongthi;

import java.util.List;

import thionline.entities.Permission;

public class PhongthiPackage {
	Permission permission;
	List<PhongthiData> listPhongthi;
	
	public PhongthiPackage(Permission permission, List<PhongthiData> listPhongthi) {
		super();
		this.permission = permission;
		this.listPhongthi = listPhongthi;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public List<PhongthiData> getListPhongthi() {
		return listPhongthi;
	}

	public void setListPhongthi(List<PhongthiData> listPhongthi) {
		this.listPhongthi = listPhongthi;
	}
	
	
	
	
}
