package com.ssafy.happy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happy.dto.BoardDto;

@Mapper
public interface BoardMapper {
	public List<BoardDto> listBoard();
}
