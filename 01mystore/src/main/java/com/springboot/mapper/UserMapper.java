package com.springboot.mapper;

import com.springboot.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
//@Mapper
public interface UserMapper {
//    int deleteByPrimaryKey(Integer userId);
//
//    int insert(User record);
//
//    int insertSelective(User record);
//
//    User selectByPrimaryKey(Integer id);
    User selectUserByIdTo(Integer id);

    Integer login(Integer id, String password);

    Integer queryUserNumber();

    User selectUserList();

    int deleteByUserIdKey(Integer id);

    void updateByPrimaryKey(Integer userId,String userTouxiang,String userName, String userPassword, Integer userAge, String userEmail, Integer userSex, Integer userNumber, Integer userAddressId);

    void userAdd(String userName, String userTouxiang,String userPassword, Integer userAge, String userEmail, Integer userSex, Integer userNumber, Integer userAddressId);

    void upUserTouxiang(Integer userId, String userTouxiang);

    List<User> getAllUser();

//
//    int updateByPrimaryKeySelective(User record);
//
//    int updateByPrimaryKey(User record);
}