package chl.pengBingLang.mapper;

import chl.pengBingLang.po.SysModule;

public interface SysModuleMapper {
	int deleteByPrimaryKey(String id);

	int insert(SysModule record);

	int insertSelective(SysModule record);

	SysModule selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SysModule record);

	int updateByPrimaryKey(SysModule record);
}