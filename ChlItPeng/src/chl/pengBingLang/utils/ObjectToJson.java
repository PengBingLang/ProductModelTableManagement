package chl.pengBingLang.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * java对象转为Json数据对象
 * 
 * @author PengBingLang 彭秉浪
 */
public class ObjectToJson {

	/**
	 * JDBC中ResultSet转换为Json
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @throws JSONException
	 * @author PengBingLang 彭秉浪
	 */
	public static JSONObject resultSetToJsonObject(ResultSet rs) throws SQLException, JSONException {
		// json对象
		JSONObject jsonObj = new JSONObject();

		// 获取列数
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();

		// 遍历ResultSet中的每条数据
		if (rs.next()) {
			// 遍历每一列
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnLabel(i);
				String value = rs.getString(columnName);
				jsonObj.put(columnName, value);
			}
		}
		return jsonObj;
	}

	/**
	 * JDBC中ResultSet转换为Json数组
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @throws JSONException
	 * @author PengBingLang 彭秉浪
	 */
	public static JSONArray resultSetToJsonArry(ResultSet rs) throws SQLException, JSONException {
		// json数组
		JSONArray array = new JSONArray();

		// 获取列数
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();

		// 遍历ResultSet中的每条数据
		while (rs.next()) {
			JSONObject jsonObj = new JSONObject();
			// 遍历每一列
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnLabel(i);
				String value = rs.getString(columnName);
				jsonObj.put(columnName, value);
			}
			array.add(jsonObj);
		}
		return array;
	}
}
