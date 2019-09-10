package com.huang.wangpan.model;

import org.springframework.stereotype.Component;

@Component
public class Upfile {
	private String md5;
	private String realpath;
	private int id;
	private String filesize;
	public String getRealpath() {
		return realpath;
	}
	public void setRealpath(String realpath) {
		this.realpath = realpath;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getFilesize() {
		return filesize;
	}
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Upfile [md5=" + md5 + ", realpath=" + realpath + ", id=" + id + ", filesize=" + filesize + "]";
	}

	
}
