package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.UserDto;

public interface UserService {
	public boolean insertUser(UserDto user) throws Exception;
	public boolean updateUser(UserDto user) throws Exception;
	public boolean deleteUser(String id) throws Exception;
	public boolean isUsedId(String id) throws Exception;
	public UserDto login(UserDto memberDto) throws Exception;
	public UserDto userInfo(String userid) throws Exception;
	public List<UserDto> getUserList() throws Exception;
}
