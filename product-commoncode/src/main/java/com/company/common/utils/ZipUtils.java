package com.company.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Zip压缩解压文件工具类
 * @author Lauchikeung
 */
public class ZipUtils {
	/**
	 * 压缩单个或多个文件
	 * @param file
	 * @param zipFile
	 */
	public static void compress(File file,File zipFile){
		try {
			compressFile(zipFile,file);
		} catch (Exception e) {
			throw new RuntimeException(e+"压缩异常");
		}
	}
	
	public static void compressFile(ZipOutputStream zOutput,File zipFile,File...baseFiles){
		for (int i = 0; i < baseFiles.length; i++) {
			String basePath = outPath(baseFiles[i].getParent());
			if(baseFiles[i].isDirectory()){
				File[] files = baseFiles[i].listFiles();
				try {
					if(files.length>0){
						compressFile(zOutput,zipFile,files);
					}else{
						zOutput.putNextEntry(new ZipEntry(basePath+baseFiles[i].getName()+File.separator));
					}
				} catch (Exception e) {
					throw new RuntimeException(e+"压缩异常");
				}
			}else{
				try (InputStream input = new FileInputStream(baseFiles[i]);){
					zOutput.putNextEntry(new ZipEntry(basePath+baseFiles[i].getName()));
					byte[] bufferArray = new byte[1024*4];
					int temp = 0;
					while((temp = input.read(bufferArray))!=-1){
						zOutput.write(bufferArray, 0, temp);
					}
				} catch (Exception e) {
					throw new RuntimeException(e+"压缩异常");
				}
			}
		}
	}
	
	public static void compressFile(File zipFile,File...baseFiles){
		try (ZipOutputStream output = new ZipOutputStream(new FileOutputStream(zipFile),Charset.forName("gbk"));){
			compressFile(output,zipFile, baseFiles);
		} catch (Exception e) {
			throw new RuntimeException(e+"压缩异常");
		}
	}
	
	public static String outPath(String path){		
		path = path.substring(3,path.length());
		if(!"".equals(path))
			path = path+File.separator;
		return path;
	}
	
	/**
	 * 解压单个或多个文件
	 * @param zipFile
	 * @param outFile
	 */
	public static void uncompress(File zipFile,File outFile){
		OutputStream output = null;
		try (ZipFile zFile = new ZipFile(zipFile,Charset.forName("gbk"));
			 ZipInputStream zipInput = new ZipInputStream(new FileInputStream(zipFile),Charset.forName("gbk"));)
		{
			ZipEntry zipEntry = null;
			while((zipEntry = zipInput.getNextEntry())!=null){
				File file = new File(outFile+File.separator+zipEntry.getName());
				if(zipEntry.isDirectory()){
					file.mkdir();
					continue;
				}
				if(!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				if(!file.exists()&&!zipEntry.isDirectory())
					file.createNewFile();	
				InputStream input = zFile.getInputStream(zipEntry);
				int temp = 0;
				byte[] buffArray = new byte[1024*4];
				output = new FileOutputStream(file);
				while((temp = input.read(buffArray))!=-1){
					output.write(buffArray, 0, temp);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e+"解压异常");
		} finally{
			try {
				if(output!=null)
					output.close();
			} catch (IOException e) {
				throw new RuntimeException(e+"关闭输出流异常");
			}
		}
	}
}
