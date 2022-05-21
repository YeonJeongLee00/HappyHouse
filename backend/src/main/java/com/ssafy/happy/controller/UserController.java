package com.ssafy.happy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happy.dto.UserDto;
import com.ssafy.happy.service.JwtServiceImpl;
import com.ssafy.happy.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

// Swagger 
@Api("User Controller")
// 메소드 전체가 REST로 동작
@RestController
// [/user] Rest url는 다 여기로 넘어온다
@RequestMapping("/user")
public class UserController {
	
	//	성공, 실패로 결과를 전송을 위한 변수 선언 
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
//  Service 주입
	@Autowired
	private UserService userService;

// Jwt 주입
	@Autowired
	private JwtServiceImpl jwtService;
	
	@ApiOperation(value = "회원 가입")
	@PostMapping
	public ResponseEntity<Boolean> insertUser(@RequestBody UserDto user) throws Exception{
		System.out.println("inserUser ------------------- ");
		System.out.println(user);
		return new ResponseEntity<Boolean>(userService.insertUser(user), HttpStatus.OK);
	}
	
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login( HttpServletRequest request, 
			@RequestBody UserDto user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			System.out.println("login in");
			System.out.println(user);
			System.out.println(request.getAttribute("id"));
			UserDto loginUser = userService.login(user);
			if (loginUser != null) {
				String token = jwtService.create("id", loginUser.getId(), "access-token");// key, data, subject
//				logger.debug("로그인 토큰정보 : {}", token);
				System.out.println("로그인 토큰정보 : " + token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
//			logger.error("로그인 실패 : {}", e);
			System.out.println("로그인 실패 : " +  e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{id}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("id") @ApiParam(value = "인증할 회원의 아이디.", required = true) String id,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
//			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.userInfo(id);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
//				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
//			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	@GetMapping("/list")
	public ResponseEntity<List<UserDto>> getInfo(){
		List<UserDto> list = userService
	}

}
