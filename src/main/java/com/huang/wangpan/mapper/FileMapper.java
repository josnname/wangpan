package com.huang.wangpan.mapper;

import com.huang.wangpan.model.Upfile;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("select * from filerepository where md5=#{md5}")
    public Upfile searchFileBymd5(@Param("md5") String md5);

    @Select("select * from filerepository")
    public List<Upfile> searchAll();

    @Insert("insert into filerepository(md5,realpath,filesize) values(#{md5}, #{realpath}, #{filesize})")
    @Options(useGeneratedKeys = true, keyProperty = "id")//设置id为自增
    public void insert(Upfile file);
}
