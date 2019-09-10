package com.huang.wangpan.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huang.wangpan.dto.UserData;
import com.huang.wangpan.dto.Fileim;
import com.huang.wangpan.mapper.UserFileMapper;
import com.huang.wangpan.model.UserFile;

import com.huang.wangpan.utils.PathAnalysis;
import com.huang.wangpan.utils.ResultData;
import com.huang.wangpan.utils.VoToBean;
import com.huang.wangpan.utils.GetIcon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Component
public class GetDataService {
    @Autowired
    HttpServletRequest request;
    @Autowired
    UserFileMapper userFileMapper;
    @Autowired
    PathAnalysis pa;
    @Autowired
    VoToBean voToBean;
    @Autowired
    ResultData rd;
    @Autowired
    UserData userData;
    @Autowired
    GetIcon getIcon;
//    private String id = userData.getId();
    public void deleteFile(String path) {
        List<UserFile> ufs=userFileMapper.searchByUid(userData.getId());

        for(int i=0 ;i<ufs.size();i++) {
            UserFile temp = ufs.get(i);
            String paths = temp.getVirtualpath();
            if(paths.startsWith(path)) {
                userFileMapper.deleteFile(0, paths, userData.getId());
            }
        }
    }

    public Boolean isSameName(String path,String filename) {
        List<UserFile> ufs=userFileMapper.searchByUid(userData.getId());
        for(int i=0 ;i<ufs.size();i++) {
            UserFile temp = ufs.get(i);
            String paths = temp.getVirtualpath();
            if(paths.startsWith(path+filename)) {
                String temps =paths.substring(path.length(),paths.length());
                int index = temps.indexOf('/');
                String test="";
                if(index!=-1) {
                    test = temps.substring(0, index);
                }else {
                    test = temps.substring(0, temps.length());
                }
                System.out.println("test="+test);
                if(test.equals(filename)) {

                    System.out.println("filename="+filename);
                    return true;
                }
            }
        }
        return false;
    }

    public void rename(String path,String newname,String filename) {
        List<UserFile> ufs=userFileMapper.searchByUid(userData.getId());
        for(int i=0 ;i<ufs.size();i++) {
            UserFile temp = ufs.get(i);
            String paths = temp.getVirtualpath();
            if(paths.startsWith(path+filename)) {
                String temps =paths.substring(path.length(),paths.length());
                int index = temps.indexOf('/');
                String end="";
                if(index!=-1) {
                    end = temps.substring(index,temps.length());
                }
                String newpath =path+newname+end;
                System.out.println("newpath---->"+newpath);
                userFileMapper.updateFilename(newpath, paths,userData.getId());
            }
        }
    }

    public ResultData CreateNewFile(String newpath,String name,int page,String isfloder) {
        Boolean flag=true;
        List<UserFile> ufs=userFileMapper.searchByUid(userData.getId());
        List<Fileim> fms=pa.getNewFloder(ufs, newpath, page);
        Fileim newfileim = new Fileim();
        newfileim.setFilename(name);
        newfileim.setFilesize("-");
        if("false".equals(isfloder)) {
            newfileim.setIconsign(getIcon.Icons(name));
        }else {
            newfileim.setIconsign("#icon-folder");
        }
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = date.format(new Date());
        newfileim.setMtime(format);
        if(!isSameName(newpath, name)) {
            fms.add(newfileim);
            flag=false;
        }
        insetUserFileToSql(voToBean.fileimToUserFile(newfileim, userData.getId(), newpath));
        JSONObject message = new JSONObject();
        JSONArray data =new JSONArray();
        for(int i=0;i<fms.size();i++) {
            Fileim temp = fms.get(i);
            JSONObject tempj = new JSONObject();
            tempj.put("filesize", temp.getFilesize());
            tempj.put("filename", temp.getFilename());
            tempj.put("date", temp.getMtime());
            tempj.put("iconsign", temp.getIconsign());
            data.add(tempj);
        }
        JSONObject msg = new JSONObject();
        msg.put("Catalogue", page);
        msg.put("currentpath", newpath);
        msg.put("flag",flag);
        message.put("code", 0);
        message.put("msg", msg);
        message.put("data",data);
        rd.setData(message);
        return rd;
    }
    public void insetUserFileToSql(UserFile uf) {
        userFileMapper.insert(uf);
    }
}
