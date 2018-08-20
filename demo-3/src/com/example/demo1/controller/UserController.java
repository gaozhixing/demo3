package com.example.demo1.controller;

import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.domain.TUser;
import com.example.demo1.domain.User;
import com.example.demo1.model.BaseResult;
import com.example.demo1.service.UserService;
import com.example.demo1.util.ConstantUtil;
import com.example.demo1.util.RedisClient;
import com.example.demo1.util.StringUtil;
import com.github.pagehelper.Page;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisClient redisClient;
	
	@RequestMapping("/create")
	public BaseResult<String> createUser( TUser user) {
		/*User user = new User();
		String height = request.getParameter("height").toString();
		String weight = request.getParameter("weight").toString();
		user.setId(request.getParameter("userId"));
		user.setSex(request.getParameter("sex"));
		if (!StringUtil.isBlank(height)) {
			user.setHeight(height);
		}
		if (!StringUtil.isBlank(weight)) {
			user.setWeight(weight);
		}*/
		/*BaseResult<String> result  = userService.createUser(user);
		if (ConstantUtil.ResultCode.COMMON_SUCCESS_CODE.equals(result.getResultCode())) {
			redisClient.set(ConstantUtil.RedisHeader.User_Header+result.getData(), user);
		}*/
		System.out.println(user.getId());
		return null;
	}
	
	@RequestMapping("/delete")
	public BaseResult deleteUser(String id) {
		BaseResult result = userService.deleteUserById(id);
		if (ConstantUtil.ResultCode.COMMON_SUCCESS_CODE.equals(result.getResultCode())) {
			redisClient.delete(ConstantUtil.RedisHeader.User_Header+id);
		}
		return result;
	}
	
	/*@RequestMapping("/update")
	public BaseResult updateUser(User user) {
		BaseResult result = userService.updateUserById(user);
		if (ConstantUtil.ResultCode.COMMON_SUCCESS_CODE.equals(result.getResultCode())) {
			redisClient.set(ConstantUtil.RedisHeader.User_Header+user.getId(),user);
		}
		return result;
	}*/
	
	@RequestMapping("/query")
	public BaseResult<TUser> queryUser(String id) {
		//如果有缓存就从缓存中取
		/*if (redisClient.get(ConstantUtil.RedisHeader.User_Header+id) != null) {
			logger.info("从缓存中读取信息");
			return new BaseResult<User>(ConstantUtil.ResultCode.COMMON_SUCCESS_CODE,
					ConstantUtil.ResultCode.COMMON_SUCCESS_MSG,
					(User)redisClient.get(ConstantUtil.RedisHeader.User_Header+id));
		}*/
		//如果没有缓存或者缓存读取失败，从库里去并存到缓存中去
		BaseResult<TUser> result = userService.queryUserById(id);
		/*if (ConstantUtil.ResultCode.COMMON_SUCCESS_CODE.equals(result.getResultCode()) && result.getData() != null) {
			redisClient.set(ConstantUtil.RedisHeader.User_Header+result.getData().getId(), result.getData());
		}*/
		return result;
	}
	
	@RequestMapping("/queryList")
	public BaseResult<List<User>> queryUser(HttpServletRequest request) {
		
		return userService.queryUserList();
	}
	
	@RequestMapping("/queryListByPage")
	public BaseResult<List<User>> queryUserByPage() {
		Page page = new Page(1,10);
		return userService.queryUserListByPage(page);
	}

}
