package com.project.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.happyhouse.dto.BoardDto;
import com.project.happyhouse.mapper.BoardMapper;

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

}
