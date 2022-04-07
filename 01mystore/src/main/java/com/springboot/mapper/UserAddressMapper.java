package com.springboot.mapper;

import com.springboot.model.UserAddress;

import java.util.List;

public interface UserAddressMapper {
//    int deleteByPrimaryKey(Integer addressId);
//
//    int insert(UserAddress record);
//
//    int insertSelective(UserAddress record);
//
//    UserAddress selectByPrimaryKey(Integer addressId);
//
//    int updateByPrimaryKeySelective(UserAddress record);
//
//    int updateByPrimaryKey(UserAddress record);

    int insertSelective(int userId, String consignee, String address, int addressStaus, String userTel);

    List<UserAddress> selectByUserId(int userId);

    void deleteByUserId(Integer id);

    void updateByPrimaryKey(String userTel, String address, Integer userId, int userStaus);

    UserAddress selectByUserIdAnduserStaus(Integer userId, int userStaus);

    UserAddress selectByPrimaryKey(Integer userAddressId);
}