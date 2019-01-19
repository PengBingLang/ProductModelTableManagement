package chl.pengBingLang.po;

/**
 * @author PengBingLang 彭秉浪
 */
public class OnePageVo {
	/**
	 * 第几页
	 */
	private Integer page = 1;
	/**
	 * 每页几行
	 */
	private Integer rows = 10;
	/**
	 * 起始行号
	 */
	private Integer beginNum = 1;
	/**
	 * 结束行号
	 */
	private Integer endNum = 10;

	/**
	 * 排序关键字
	 */
	private String sort = "productNo";
	/**
	 * 升序降序(asc,desc)
	 */
	private String order = "asc";
	/**
	 * 查询范围
	 */
	private String type = "ALL";
	/**
	 * 模糊查询关键字
	 */
	private String key = null;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getBeginNum() {
		return beginNum;
	}

	public void setBeginNum(Integer beginNum) {
		this.beginNum = beginNum;
	}

	public Integer getEndNum() {
		return endNum;
	}

	public void setEndNum(Integer endNum) {
		this.endNum = endNum;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
