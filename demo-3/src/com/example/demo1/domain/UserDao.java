package com.example.demo1.domain;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserDao {
	
    int createUser(@Param("user")User user);
	
	int deleteUserById(@Param("id")String id);
	
	int updateUserById(@Param("user")User user);
	
	User queryUserById(@Param("id")String id);
	
	List<User> queryUserList();

}
