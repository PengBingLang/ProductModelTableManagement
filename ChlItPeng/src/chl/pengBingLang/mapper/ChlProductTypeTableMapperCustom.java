package chl.pengBingLang.mapper;

import java.util.List;

import chl.pengBingLang.po.ChlProductTypeTable;
import chl.pengBingLang.po.OnePageVo;

public interface ChlProductTypeTableMapperCustom {

	/**
	 * 全部查询
	 */
	public List<ChlProductTypeTable> getOnePageProductType_ALL(OnePageVo onePageVo) throws Exception;

	/**
	 * 查询总行数
	 */
	public int getAllProductLine(OnePageVo onePageVo) throws Exception;
}