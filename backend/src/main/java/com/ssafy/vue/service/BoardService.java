package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.BoardDto;

public interface BoardService {
	public List<BoardDto> listBoard();
	public BoardDto selectBoard(int articleno);
	public boolean insertBoard(BoardDto boardDto);
	public boolean updateBoard(BoardDto boardDto);
	public boolean deleteBoard(int articleno);
	public String selectTag(int no);
	public boolean updateView(int no);
}
