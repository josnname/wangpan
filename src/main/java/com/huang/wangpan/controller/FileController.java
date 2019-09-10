package com.huang.wangpan.controller;


import com.huang.wangpan.mapper.FileMapper;
import com.huang.wangpan.model.Upfile;
import com.huang.wangpan.utils.ByteUnitConversion;
import com.huang.wangpan.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class FileController  {
    @Autowired
    FileMapper fileMapper;
    @Autowired
    Upfile upfile;
    @Value("${file.warehouse}")
    private String fileWarehouse;
    @RequestMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file) {
        System.out.println("上传我来了");
            try {
                if (file.isEmpty()) {
                    System.out.println("文件为空");
                }
                // 获取文件名
                String fileName = file.getOriginalFilename();
                String size = new ByteUnitConversion().readableFileSize(file.getSize());
                upfile.setFilesize(size);
                upfile.setRealpath(fileWarehouse+fileName);
                fileMapper.insert(upfile);
                System.out.println("上传的文件名为：" + fileName);
                // 获取文件的后缀名
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                System.out.println("文件的后缀名为：" + suffixName);
                // 设置文件存储路径
                String filePath = fileWarehouse;
                String path = filePath + fileName;
                System.out.println(fileWarehouse);
                File dest = new File(path);
                // 检测是否存在目录
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();// 新建文件夹
                }
                file.transferTo(dest);// 文件写入
                System.out.println("上传成功");
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @RequestMapping("/getMD5")
    public void getMD5(HttpServletResponse response,HttpServletRequest request){
        String MD5 = request.getParameter("MD5");
        ResultData rd = new ResultData();
        System.out.println(MD5);
        Upfile file = null;
        file=fileMapper.searchFileBymd5(MD5);
        if(file ==null) {
            upfile.setMd5(MD5);
            rd.setData("yes");
            rd.writeToResponse(response);
        }else {
            rd.setData("no");
            rd.writeToResponse(response);
        }
    }
    @RequestMapping("/download*")
    public void download(HttpServletRequest request,HttpServletResponse response) {
        String path =fileWarehouse;
        String filename = request.getParameter("filename");
        String currentpath = request.getParameter("currentpath");
        System.out.println(filename);
        File file = new File(path+filename);
        doDownload(response,file);
    }

    public void doDownload(HttpServletResponse response,File file) {
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
