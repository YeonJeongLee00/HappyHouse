package com.project.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.happyhouse.dto.BoardDto;
import com.project.happyhouse.service.BoardService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/board")
public class BoardController {
	// 수정본

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;

	// 모든 게시글 반환
	// Swagger
	@ApiOperation(value = "모든 게시글의 정보를 반환", response = List.class)
	@GetMapping
	public ResponseEntity<List<BoardDto>> listBoard() throws Exception {
		logger.debug("listBoard 호출");
		return new ResponseEntity<List<BoardDto>>(boardService.listBoard(), HttpStatus.OK);
	}

	// 글 번호에 해당하는 게시글 반환
	@ApiOperation(value = "글 번호에 해당하는 게시글 반환", response = BoardDto.class)
	@GetMapping("{articleno}")
	public ResponseEntity<BoardDto> selectBoard(@PathVariable int articleno) throws Exception {
		logger.debug("selectBoard 호출");
		return new ResponseEntity<BoardDto>(boardService.selectBoard(articleno), HttpStatus.OK);
	}

	// 게시 글 작성
	@ApiOperation(value = "게시 글 작성, 작성을 성공하면 'success'를  실패하면 'fail'을 반환", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertBoard(@RequestBody BoardDto boardDto) throws Exception {
		logger.debug("insertBoard 호출");
		if (boardService.insertBoard(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	// 게시 글 수정
	@ApiOperation(value = "게시 글 수정, 수정 성공하면 'success' 를 실패하면 'fail'을 반환", response = String.class)
	@PutMapping("{articleno}")
	public ResponseEntity<String> updateBoard(@RequestBody BoardDto boardDto) throws Exception{
		logger.debug("updateBoard 호출, board : " + boardDto);
		if(boardService.updateBoard(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	// 게시 글 삭제
	@ApiOperation(value = "게시 글 삭제, 삭제 성공하면 'success' 를 실패하면 'fail'을 반환", response = String.class)
	@DeleteMapping("{articleno}")
	public ResponseEntity<String> deleteBoard(@PathVariable int articleno) throws Exception{
		logger.debug("deleteBoard 호출, articleno : " + articleno);
		if(boardService.deleteBoard(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}

}
