package it.unical.pizzamanager.model;

public class PasswordSetting {

	private String oldPassword;
	private String newPassword;

	
	public PasswordSetting() {
		this.oldPassword="";
		this.newPassword="";
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}


}
