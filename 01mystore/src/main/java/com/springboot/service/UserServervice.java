package com.springboot.service;

import com.springboot.model.User;

import java.util.List;


public interface UserServervice {
    User selectUserByIdTo(Integer id);

    Integer login(Integer id,String password);

    Integer queryUserNumber();

    User selectUserList(Integer pageNum,Integer pageSize);

    void deleteByUserIdKey(Integer id);

    void userAdd(String userName,String userTouxiang, String userPassword, Integer userAge, String userEmail, Integer userSex, String userNumber, Integer userAddressId,Integer userStat);

    void updateByPrimaryKey(Integer userId,String userTouxiang,String userName, String userPassword, Integer userAge, String userEmail, Integer userSex, String userNumber, Integer userAddressId);

    void upUserTouxiang(Integer userId,String userTouxiang);

    List<User> getAlluser(Integer pageNum,Integer pageSize);

    User selectUserByEmailAndPwd(String userEmail, String userPassword);

    void updateUserAddress(int addressId,int userId, Integer userStat);

//    List<User> getAlluser();
}
