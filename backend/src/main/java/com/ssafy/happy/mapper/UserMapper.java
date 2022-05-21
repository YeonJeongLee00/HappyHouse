package com.ssafy.happy.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happy.dto.UserDto;

@Mapper
public interface UserMapper {
	public int insertUser(UserDto user) throws SQLException;
	public UserDto login(UserDto user) throws SQLException;
	public UserDto userInfo(String id) throws SQLException;
}
