package com.example.demo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.domain.User;
import com.example.demo1.model.BaseResult;
import com.example.demo1.model.DescException;
import com.example.demo1.model.ExceptionEnum;
import com.example.demo1.util.ExceptionHandle;
import com.example.demo1.util.ResultUtil;

@RestController
public class AopTestController {
	
	@Autowired
	private ExceptionHandle hand;
	
	@RequestMapping(value = "/getResult",method = RequestMethod.GET)
	@ResponseBody
    public BaseResult getResult(@RequestParam("name") String name, @RequestParam("pwd") String pwd){
        BaseResult result = ResultUtil.success();
            User user = null;
            if (name.equals("zzp")){
                result =  ResultUtil.success(new User());
            }else if (name.equals("pzz")){
            	throw new DescException(ExceptionEnum.USER_NOT_FIND);
//                result =  ResultUtil.error(ExceptionEnum.USER_NOT_FIND);
            }else{
//                int i = 1/0;
            	user.toString();//抛出空指针，自动处理
            }
        return result;
    }

}
