package com.example.demo1.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo1.domain.TUser;
import com.example.demo1.domain.User;
import com.example.demo1.model.BaseResult;
import com.example.demo1.service.UserService;
import com.example.demo1.util.CompareUtil;
import com.example.demo1.util.ConstantUtil;
import com.example.demo1.util.RedisClient;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by majl on 2017/9/1.
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    @Autowired
	private RedisClient redisClient;

    @Autowired
	private UserService userService;
    @ResponseBody
    @RequestMapping("/picture")
    public BaseResult<TUser> uploadPicture(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取文件需要上传到的路径
		StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
		String path = this.getClass().getResource("/").getPath() + ConstantUtil.imgPath.USERIMG_PATH;
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		logger.debug("path=" + path);
		req.setCharacterEncoding("utf-8"); // 设置编码
		// 获得磁盘文件条目工厂
		Iterator<String> iterator = req.getFileNames();
		MultipartFile multipartFile = null;
		while (iterator.hasNext()) {
			multipartFile = req.getFile(iterator.next());
		}
		String fileName = req.getParameter("userId") + ".jpg";
		// 自定义上传图片的名字为userId.jpg
		String destPath = path + fileName;
		//String destPath = path + "ceshi.jpg";
		logger.debug("destPath=" + destPath);

		// 真正写到磁盘上
		File file = new File(destPath);
		OutputStream out = new FileOutputStream(file);
		InputStream in = multipartFile.getInputStream();
		int length = 0;
		byte[] buf = new byte[1024];
		while ((length = in.read(buf)) != -1) {
			out.write(buf, 0, length);
		}
		in.close();
		out.close();
		BaseResult<TUser> result = userService.queryUserById(req.getParameter("userId"));
		String bodyType = CompareUtil.compare(fileName);
		String[] strings = redisClient.get(bodyType).toString().split("。");
		result.getData().setBodydesc(strings[0]);
		result.getData().setBodytype(bodyType);
		result.getData().setAdvise(strings[1]);
		userService.updateUserById(result.getData());
		/*response.setCharacterEncoding("utf-8");
		response.setContentType("application/json"); 
		PrintWriter printWriter =response.getWriter();
		printWriter.write(JSON.toJSONString(result.getData())); 
		printWriter.flush();*/
		
		file.delete();
		return result;
    }
}