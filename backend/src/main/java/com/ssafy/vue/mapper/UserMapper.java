package com.ssafy.vue.mapper;

import java.sql.SQLException;

import com.ssafy.vue.dto.UserDto;


public interface UserMapper  {

//	public MemberDto login(MemberDto memberDto) throws SQLException;
//	public MemberDto userInfo(String userid) throws SQLException;
	
	public int insertUser(UserDto user) throws SQLException;
	public UserDto login(UserDto user) throws SQLException;
	public UserDto userInfo(String id) throws SQLException;
	
}