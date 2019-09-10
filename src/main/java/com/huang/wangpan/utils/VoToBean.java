package com.huang.wangpan.utils;

import com.huang.wangpan.dto.Fileim;
import com.huang.wangpan.model.UserFile;
import org.springframework.stereotype.Component;

@Component
public class VoToBean {
	public static UserFile fileimToUserFile(Fileim fim, String uid, String currentpath) {
		UserFile uf = new UserFile();
		uf.setFilesize(fim.getFilesize());
		uf.setIconSign(fim.getIconsign());
		uf.setMtime(fim.getMtime());
		uf.setUid(uid);
		uf.setVirtualpath(currentpath+fim.getFilename());
		if(fim.getIconsign().trim()!="#icon-folder") {
			uf.setRealpath("H:/fileUploadRepository/"+fim.getFilename());
		}
		return uf;
	}
}
