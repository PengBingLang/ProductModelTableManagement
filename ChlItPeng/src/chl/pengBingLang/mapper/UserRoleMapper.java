package chl.pengBingLang.mapper;

import chl.pengBingLang.po.UserRole;
import chl.pengBingLang.po.UserRoleKey;

public interface UserRoleMapper {
	int deleteByPrimaryKey(UserRoleKey key);

	int insert(UserRole record);

	int insertSelective(UserRole record);

	UserRole selectByPrimaryKey(UserRoleKey key);

	int updateByPrimaryKeySelective(UserRole record);

	int updateByPrimaryKey(UserRole record);
}