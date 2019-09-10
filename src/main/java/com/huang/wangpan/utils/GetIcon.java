package com.huang.wangpan.utils;

import org.springframework.stereotype.Component;
@Component
public class GetIcon {
	public  String Icons(String name) {
		String icon="";
		String []names = name.split("\\.");
		String suffix = names[names.length-1].toLowerCase();
		switch(suffix) {
		case "mp3":
			icon="#icon-file_music";
			break;
		case "zip":
			icon="#icon-file_zip";
		case "mp4":
		case "avi":
		case "wmv":
			icon="#icon-file_video";
			break;
		case "txt":
			icon="#icon-file_txt";
			break;
		case "pdf":
			icon="#icon-file_pdf";
			break;
		case "docx":
		case "doc":
			icon="file_word_office";
			break;
		case "ppt":
			icon="file_ppt_office";
			break;
		case "jpg":
		case "jpeg":
		case "png":
			icon="#icon-file_pic";
			break;
		case "xlsx":
			icon="file_excel_office";
			break;
		case "css":
			icon="#icon-file_css";
			break;
		case "java":
		case "class":
		case "jar":
			icon="#icon-file_code";
			break;
		default :
			icon="#icon-file_unknown";
		}
		return icon;
	}
}
