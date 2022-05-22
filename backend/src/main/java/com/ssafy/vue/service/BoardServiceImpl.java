package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.dto.BoardDto;
import com.ssafy.vue.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardDto> listBoard() {
		return boardMapper.listBoard();
	}

	@Override
	public BoardDto selectBoard(int articleno) {
		return boardMapper.selectBoard(articleno);
	}

	@Override
	public boolean insertBoard(BoardDto boardDto) {
		return boardMapper.insertBoard(boardDto) == 1;
	}

	@Override
	@Transactional
	public boolean updateBoard(BoardDto boardDto) {
		return boardMapper.updateBoard(boardDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int articleno) {
		return boardMapper.deleteBoard(articleno) == 1;
	}

	@Override
	public String selectTag(int no) {
		return boardMapper.selectTag(no);
	}

	@Override
	public boolean updateView(int no) {
		return boardMapper.updateView(no) == 1;
	}
	
}