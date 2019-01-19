package chl.pengBingLang.po;

public class SysUser {
	private String id;

	private String name;

	private String password;

	private String email;

	private String tel;

	private String name2;

	private String enable;

	private String backup01;

	private String backup02;

	private String backup03;

	private String backup04;

	private String backup05;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel == null ? null : tel.trim();
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2 == null ? null : name2.trim();
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable == null ? null : enable.trim();
	}

	public String getBackup01() {
		return backup01;
	}

	public void setBackup01(String backup01) {
		this.backup01 = backup01 == null ? null : backup01.trim();
	}

	public String getBackup02() {
		return backup02;
	}

	public void setBackup02(String backup02) {
		this.backup02 = backup02 == null ? null : backup02.trim();
	}

	public String getBackup03() {
		return backup03;
	}

	public void setBackup03(String backup03) {
		this.backup03 = backup03 == null ? null : backup03.trim();
	}

	public String getBackup04() {
		return backup04;
	}

	public void setBackup04(String backup04) {
		this.backup04 = backup04 == null ? null : backup04.trim();
	}

	public String getBackup05() {
		return backup05;
	}

	public void setBackup05(String backup05) {
		this.backup05 = backup05 == null ? null : backup05.trim();
	}
}