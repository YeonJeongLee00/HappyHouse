package com.ssafy.happy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happy.dto.BoardDto;
import com.ssafy.happy.service.BoardService;


@RestController
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping
	public ResponseEntity<List<BoardDto>> listBoard() throws Exception {
		return new ResponseEntity<List<BoardDto>>(boardService.listBoard(), HttpStatus.OK);
	}
}
