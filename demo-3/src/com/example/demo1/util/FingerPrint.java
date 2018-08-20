package com.example.demo1.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

//import org.junit.Test;

/**
 * 均值哈希实现图像指纹比较
 * @author guyadong
 *
 */
public final class FingerPrint {
    /**
     * 图像指纹的尺寸,将图像resize到指定的尺寸，来计算哈希数组 
     */
    private static final int HASH_SIZE=16;
    /**
     * 保存图像指纹的二值化矩阵
     */
    private final byte[] binaryzationMatrix;
    public FingerPrint(byte[] hashValue) {
        if(hashValue.length!=HASH_SIZE*HASH_SIZE)
            throw new IllegalArgumentException(String.format("length of hashValue must be %d",HASH_SIZE*HASH_SIZE ));
        this.binaryzationMatrix=hashValue;
    }
    public FingerPrint(String hashValue) {
        this(toBytes(hashValue));
    }
    public FingerPrint (BufferedImage src){
        this(hashValue(src));
    }
    private static byte[] hashValue(BufferedImage src){
        BufferedImage hashImage = resize(src,HASH_SIZE,HASH_SIZE);
        byte[] matrixGray = (byte[]) toGray(hashImage).getData().getDataElements(0, 0, HASH_SIZE, HASH_SIZE, null); 
        return  binaryzation(matrixGray);
    }
    /**
     * 从压缩格式指纹创建{@link FingerPrint}对象
     * @param compactValue
     * @return
     */
    public static FingerPrint createFromCompact(byte[] compactValue){
        return new FingerPrint(uncompact(compactValue));
    }

    public static boolean validHashValue(byte[] hashValue){
        if(hashValue.length!=HASH_SIZE)
            return false;
        for(byte b:hashValue){
            if(0!=b&&1!=b)return false;         
        }
        return true;
    }
    public static boolean validHashValue(String hashValue){
        if(hashValue.length()!=HASH_SIZE)
            return false;
        for(int i=0;i<hashValue.length();++i){
            if('0'!=hashValue.charAt(i)&&'1'!=hashValue.charAt(i))return false;         
        }
        return true;
    }
    public byte[] compact(){
        return compact(binaryzationMatrix);
    }

    /**
     * 指纹数据按位压缩
     * @param hashValue
     * @return
     */
    private static byte[] compact(byte[] hashValue){
        byte[] result=new byte[(hashValue.length+7)>>3];
        byte b=0;
        for(int i=0;i<hashValue.length;++i){
            if(0==(i&7)){
                b=0;
            }
            if(1==hashValue[i]){
                b|=1<<(i&7);
            }else if(hashValue[i]!=0)
                throw new IllegalArgumentException("invalid hashValue,every element must be 0 or 1");
            if(7==(i&7)||i==hashValue.length-1){
                result[i>>3]=b;
            }
        }
        return result;
    }

    /**
     * 压缩格式的指纹解压缩
     * @param compactValue
     * @return
     */
    private static byte[] uncompact(byte[] compactValue){
        byte[] result=new byte[compactValue.length<<3];
        for(int i=0;i<result.length;++i){
            if((compactValue[i>>3]&(1<<(i&7)))==0)
                result[i]=0;
            else
                result[i]=1;
        }
        return result;      
    }
    /**
     * 字符串类型的指纹数据转为字节数组
     * @param hashValue
     * @return
     */
    private static byte[] toBytes(String hashValue){
        hashValue=hashValue.replaceAll("\\s", "");
        byte[] result=new byte[hashValue.length()];
        for(int i=0;i<result.length;++i){
            char c = hashValue.charAt(i);
            if('0'==c)
                result[i]=0;
            else if('1'==c)
                result[i]=1;
            else
                throw new IllegalArgumentException("invalid hashValue String");
        }
        return result;
    }
    /**
     * 缩放图像到指定尺寸
     * @param src
     * @param width
     * @param height
     * @return
     */
    private static BufferedImage resize(Image src,int width,int height){
        BufferedImage result = new BufferedImage(width, height,  
                BufferedImage.TYPE_3BYTE_BGR); 
         Graphics g = result.getGraphics();
         try{
             g.drawImage(src.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
         }finally{
             g.dispose();
         }
        return result;      
    }
    /**
     * 计算均值
     * @param src
     * @return
     */
    private static  int mean(byte[] src){
        long sum=0;
        // 将数组元素转为无符号整数
        for(byte b:src)sum+=(long)b&0xff;
        return (int) (Math.round((float)sum/src.length));
    }
    /**
     * 二值化处理
     * @param src
     * @return
     */
    private static byte[] binaryzation(byte[]src){
        byte[] dst = src.clone();
        int mean=mean(src);
        for(int i=0;i<dst.length;++i){
            // 将数组元素转为无符号整数再比较
            dst[i]=(byte) (((int)dst[i]&0xff)>=mean?1:0);
        }
        return dst;

    }
    /**
     * 转灰度图像
     * @param src
     * @return
     */
    private static BufferedImage toGray(BufferedImage src){
        if(src.getType()==BufferedImage.TYPE_BYTE_GRAY){
            return src;
        }else{
            // 图像转灰
            BufferedImage grayImage = new BufferedImage(src.getWidth(), src.getHeight(),  
                    BufferedImage.TYPE_BYTE_GRAY);
            new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null).filter(src, grayImage);
            return grayImage;       
        }
    }

    @Override
    public String toString() {
        return toString(true);
    }
    /**
     * @param multiLine 是否分行
     * @return
     */
    public String toString(boolean multiLine) {
        StringBuffer buffer=new StringBuffer();
        int count=0;
        for(byte b:this.binaryzationMatrix){
            buffer.append(0==b?'0':'1');
            if(multiLine&&++count%HASH_SIZE==0)
                buffer.append('\n');
        }
        return buffer.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FingerPrint){
            return Arrays.equals(this.binaryzationMatrix,((FingerPrint)obj).binaryzationMatrix);
        }else
            return super.equals(obj);
    }

    /**
     * 与指定的压缩格式指纹比较相似度
     * @param compactValue
     * @return
     * @see #compare(FingerPrint)
     */
    public float compareCompact(byte[] compactValue){
        return compare(createFromCompact(compactValue));
    }
    /**
     * @param hashValue
     * @return
     * @see #compare(FingerPrint)
     */
    public float compare(String hashValue){
        return compare(new FingerPrint(hashValue));
    }
    /**
     * 与指定的指纹比较相似度
     * @param hashValue
     * @return
     * @see #compare(FingerPrint)
     */
    public float compare(byte[] hashValue){
        return compare(new FingerPrint(hashValue));
    }
    /**
     * 与指定图像比较相似度
     * @param image2
     * @return
     * @see #compare(FingerPrint)
     */
    public float compare(BufferedImage image2){
        return compare(new FingerPrint(image2));
    }
    /**
     * 比较指纹相似度
     * @param src
     * @return 
     * @see #compare(byte[], byte[])
     */
    public float compare(FingerPrint src){
        if(src.binaryzationMatrix.length!=this.binaryzationMatrix.length)
            throw new IllegalArgumentException("length of hashValue is mismatch");
        return compare(binaryzationMatrix,src.binaryzationMatrix);
    }
    /**
     * 判断两个数组相似度，数组长度必须一致否则抛出异常
     * @param f1
     * @param f2
     * @return 返回相似度(0.0~1.0)
     */
    private static float compare(byte[] f1,byte[] f2){
        if(f1.length!=f2.length)
            throw new IllegalArgumentException("mismatch FingerPrint length");
        int sameCount=0;
        for(int i=0;i<f1.length;++i){
            if(f1[i]==f2[i])++sameCount;
        }
        return (float)sameCount/f1.length;
    }
    public static float compareCompact(byte[] f1,byte[] f2){
        return compare(uncompact(f1),uncompact(f2));
    }
    public static float compare(BufferedImage image1,BufferedImage image2){
        return new FingerPrint(image1).compare(new FingerPrint(image2));
    }
    /***
     * 输入两个文件目录一个是用户的文件pathuser，一个是标准文件pathstd(五个图片）
     * 输出是simPath（只包含相似度最高的图片名称，不含后缀）
     * 

     */
    /**
     * 均值哈希实现图像指纹比较
     * @author guyadong
     *
     */
    public static void main(String[] args) throws IOException {
    	
    	//1.读上来文件 
    	String userImgPath = "c:\\testinput\\";
    	String stdImgPath = "c:\\inputstd\\";
    	File[] stdFiles = new File(stdImgPath).listFiles();//遍历用户的标准对比图片
    	if (stdFiles == null) {// 如果目录为空，直接退出
  			return;
  		}
    	Map<Float,String> compareResult = new TreeMap<>();
    	FingerPrint fp2 =new FingerPrint(ImageIO.read(new File(userImgPath).listFiles()[0]));
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
    	System.out.println(value.substring(0, value.indexOf(".")));
    	
    	//System.out.println("-----------------------------------");
    	
     /*	float max[] = new float[4];
     	String[] path=new String[]{"梨型","苹果型","沙漏型","香蕉型"};
     	String pathuser="c:\\testinput\\";//输入变量之新加入的用户
     	String pathstd="c:\\inputstd\\";//标准对比图片
     	String path1="";
     	
     	float simk=0;
     	
     	 String simPath="";
     	  
  		List<File> fileList = new ArrayList<File>();
  		File file = new File(pathuser);//遍历用户的所有图片地址
  		File[] files = file.listFiles();// 获取目录下的所有文件或文件夹
  		if (files == null) {// 如果目录为空，直接退出
  			return;
  		}
  		// 遍历，目录下的所有文件
  		for (File f : files) {
  			if (f.isFile()) {
  				fileList.add(f);
  			} else if (f.isDirectory()) {
  				System.out.println(f.getAbsolutePath());
  			}
  		}
  		for (File f1 : fileList) {
  			path1=pathuser+f1.getName();//上传的文件的地址+名字
  			System.out.println("path1："+path1);
  			System.out.println(f1.getName().substring(0,f1.getName().indexOf(".")));
  		}
  		System.out.println("outpath1=="+path1);
      	 
         for(int k=0;k<=3;k++){
         	FingerPrint fp1 = new FingerPrint(ImageIO.read(new File(pathstd+path[k]+".jpg")));
         	FingerPrint fp3 =new FingerPrint(ImageIO.read(new File(path1)));
         	
         	max[k] =fp1.compare(fp3);
         	System.out.printf("sim"+k+"=%f"+"\n",max[k]);
         	System.out.printf("path=%s"+"\n",path[k]);
         	if(k>0&&(max[k] >= max[k-1])){
         		simk=max[k];
         	 simPath=path[k];//+k;//+simtxt;
         	}
         	
         }
         
      
         System.out.printf("simname=%s"+"\n",simPath);
        //System.out.printf("sim=%f"+"\n",simk);
*/	}

    
    
}