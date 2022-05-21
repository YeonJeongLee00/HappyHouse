package com.ssafy.happy.service;

import java.util.List;

import com.ssafy.happy.dto.BoardDto;

public interface BoardService {
	public List<BoardDto> listBoard();
	public BoardDto selectBoard(int articleno);
	public boolean insertBoard(BoardDto boardDto);
	public boolean updateBoard(BoardDto boardDto);
	public boolean deleteBoard(int articleno);
	
}
