package com.cloud.SuperDuperDrive.Mapper;

import com.cloud.SuperDuperDrive.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("Select * from users where username = #{username}")
    User getUser(String username);

    @Insert("Insert into users (username,salt, password, firstname, lastname) values(#{username}, #{salt}, #{password},#{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);
}
