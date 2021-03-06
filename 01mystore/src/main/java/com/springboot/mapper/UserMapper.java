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

    User selectUserList(Integer pageNum,Integer pageSize);

    int deleteByUserIdKey(Integer id);

    void updateByPrimaryKey(Integer userId, String userTouxiang, String userName, String userPassword, Integer userAge, String userEmail, Integer userSex, String userNumber, Integer userAddressId);

    void userAdd(String userName, String userTouxiang, String userPassword, Integer userAge, String userEmail, Integer userSex, String userNumber, Integer userAddressId, Integer userStat);

    void upUserTouxiang(Integer userId, String userTouxiang);

    List<User> getAllUser();

    User selectUserByEmailAndPwd(String userEmail, String userPassword);

    void updateUserAddress(int addressId,int userId, Integer userStat);

//
//    int updateByPrimaryKeySelective(User record);
//
//    int updateByPrimaryKey(User record);
}