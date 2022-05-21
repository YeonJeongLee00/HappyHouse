package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.UserDto;

public interface UserService {

//	public MemberDto login(MemberDto memberDto) throws Exception;
//	public MemberDto userInfo(String userid) throws Exception;
	public boolean insertUser(UserDto user) throws Exception;
	public UserDto login(UserDto memberDto) throws Exception;
	public UserDto userInfo(String userid) throws Exception;
	public List<UserDto> getInfo() throws Exception;
}
