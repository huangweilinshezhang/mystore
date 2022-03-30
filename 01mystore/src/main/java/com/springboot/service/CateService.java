package com.springboot.service;

import com.springboot.model.Cate;
//import com.springboot.model.cate;

import java.util.List;

public interface CateService {

    List<Cate> getAllCate(Integer pageNum, Integer pageSize);

      Cate selectByPrimaryKey(Integer cateId);

      Cate selectByclist(int cateParentId);

    List<String> selectCateFid();

    List<Cate> selectCateClist(int cateParentId);

    int toCateAdd(Integer cateParentId, String cateName);

    int deleteByPrimaryKey(Integer cateId);

    List<Cate> selectListByPrimaryKey(Integer productFid);
}
