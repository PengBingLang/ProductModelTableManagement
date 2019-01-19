package chl.pengBingLang.service;

import chl.pengBingLang.po.ChlProductTypeTable;
import chl.pengBingLang.po.ChlProductTypeTableKey;
import chl.pengBingLang.po.OnePageVo;
import net.sf.json.JSONObject;

/**
 * 产品型号表管理service
 * 
 * @author PengBingLang 彭秉浪
 */
public interface ProductTypeService {

	/**
	 * 分页查询全部产品型号表
	 * 
	 * @param onePageVo
	 * @return
	 * @throws Exception
	 * @author PengBingLang 彭秉浪
	 */
	public JSONObject getOnePageProductType(OnePageVo onePageVo) throws Exception;

	/**
	 * 根据ID删除产品型号
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author PengBingLang 彭秉浪
	 */
	public JSONObject deleteProductTypeById(ChlProductTypeTableKey id) throws Exception;

	/**
	 * 根据ID修改产品型号
	 * 
	 * @param productType
	 * @return
	 * @throws Exception
	 * @author PengBingLang 彭秉浪
	 */
	public JSONObject updateOneProductType(ChlProductTypeTable productType) throws Exception;

	/**
	 * 根据ID查询产品型号是否存在
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author PengBingLang 彭秉浪
	 */
	public JSONObject selectByPrimaryKey(ChlProductTypeTableKey id) throws Exception;
	public JSONObject insertSelective(ChlProductTypeTable productType) throws Exception;
}
