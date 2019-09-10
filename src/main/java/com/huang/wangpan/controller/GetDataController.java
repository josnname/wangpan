package com.huang.wangpan.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huang.wangpan.dto.UserData;
import com.huang.wangpan.dto.Fileim;
import com.huang.wangpan.mapper.UserFileMapper;
import com.huang.wangpan.model.UserFile;
import com.huang.wangpan.service.GetDataService;
import com.huang.wangpan.utils.PathAnalysis;
import com.huang.wangpan.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class GetDataController {
    @Autowired
    UserFileMapper userFileMapper;
    @Autowired
    GetDataService getDataService;
    @Autowired
    UserData userData;
    @RequestMapping("/getFile*")
    public void index(HttpServletRequest request,
                      HttpServletResponse response){
        String method = request.getParameter("method");
        System.out.println(method);
        if("getSuperior".equals(method)){
//            获得上一级的文件
            System.out.println("上一级");
            ResultData rd = new ResultData();
            String currentpath = request.getParameter("currentpath");
            String Catalogue = request.getParameter("Catalogue");
            String newpath="/";
            String []paths = currentpath.split("/");
            for(int i=0 ;i<paths.length-1;i++) {
                if(paths[i]!=null&&paths[i].trim().length()!=0)
                    newpath+=paths[i]+"/";
            }
            int page = Integer.parseInt(Catalogue);
            if(page>0) {
                PathAnalysis pa= new PathAnalysis();
                List<UserFile> ufs=userFileMapper.searchByUid(userData.getId());
                List<Fileim> fms=pa.SuperiorCatalogue(ufs, newpath, page);
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

                System.out.println(newpath);
                JSONObject msg = new JSONObject();
                msg.put("Catalogue", page-1);
                msg.put("currentpath", newpath);
//			rd.setData(data);
                message.put("code", 0);
                message.put("msg", msg);
                message.put("data",data);
                rd.setData(message);
                rd.writeToResponse(response);
                System.out.println("上级目录更新完毕！");
            }
        }else if("getNewFloder".equals(method)){
//            创建新的文件夹
            System.out.println("创建新的文件夹");
            String name =request.getParameter("name");
            String newpath = request.getParameter("currentpath");
            String Catalogue = request.getParameter("Catalogue");
            String isfloder = request.getParameter("file");
            int page = Integer.parseInt(Catalogue);
            ResultData rd=getDataService.CreateNewFile(newpath, name, page,isfloder);
            rd.writeToResponse(response);

        }else if("index".equals(method)){
//            获取首页面数据
            ResultData rd = new ResultData();
            PathAnalysis pa= new PathAnalysis();
            List<UserFile> ufs=userFileMapper.searchByUid(userData.getId());
            System.out.println("userData.getId()"+userData.getId());
            List<Fileim> ufs1=pa.getIndexPath(ufs);
            JSONObject message = new JSONObject();
            JSONArray data =new JSONArray();
            for(int i=0;i<ufs1.size();i++) {
                Fileim temp = ufs1.get(i);
                JSONObject tempj = new JSONObject();
                tempj.put("filesize", temp.getFilesize());
                tempj.put("filename", temp.getFilename());
                tempj.put("date", temp.getMtime());
                tempj.put("iconsign", temp.getIconsign());
                data.add(tempj);
            }
            JSONObject msg = new JSONObject();
            msg.put("Catalogue", 0);
            msg.put("currentpath", "/");
            message.put("code", 0);
            message.put("msg", msg);
            message.put("data",data);
            rd.setData(message);
            rd.writeToResponse(response);
            System.out.println("ok");
        }else if("rename".equals(method)){
            String currentpath = request.getParameter("currentpath");
            ResultData rd = new ResultData();
            String newname = request.getParameter("newname");
            String filename = request.getParameter("filename");
            System.out.println("rename的newname---->"+newname);
            if(!getDataService.isSameName(currentpath, newname)) {
                getDataService.rename(currentpath, newname,filename);
            }else {
                System.out.println("相同");
                JSONObject flag =new JSONObject();
                flag.put("flag", false);
                rd.setData(flag);
                rd.writeToResponse(response);
            }
        }else if("deleteFile".equals(method)){
            String filename = request.getParameter("filename");
            System.out.println("deleteFilename---->"+filename);
            String currentpath = request.getParameter("currentpath");
            getDataService.deleteFile(currentpath+filename);
        }else if("getSub".equals(method)){
            System.out.println("下一级");
            ResultData rd = new ResultData();
            String filename = request.getParameter("filename");
            String currentpath = request.getParameter("currentpath");
            String Catalogue = request.getParameter("Catalogue");
            int page = Integer.parseInt(Catalogue);
            String newpath="";
            System.out.println(currentpath);
            if(currentpath!=null) {
                newpath= currentpath+filename+"/";
            }else {
                newpath="/"+filename+"/";
            }
            PathAnalysis pa= new PathAnalysis();

            List<UserFile> ufs=userFileMapper.searchByUid(userData.getId());
            List<Fileim> fms=pa.getSubdirectories(ufs, newpath, page);
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
            msg.put("Catalogue", page+1);
            msg.put("currentpath", newpath);
            message.put("code", 0);
            message.put("msg",msg);
            message.put("data",data);
            rd.setData(message);
            rd.writeToResponse(response);
            System.out.println("更新完毕！");
        }

    }
}
