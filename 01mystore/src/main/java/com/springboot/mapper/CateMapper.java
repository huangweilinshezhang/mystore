package com.springboot.mapper;

import com.springboot.model.Cate;
import com.springboot.model.Cate;

import java.util.List;

public interface CateMapper {
    List<Cate> getAllCate();
    int deleteByPrimaryKey(Integer cateId);

    int insert(Cate record);

    int insertSelective(Cate record);

    Cate selectByPrimaryKey(Integer cateId);

    int updateByPrimaryKeySelective(Cate record);

    int updateByPrimaryKey(Cate record);

    Cate selectByclist(int cateParentId);
}