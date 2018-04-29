package com.company.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件操作类 工具类
 * @author Lauchikeung
 *
 */
public class FileUtils {
	//http://blog.csdn.net/u014263388/article/details/52098719
	//import java.nio.file.Files;JAVA7拷贝文件 速度更快
	//Time taken by FileStreams Copy = 127572360
	//Time taken by FileChannels Copy = 10449963
	//Time taken by Java7 Files Copy = 10808333
	//Time taken by Apache Commons IO Copy = 17971677
	
	/**
	 * 拷贝文件
	 * @param oldPath
	 * @param newPath
	 * @return true 成功，false 失败
	 */
	public static boolean copyFile(String oldPath,String newPath){
		File oldFile = new File(oldPath);
		File newFile = new File(newPath);
		InputStream input = null;
		OutputStream output = null;
		try {
			byte[] bufferArray = new byte[4*1024];//缓冲区 4kb
			input = new FileInputStream(oldFile);
			output = new FileOutputStream(newFile);
			int temp;
			while((temp = input.read(bufferArray)) != -1){
				output.write(bufferArray,0,temp);
			}
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if(input!=null)
					input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(output!=null)
					output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 删除文件
	 * @param path
	 */
	public static void deleteFile(String path){
		File file = new File(path);
		if(file.exists()){
			file.delete();
		}
	}
}
