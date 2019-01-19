package chl.pengBingLang.mapper;

import chl.pengBingLang.po.PermissionVo;

/**
 * @author PengBingLang 彭秉浪
 */
public interface PermissionMapper {

	/**
	 * 查询是否授权
	 * 
	 * @param p
	 * @return
	 * @author PengBingLang 彭秉浪
	 */
	public int selectIsGrant(PermissionVo p) throws Exception;

	/**
	 * 是否拒绝权限
	 * 
	 * @param p
	 * @return
	 * @author PengBingLang 彭秉浪
	 */
	public int selectIsRefuse(PermissionVo p) throws Exception;

}
