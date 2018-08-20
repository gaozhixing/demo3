package com.example.demo1.util;

public class ConstantUtil {
	
	public final static class ResultCode {
		/**
		 * 通用错误返回码
		 */
		public static final String COMMON_ERROR_CODE = "999";
		/**
		 * 通用成功返回码
		 */
		public static final String COMMON_SUCCESS_CODE = "000";
		/**
		 * 通用成功返回mag
		 */
		public static final String COMMON_SUCCESS_MSG = "成功";
	}
	
	public final static class RedisHeader {
		/**
		 * 存储User的key头部
		 */
		public static final String User_Header = "user:";
	}
	public final static class imgPath{
		/**
		 * 存储用户上传的图片的本地路径
		 */
		public static final String USERIMG_PATH = "userImg/";
		/**
		 * 本地匹配库
		 */
		public static final String STDIMG_Path = "local/";
		/**
		 * 静态资源地址
		 */
		public static final String STATIC_IMG = "file:C:/Users/Xing/inputstd";
		/**
		 * 静态资源访问路径
		 */
		public static final String STATIC_URL = "/img_url/**";
		
	}

}
