package com.springboot.service;

import com.springboot.model.PageRequest;
import com.springboot.model.PageResult;
import com.springboot.model.User;

import java.util.List;


public interface UserServervice {
    User selectUserByIdTo(Integer id);

    Integer login(Integer id,String password);


    Integer queryUserNumber();

    User selectUserList(Integer pageNum,Integer pageSize);

    void deleteByUserIdKey(Integer id);

//    void updateByPrimaryKey(Integer id);

    void userAdd(String userName,String userTouxiang, String userPassword, Integer userAge, String userEmail, Integer userSex, Integer userNumber, Integer userAddressId);

    void updateByPrimaryKey(Integer userId,String userTouxiang,String userName, String userPassword, Integer userAge, String userEmail, Integer userSex, Integer userNumber, Integer userAddressId);

    void upUserTouxiang(Integer userId,String userTouxiang);

    List<User> getAlluser(Integer pageNum,Integer pageSize);

//    List<User> getAlluser();
}
