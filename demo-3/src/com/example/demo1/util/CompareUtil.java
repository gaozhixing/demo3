package com.example.demo1.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

public class CompareUtil {

	public static String compare(String fileName) throws IOException {
		//1.读上来文件 
    	String userImgPath = CompareUtil.class.getResource("/").getPath()+ConstantUtil.imgPath.USERIMG_PATH+fileName;
    	String stdImgPath = CompareUtil.class.getResource("/").getPath()+ConstantUtil.imgPath.STDIMG_Path;
    	File[] stdFiles = new File(stdImgPath).listFiles();//遍历用户的标准对比图片
    	if (stdFiles == null) {// 如果目录为空，直接退出
  			//return ;
  		}
    	Map<Float,String> compareResult = new TreeMap<>();
    	FingerPrint fp2 =new FingerPrint(ImageIO.read(new File(userImgPath)));
    	float maxKey = 0f;
    	for(int i=0;i<stdFiles.length;i++){
    		FingerPrint fp1 = new FingerPrint(ImageIO.read(stdFiles[i]));
    		float tmpKey = fp1.compare(fp2);
    		compareResult.put(tmpKey, stdFiles[i].getName());//对比值，文件名
    		if(tmpKey>maxKey){
    			maxKey = tmpKey;
    		}	
    	}
    	//2.对比值
    	String value = compareResult.get(maxKey);
    	return value.substring(0, value.indexOf("."));
    	
	}
}
