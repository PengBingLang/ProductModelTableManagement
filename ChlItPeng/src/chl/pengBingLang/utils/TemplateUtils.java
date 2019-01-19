package chl.pengBingLang.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 根据模板文件，填充数据后，输出
 * 
 * @author PengBingLang 彭秉浪
 */
public class TemplateUtils {
	/**
	 * freemarker模板配置
	 */
	private Configuration configuration;

	/**
	 * TemplateUtils_构造函数
	 */
	public TemplateUtils() {
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		// configuration.setEncoding(Locale.CHINA, "UTF-8");
		// 设置模板文件所在的目录
		configuration.setClassForTemplateLoading(this.getClass(), "/template");
	}

	public StringBuffer createBuffer(String templateName, Map<String, Object> model) {
		Template template = null;
		try {
			// 获取模板信息
			template = configuration.getTemplate(templateName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringWriter out = new StringWriter();
		try {
			template.process(model, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.getBuffer();
	}

	public static void main(String[] args) {
		TemplateUtils t = new TemplateUtils();
		String templateName = "mailTemplate.html";
		Map map = new HashMap<String, String>();
		map.put("title", "test表格标题");
		map.put("no", "123");
		StringBuffer buffer = t.createBuffer(templateName, map);
		System.out.println(buffer.toString());
	}
}
