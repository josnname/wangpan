package com.huang.wangpan.utils;

import com.huang.wangpan.dto.Fileim;
import com.huang.wangpan.model.UserFile;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PathAnalysis {
	
	public List<Fileim> getNewFloder(List<UserFile> ufs, String currentpath, int page){
		List<Fileim> fms = new ArrayList<Fileim>();
		Map<String, String> m = new Hashtable<String, String>();
		for (int i = 0; i < ufs.size(); i++) {
			UserFile temp = ufs.get(i);
			String path = temp.getVirtualpath();
			String temps[] = path.split("/");
			if(temp.getSurvival()==1) {
				if (path.startsWith(currentpath)) {
					if (m.get(temps[page+1]) == null) {
						Fileim fm = new Fileim();
						fm.setFilename(temps[page+1]);
						m.put(temps[page+1], "");
						fm.setFilesize(temp.getFilesize());
						fm.setIconsign(temp.getIconSign());
						fm.setMtime(temp.getMtime());
						fms.add(fm);
					}
				}
			}
			
		}
		return fms;
		
	}
	public List<Fileim> SuperiorCatalogue(List<UserFile> ufs, String currentpath, int page) {
		List<Fileim> fms = new ArrayList<Fileim>();
		Map<String, String> m = new Hashtable<String, String>();
		for (int i = 0; i < ufs.size(); i++) {
			UserFile temp = ufs.get(i);
			String path = temp.getVirtualpath();
			String temps[] = path.split("/");
			if(temp.getSurvival()==1) {
				if (path.startsWith(currentpath)) {
					if (m.get(temps[page]) == null) {
						Fileim fm = new Fileim();
						fm.setFilename(temps[page]);
						m.put(temps[page], "");
						fm.setFilesize(temp.getFilesize());
						fm.setIconsign(temp.getIconSign());
						fm.setMtime(temp.getMtime());
						fms.add(fm);
					}
				}
			}
			
		}
		return fms;
	}


	public List<Fileim> getSubdirectories(List<UserFile> ufs, String currentpath, int page) {
		List<Fileim> fms = new ArrayList<Fileim>();
		Map<String, String> m = new Hashtable<String, String>();
		for (int i = 0; i < ufs.size(); i++) {
			UserFile temp = ufs.get(i);
			String path = temp.getVirtualpath();
			if(temp.getSurvival()==1) {
				if (path.startsWith(currentpath)) {
					String temps[] = path.split("/");
					if (temps.length > page + 2) {
						if (m.get(temps[page + 2]) == null) {
							Fileim fm = new Fileim();
							fm.setFilename(temps[page + 2]);
							m.put(temps[page + 2], "");
							fm.setFilesize(temp.getFilesize());
							fm.setIconsign(temp.getIconSign());
							fm.setMtime(temp.getMtime());
							fms.add(fm);
						}

					}
				}
			}
		}
		return fms;
	}

	public List<Fileim> getIndexPath(List<UserFile> ufs) {
		List<Fileim> ufs1 = new ArrayList<Fileim>();
		Map<String, String> m = new Hashtable<String, String>();
		for (int i = 0; i < ufs.size(); i++) {
			UserFile temp = ufs.get(i);
			Fileim uf = new Fileim();
			String path = temp.getVirtualpath();
			StringTokenizer st = new StringTokenizer(path, "/");
			String filename = st.nextToken();
			if(temp.getSurvival()==1) {
				if (m.get(filename) == null) {
					m.put(filename, "");
					uf.setFilename(filename);
					uf.setMtime(temp.getMtime());
					uf.setIconsign(temp.getIconSign());
					uf.setFilesize("-");
					ufs1.add(uf);
				}
			}
		}
		return ufs1;
	}

}
