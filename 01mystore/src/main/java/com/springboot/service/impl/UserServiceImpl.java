package com.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.mapper.UserMapper;
import com.springboot.model.PageRequest;
import com.springboot.model.PageResult;
import com.springboot.model.PageUtils;
import com.springboot.model.User;
import com.springboot.service.UserServervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//
import java.util.List;

import java.util.List;

@Service
public class UserServiceImpl implements UserServervice {
    @Autowired
    private UserMapper userMapper;

    @Override
//    根据id查询用户
    public User selectUserByIdTo(Integer id) {
        return userMapper.selectUserByIdTo(id);
    }
    @Override
//    登录查询
    public Integer login(Integer id,String password) {
        return userMapper.login(id,password);
    }

    @Override
    //查询用户数量
    public Integer queryUserNumber() {
        return userMapper.queryUserNumber();
    }

    @Override
//    查询全部用户
    public User selectUserList() {
        return userMapper.selectUserList();
    }

    @Override
    public void deleteByUserIdKey(Integer id) {
        userMapper.deleteByUserIdKey(id);
    }


    @Override
    public void updateByPrimaryKey(Integer userId,String userTouxiang,String userName, String userPassword, Integer userAge, String userEmial, Integer userSex, Integer userNumber, Integer userAddressId) {
        userMapper.updateByPrimaryKey(userId,userTouxiang,userName,userPassword,userAge,userEmial,userSex,userNumber,userAddressId);
    }
    @Override
    public void upUserTouxiang(Integer userId,String userTouxiang) {
//        System.out.println(userId,userTouxiang);
        userMapper.upUserTouxiang(userId,userTouxiang);
    }

    @Override
    public List<User> getAlluser(Integer pageNum,Integer pageSize) {
//        List<User> user=userMapper.getAllUser(pageNum,pageSize);
        PageHelper.startPage(pageNum,pageSize);
        return userMapper.getAllUser();
    }

    @Override
    public void userAdd(String userName,String userTouxiang, String userPassword, Integer userAge, String userEmial, Integer userSex, Integer userNumber, Integer userAddressId) {
        userMapper.userAdd(userName,userTouxiang,userPassword,userAge,userEmial,userSex,userNumber,userAddressId);
    }

}
