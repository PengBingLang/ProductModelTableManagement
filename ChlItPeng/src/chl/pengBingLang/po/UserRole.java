package chl.pengBingLang.po;

public class UserRole extends UserRoleKey {
	private String doc;

	private String backup01;

	private String backup02;

	private String backup03;

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc == null ? null : doc.trim();
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
}