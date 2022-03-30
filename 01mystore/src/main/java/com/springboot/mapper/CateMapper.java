package com.springboot.mapper;
import com.springboot.model.Cate;
import java.util.List;

public interface CateMapper{
    int deleteByPrimaryKey(Integer cateId);

    int insert(Cate record);

    int insertSelective(Cate record);

    Cate selectByPrimaryKey(Integer cateId);

    int updateByPrimaryKeySelective(Cate record);

    int updateByPrimaryKey(Cate record);

    List<Cate> getAllCate();

    Cate selectByclist(int cateParentId);

    List<String> selectCateFid();

    List<Cate> selectCateClist(int cateParentId);

    int toAddCate(Integer cateParentId, String cateName);

    List<Cate> selectListByPrimaryKey(Integer productFid);
}
