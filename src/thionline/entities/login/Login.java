package thionline.entities.login;

import thionline.entities.Permission;
import thionline.entities.login.LoginData;

public class Login {
	Permission permission;
	LoginData loginData;
	public Login(Permission permission, LoginData loginData) {
		super();
		this.permission = permission;
		this.loginData = loginData;
	}
	public Permission getpermission() {
		return permission;
	}
	public void setpermission(Permission permission) {
		this.permission = permission;
	}
	public LoginData getLoginData() {
		return loginData;
	}
	public void setLoginData(LoginData loginData) {
		this.loginData = loginData;
	}
	
	
	
	
}