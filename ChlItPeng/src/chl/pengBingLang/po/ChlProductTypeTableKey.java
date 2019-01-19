package chl.pengBingLang.po;

public class ChlProductTypeTableKey {
	/**
	 * 产品型号
	 */
	private String productNo;
	/**
	 * 所属系统
	 */
	private String systemType;

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo == null ? null : productNo.trim();
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType == null ? null : systemType.trim();
	}
}