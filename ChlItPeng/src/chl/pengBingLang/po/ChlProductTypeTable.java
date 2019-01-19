package chl.pengBingLang.po;

public class ChlProductTypeTable extends ChlProductTypeTableKey {
	/**
	 * 可用性
	 */
	private String usability;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 首用车型
	 */
	private String firstCarType;
	/**
	 * 客户图号
	 */
	private String customerPrintNo;
	/**
	 * 发号日期
	 */
	private String grantNumDate;
	/**
	 * 设计人
	 */
	private String designPeople;
	/**
	 * 开发单位
	 */
	private String developCompany;
	/**
	 * 说明(派生特征)
	 */
	private String explainAll;
	/**
	 * 备用字段01
	 */
	private String backupColumn01;
	/**
	 * 备用字段02
	 */
	private String backupColumn02;
	/**
	 * 备用字段03
	 */
	private String backupColumn03;

	public String getUsability() {
		return usability;
	}

	public void setUsability(String usability) {
		this.usability = usability == null ? null : usability.trim();
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName == null ? null : productName.trim();
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName == null ? null : customerName.trim();
	}

	public String getFirstCarType() {
		return firstCarType;
	}

	public void setFirstCarType(String firstCarType) {
		this.firstCarType = firstCarType == null ? null : firstCarType.trim();
	}

	public String getCustomerPrintNo() {
		return customerPrintNo;
	}

	public void setCustomerPrintNo(String customerPrintNo) {
		this.customerPrintNo = customerPrintNo == null ? null : customerPrintNo.trim();
	}

	public String getGrantNumDate() {
		return grantNumDate;
	}

	public void setGrantNumDate(String grantNumDate) {
		this.grantNumDate = grantNumDate == null ? null : grantNumDate.trim();
	}

	public String getDesignPeople() {
		return designPeople;
	}

	public void setDesignPeople(String designPeople) {
		this.designPeople = designPeople == null ? null : designPeople.trim();
	}

	public String getDevelopCompany() {
		return developCompany;
	}

	public void setDevelopCompany(String developCompany) {
		this.developCompany = developCompany == null ? null : developCompany.trim();
	}

	public String getExplainAll() {
		return explainAll;
	}

	public void setExplainAll(String explainAll) {
		this.explainAll = explainAll == null ? null : explainAll.trim();
	}

	public String getBackupColumn01() {
		return backupColumn01;
	}

	public void setBackupColumn01(String backupColumn01) {
		this.backupColumn01 = backupColumn01 == null ? null : backupColumn01.trim();
	}

	public String getBackupColumn02() {
		return backupColumn02;
	}

	public void setBackupColumn02(String backupColumn02) {
		this.backupColumn02 = backupColumn02 == null ? null : backupColumn02.trim();
	}

	public String getBackupColumn03() {
		return backupColumn03;
	}

	public void setBackupColumn03(String backupColumn03) {
		this.backupColumn03 = backupColumn03 == null ? null : backupColumn03.trim();
	}
}