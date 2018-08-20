package com.example.demo1.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo1.domain.TUser;
import com.example.demo1.domain.TUserMapper;
import com.example.demo1.domain.User;
import com.example.demo1.domain.UserDao;
import com.example.demo1.model.BaseResult;
import com.example.demo1.model.ExceptionEnum;
import com.example.demo1.model.PageResult;
import com.example.demo1.util.ConstantUtil;
import com.example.demo1.util.ResultUtil;
import com.example.demo1.util.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private TUserMapper mapper;
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
			
	public BaseResult<String> createUser(TUser user) {
		BaseResult<String> result = ResultUtil.success();
		if (user != null) {
			if (!StringUtil.isBlank(user.getId())) {
				mapper.insert(user);
				result.setData(user.getId());
				return result;
			}
			return ResultUtil.error(ConstantUtil.ResultCode.COMMON_ERROR_CODE, "请求参数Id为空");
		}
		return ResultUtil.error(ExceptionEnum.REQUEST_IS_NULL);
	}
	
	public BaseResult deleteUserById(String id) {
		BaseResult result = ResultUtil.success();
		if (id != null) {
			userDao.deleteUserById(id);
			return result;
		}
		return ResultUtil.error(ConstantUtil.ResultCode.COMMON_ERROR_CODE, "请求参数id为空");		
	}
	
	public BaseResult updateUserById(TUser user) {
		BaseResult result = ResultUtil.success();
		if (user == null) {
			return ResultUtil.error(ConstantUtil.ResultCode.COMMON_ERROR_CODE, "传入User对象为空");
		}
		if (user.getId() == null) {
			return ResultUtil.error(ConstantUtil.ResultCode.COMMON_ERROR_CODE, "请求参数id为空");
		}
		mapper.updateByPrimaryKey(user);
		return result;
	}
	
	public BaseResult<TUser> queryUserById(String id) {
		BaseResult<TUser> result = ResultUtil.success();
		if (id == null) {
			return ResultUtil.error(ConstantUtil.ResultCode.COMMON_ERROR_CODE, "请求参数id为空");
		}
		TUser user = mapper.selectByPrimaryKey(id);
		if (user == null) {
			return ResultUtil.error(ExceptionEnum.REQUEST_IS_NULL);
		}
		result.setData(user);
		return result;
	}
	
	public BaseResult<List<User>> queryUserList() {
		BaseResult<List<User>> result = ResultUtil.success();
		List<User> userList = userDao.queryUserList();
		if (userList != null && userList.size() > 0) {
			result.setData(userList);
			return result;
		}
		return ResultUtil.error(ExceptionEnum.REQUEST_IS_NULL);
	}
	
	public PageResult<User> queryUserListByPage(Page page) {
		PageResult<User> result = (PageResult<User>)ResultUtil.success();
		PageHelper.startPage(page);
		List<User> userList = userDao.queryUserList();
		result.setResult(userList);
		result.setCount(((Page)userList).getTotal());
		result.setPageNum(page.getPageNum());
		result.setPageSize(page.getPageSize());
		return result;
	}

}
