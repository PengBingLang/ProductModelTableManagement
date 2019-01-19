package chl.pengBingLang.mapper;

import chl.pengBingLang.po.RoleModule;
import chl.pengBingLang.po.RoleModuleKey;

public interface RoleModuleMapper {
    int deleteByPrimaryKey(RoleModuleKey key);

    int insert(RoleModule record);

    int insertSelective(RoleModule record);

    RoleModule selectByPrimaryKey(RoleModuleKey key);

    int updateByPrimaryKeySelective(RoleModule record);

    int updateByPrimaryKey(RoleModule record);
}