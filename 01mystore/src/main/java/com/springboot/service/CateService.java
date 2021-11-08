package com.springboot.service;

import com.springboot.model.Cate;
import com.springboot.model.Product;

import java.util.List;

public interface CateService {
       List<Cate> getAllCate(Integer pageNum, Integer pageSize);

      Cate selectByPrimaryKey(Integer cateId);

      Cate selectByclist(int cateParentId);
}
