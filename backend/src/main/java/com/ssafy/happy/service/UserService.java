package com.ssafy.happy.service;

import java.util.List;

import com.ssafy.happy.dto.UserDto;

public interface UserService {
	public boolean insertUser(UserDto user) throws Exception;
	public UserDto login(UserDto memberDto) throws Exception;
	public UserDto userInfo(String userid) throws Exception;
	public List<UserDto> getInfo() throws Exception;
}
