package com.huang.wangpan.mapper;

import com.huang.wangpan.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
//    @Select("select * from user where uid =#{uid}")
//    public User searchByUid(@Param("uid") String uid);
//
//    @Select("select * from user")
//    public List<User> searchAll();

    @Insert("insert into user(token,name,accountid,gmtcreate,gmtmodified) values(#{token}, #{name}, #{accountid},now(),now())")
     void insert(User user);
    @Select("select * from user where token =#{token}")
    User searchByToken(@Param("token") String token);
//    @Select("select * from user where uid=#{uid} and password=#{password}")
//    public User login(@Param("uid") String uid, @Param("password") String password);

}
