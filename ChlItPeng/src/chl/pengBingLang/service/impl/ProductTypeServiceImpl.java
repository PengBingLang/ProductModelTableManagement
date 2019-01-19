package chl.pengBingLang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import chl.pengBingLang.mapper.ChlProductTypeTableMapper;
import chl.pengBingLang.mapper.ChlProductTypeTableMapperCustom;
import chl.pengBingLang.po.ChlProductTypeTable;
import chl.pengBingLang.po.ChlProductTypeTableKey;
import chl.pengBingLang.po.OnePageVo;
import chl.pengBingLang.service.ProductTypeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author PengBingLang 彭秉浪
 */
public class ProductTypeServiceImpl implements ProductTypeService {

	// 注入Mapper实现类
	@Autowired
	private ChlProductTypeTableMapperCustom productTypeMapper;
	@Autowired
	private ChlProductTypeTableMapper productTypeTableMapper;

	@Override
	public JSONObject getOnePageProductType(OnePageVo onePageVo) throws Exception {
		// 计算行号：
		int pageNum = onePageVo.getPage();
		int pageSize = onePageVo.getRows();
		onePageVo.setBeginNum((pageNum - 1) * pageSize + 1);
		onePageVo.setEndNum(pageNum * pageSize);

		JSONObject json = new JSONObject();
		String key = onePageVo.getKey();
		if (checkKey(key)) {
			json.put("message", "输入非法字符！");
			return json;
		}
		int i = productTypeMapper.getAllProductLine(onePageVo);// 数据总行数
		if (i > 0) {
			json.put("total", i + "");
			List<ChlProductTypeTable> onePageProductType = productTypeMapper.getOnePageProductType_ALL(onePageVo);
			json.put("rows", JSONArray.fromObject(onePageProductType));
		} else {
			json.put("message", "没有查询到任何数据");
		}
		return json;
	}

	/**
	 * 检查关键字是否包含非法字符
	 * 
	 * @param key
	 * @return
	 * @author PengBingLang 彭秉浪
	 */
	public boolean checkKey(String key) {
		if (null == key) {
			return true;
		}
		if (-1 != key.indexOf('\'')) {
			return true;
		}
		if (-1 != key.indexOf('"')) {
			return true;
		}
		if (-1 != key.indexOf(',')) {
			return true;
		}
		return false;
	}

	@Override
	public JSONObject deleteProductTypeById(ChlProductTypeTableKey id) throws Exception {
		JSONObject json = new JSONObject();
		Integer result = productTypeTableMapper.deleteByPrimaryKey(id);
		if (1 == result) {
			json.put("message", id.getProductNo() + "删除成功！");
		} else {
			json.put("message", id.getProductNo() + "删除失败！");
		}
		return json;
	}

	@Override
	public JSONObject updateOneProductType(ChlProductTypeTable productType) throws Exception {
		JSONObject json = new JSONObject();
		Integer result = productTypeTableMapper.updateByPrimaryKey(productType);
		if (1 == result) {
			json.put("message", productType.getProductNo() + "修改成功！");
		} else {
			json.put("message", productType.getProductNo() + "修改失败！");
		}
		return json;
	}

	@Override
	public JSONObject selectByPrimaryKey(ChlProductTypeTableKey id) throws Exception {
		JSONObject json = new JSONObject();
		ChlProductTypeTable productType = productTypeTableMapper.selectByPrimaryKey(id);
		if (null == productType) {
			json.put("message", "OK");
		} else {
			json.put("message", id.getSystemType() + "产品" + id.getProductNo() + "已存在！");
		}
		return json;
	}

	@Override
	public JSONObject insertSelective(ChlProductTypeTable productType) throws Exception {
		JSONObject json = new JSONObject();
		int i = productTypeTableMapper.insertSelective(productType);
		if (1 == i) {
			json.put("success", true);
			json.put("message", productType.getProductNo() + "新增成功！");
		} else {
			json.put("success", false);
			json.put("message", productType.getProductNo() + "新增失败！");
		}
		return json;
	}
}
