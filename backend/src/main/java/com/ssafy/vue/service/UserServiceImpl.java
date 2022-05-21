package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.UserDto;
import com.ssafy.vue.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

//	@Autowired
//	private MemberMapper memberMapper;
//	
//	@Override
//	public MemberDto login(MemberDto memberDto) throws Exception {
//		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null)
//			return null;
//		return memberMapper.login(memberDto);
//	}
//
//	@Override
//	public MemberDto userInfo(String userid) throws Exception {
//		return memberMapper.userInfo(userid);
//	}
	
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
