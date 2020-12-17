package com.guosen.serviceuser.mapper;

import com.guosen.serviceuser.domain.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT id, username, password FROM users WHERE username = #{username} LIMIT 0,1")
    User findByUsername(@Param("username") String username);

    @Select("SELECT id, username FROM users WHERE id = #{id}")
    User findById(@Param("id") Integer id);

    @Insert("INSERT INTO users (username, password) VALUES (#{username}, #{password})")
    void create(@Param("username") String username, @Param("password") String password);
}
