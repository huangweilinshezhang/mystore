package com.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.mapper.CateMapper;
import com.springboot.model.Cate;
import com.springboot.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CateServiceImpl implements CateService {
    @Autowired
    private CateMapper cateMapper;

    @Override
    public List<Cate> getAllCate(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return cateMapper.getAllCate();
    }

    @Override
    public Cate selectByPrimaryKey(Integer cateId) {
        return cateMapper.selectByPrimaryKey(cateId);
    }

    @Override
    public Cate selectByclist(int cateParentId) {

        return cateMapper.selectByclist(cateParentId);
    }

//    @Override
//    public Cate getAllCate() {
//        return (Cate) cateMapper.getAllCate();
//    }
}
