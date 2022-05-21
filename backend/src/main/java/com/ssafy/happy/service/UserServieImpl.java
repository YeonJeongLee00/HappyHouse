package com.ssafy.happy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happy.dto.UserDto;
import com.ssafy.happy.mapper.UserMapper;

@Service
public class UserServieImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean insertUser(UserDto user) throws Exception{	
		return userMapper.insertUser(user) == 1;
	}

	@Override
	public UserDto login(UserDto UserDto) throws Exception {
//		if(UserDto.getId() == null || UserDto.getPassword() == null)
//			return null;
		return userMapper.login(UserDto);
	}

	@Override
	public UserDto userInfo(String userid) throws Exception {
		return userMapper.userInfo(userid);
	}

	@Override
	public List<UserDto> getInfo() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
