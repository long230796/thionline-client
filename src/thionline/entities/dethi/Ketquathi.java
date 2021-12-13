package thionline.entities.dethi;

import java.util.List;

import thionline.entities.Permission;

public class Ketquathi {
	Permission permission;
	float diemtong;
	public Ketquathi(Permission permission, float diemtong) {
		super();
		this.permission = permission;
		this.diemtong = diemtong;
	}
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public float getDiemtong() {
		return diemtong;
	}
	public void setDiemtong(float diemtong) {
		this.diemtong = diemtong;
	}
	
	
	
}
