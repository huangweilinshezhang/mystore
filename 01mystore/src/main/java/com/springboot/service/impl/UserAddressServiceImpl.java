package com.springboot.service.impl;

import com.springboot.mapper.UserAddressMapper;
import com.springboot.mapper.UserMapper;
import com.springboot.model.UserAddress;
import com.springboot.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;
//    @Override
//    public int deleteByPrimaryKey(Integer addressId) {
//        return userAddressMapper.deleteByPrimaryKey(addressId);
//    }
//
//    @Override
//    public int insert(UserAddress record) {
//        return userAddressMapper.insert(record);
//    }
//
////    @Override
////    public int insertSelective(UserAddress record) {
////        return userAddressMapper.insertSelective(record);
////    }
//
//    @Override
//    public UserAddress selectByPrimaryKey(Integer addressId) {
//        return userAddressMapper.selectByPrimaryKey(addressId);
//    }
//
//    @Override
//    public int updateByPrimaryKeySelective(UserAddress record) {
//        return userAddressMapper.updateByPrimaryKeySelective(record);
//    }
//
//    @Override
//    public int updateByPrimaryKey(UserAddress record) {
//        return userAddressMapper.updateByPrimaryKey(record);
//    }

    @Override
    public int insertSelective(int userId, String consignee, String address, int addressStaus, String userTel) {
        return userAddressMapper.insertSelective(userId,consignee,address,addressStaus,userTel);
    }

    @Override
    public List<UserAddress> selectByUserId(int userId) {
        return userAddressMapper.selectByUserId(userId);
    }

    @Override
    public void deleteByUserId(Integer id) {
        userAddressMapper.deleteByUserId(id);
    }

    @Override
    public void updateByPrimaryKey(String userTel,String address, Integer userId, int userStaus) {
        userAddressMapper.updateByPrimaryKey(userTel,address,userId,userStaus);
    }

    @Override
    public UserAddress selectByUserIdAnduserStaus(Integer userId, int userStaus) {
        return userAddressMapper.selectByUserIdAnduserStaus(userId,userStaus);
    }

    @Override
    public UserAddress selectByPrimaryKey(Integer userAddressId) {
        return userAddressMapper.selectByPrimaryKey(userAddressId);
    }
}
