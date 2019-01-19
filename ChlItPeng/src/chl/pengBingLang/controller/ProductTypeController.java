package chl.pengBingLang.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import chl.pengBingLang.po.ChlProductTypeTable;
import chl.pengBingLang.po.ChlProductTypeTableKey;
import chl.pengBingLang.po.OnePageVo;
import chl.pengBingLang.service.ProductTypeService;
import net.sf.json.JSONObject;

/**
 * 产品型号表管理controller
 * 
 * @author PengBingLang 彭秉浪
 */
@Controller
@RequestMapping("ProductModel")
public class ProductTypeController {

	@Autowired
	private ProductTypeService productTypeService;

	@RequestMapping(value = "/getOnePage", method = { RequestMethod.POST })
	public void getOnePage(HttpServletRequest request, HttpServletResponse response, OnePageVo onePageVo)
			throws Exception {
		JSONObject json = productTypeService.getOnePageProductType(onePageVo);
		response.setContentType("text/json; charset=utf-8");
		response.getWriter().write(json.toString());
	}

	@RequestMapping(value = "/deleteOne", method = { RequestMethod.POST })
	public void deleteOne(HttpServletRequest request, HttpServletResponse response, ChlProductTypeTableKey id)
			throws Exception {
		JSONObject json = productTypeService.deleteProductTypeById(id);
		response.setContentType("text/json; charset=utf-8");
		response.getWriter().write(json.toString());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/updateOne", method = { RequestMethod.POST })
	public void updateOne(HttpServletRequest request, HttpServletResponse response, ChlProductTypeTable productType)
			throws Exception {
		JSONObject json = productTypeService.updateOneProductType(productType);
		response.setContentType("text/json; charset=utf-8");
		response.getWriter().write(json.toString());
	}

	@RequestMapping(value = "/selectOne", method = { RequestMethod.POST })
	public void selectOne(HttpServletRequest request, HttpServletResponse response, ChlProductTypeTableKey id)
			throws Exception {
		JSONObject json = productTypeService.selectByPrimaryKey(id);
		response.setContentType("text/json; charset=utf-8");
		response.getWriter().write(json.toString());
	}

	@RequestMapping(value = "/insertOne", method = { RequestMethod.POST })
	public void insertOne(HttpServletRequest request, HttpServletResponse response, ChlProductTypeTable productType)
			throws Exception {
		JSONObject json = productTypeService.insertSelective(productType);
		response.setContentType("text/json; charset=utf-8");
		response.getWriter().write(json.toString());
	}
}
