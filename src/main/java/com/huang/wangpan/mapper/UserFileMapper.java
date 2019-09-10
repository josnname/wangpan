package com.huang.wangpan.mapper;

import com.huang.wangpan.model.UserFile;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserFileMapper {
    @Insert("insert into userfile(uid,virtualpath,realpath,iconSign,filesize,mtime,survival) values(#{uid}, #{virtualpath}, #{realpath},#{iconSign}, #{filesize}, now(),1)")
    void insert(UserFile uf);

    @Select("select * from userfile where uid =#{Uid}")
    List<UserFile> searchByUid(@Param("Uid") String Uid);

    @Select("select * from userfile where uid =#{uid}")
    List<UserFile> SearcAll(@Param("uid") String uid);

    @Update("update userfile set survival=#{survival} where virtualpath=#{virtualpath} and uid=#{uid}")
    void deleteFile(@Param("survival") int survival, @Param("virtualpath") String virtualpath, @Param("uid") String uid);

    @Update("update userfile set virtualpath=#{newvirtualpath} where virtualpath=#{virtualpath} and uid=#{uid}")
    void updateFilename(@Param("newvirtualpath") String newvirtualpath, @Param("virtualpath") String virtualpath, @Param("uid") String uid);
}
