package chl.pengBingLang.mapper;

import chl.pengBingLang.po.ChlProductTypeTable;
import chl.pengBingLang.po.ChlProductTypeTableKey;

public interface ChlProductTypeTableMapper {
	int deleteByPrimaryKey(ChlProductTypeTableKey key);

	int insert(ChlProductTypeTable record);

	int insertSelective(ChlProductTypeTable record);

	ChlProductTypeTable selectByPrimaryKey(ChlProductTypeTableKey key);

	int updateByPrimaryKeySelective(ChlProductTypeTable record);

	int updateByPrimaryKey(ChlProductTypeTable record);
}