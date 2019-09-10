package com.huang.wangpan.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {
	public static synchronized void copyfile(String source, String dest) throws Exception {
		FileChannel inputChannel = null;
		FileChannel OutputChannel = null;
		try {
			inputChannel = new FileInputStream(source).getChannel();
			OutputChannel = new FileOutputStream(dest).getChannel();
			OutputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		} finally {
			inputChannel.close();
			OutputChannel.close();
		}

	}

	public static File getZipFile(String srcDir, String filePath, String fileName) throws Exception {
		File sourceDir = new File(srcDir);
		compress(sourceDir, filePath, new ZipOutputStream(new FileOutputStream(filePath + fileName)));
		File f = new File(filePath + fileName);
		return f;
	}

	private static byte[] getBytes(File sourceDir) {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		if (!sourceDir.exists()) {
			try {
				throw new Exception("压缩失败, 源文件夹不存在");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		File[] files = sourceDir.listFiles();
		if (files == null || files.length == 0) {
			try {
				throw new Exception("压缩失败, 源文件夹不存在");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		BufferedInputStream bis = null;
		byte[] buff = new byte[1024 * 10];
		FileInputStream fis = null;
		try {
			for (File file : files) {
				String fName = file.getName();
				if (file.isFile()) {
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis, 1024 * 10);
					int length;
					while ((length = bis.read(buff, 0, 1024 * 10)) != -1) {
						bout.write(buff, 0, length);
					}
					fis.close();
				} 
			}
//			sourceDir.delete();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != bis) {
					bis.close();
				}
				if (null != fis) {
					fis.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return bout.toByteArray();

	}

	private static void compress(File sourceDir, String baseDir, ZipOutputStream zos) {
		if (!sourceDir.exists()) {
			try {
				throw new Exception("压缩失败, 源文件夹不存在");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		File[] files = sourceDir.listFiles();
		if (files == null || files.length == 0) {
			try {
				throw new Exception("压缩失败, 源文件夹不存在");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		BufferedInputStream bis = null;
		byte[] buff = new byte[1024 * 10];
		FileInputStream fis = null;
		try {
			for (File file : files) {
				String fName = file.getName();
				if (file.isFile()) {
					ZipEntry zipEntry = new ZipEntry(baseDir + fName);
					zos.putNextEntry(zipEntry);
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis, 1024 * 10);
					int length;
					while ((length = bis.read(buff, 0, 1024 * 10)) != -1) {
						zos.write(buff, 0, length);
					}
					fis.close();
					file.delete();
				} else if (file.isDirectory()) {
					compress(file, baseDir + fName + "/", zos);
					file.delete();
				}
			}
			sourceDir.delete();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != bis) {
					bis.close();
				}
				if (null != fis) {
					fis.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
