package com.example.demo1.util;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class RedisClient {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisClient.class);
	
	@Resource
    private RedisTemplate<String, Object> redisTemplate; //jdbcTemplate    

     // 功能描述：设置key-value到redis中    
    public boolean set(String key ,Object value){
        try{
        	redisTemplate.opsForValue().set(key, value);
        	logger.info("缓存录入key={},value={}", key, JSON.toJSONString(value));
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }    
    }        

     // 功能描述：通过key获取缓存里面的值
    public Object get(String key){
    	if (redisTemplate.hasKey(key)) {
    		logger.info("缓存查询结果key={},value={}", key, JSON.toJSONString(redisTemplate.opsForValue().get(key)));
    		return redisTemplate.opsForValue().get(key);
    	}
    	logger.info("缓存未命中,改从库中查找");
    	return null;
    }  
    
    // 功能描述：删除缓存
    public void delete(String key){
    	logger.info("从缓存中删除key={}", key);
    	redisTemplate.delete(key);
    }        

}
