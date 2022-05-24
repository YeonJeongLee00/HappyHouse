package com.ssafy.vue.service;

import java.math.BigInteger;
import java.util.List;

import com.ssafy.vue.dto.HouseDealDto;
import com.ssafy.vue.dto.SelectHouseDto;

public interface HouseService {
	
	List<SelectHouseDto> selectHouse(BigInteger aptCode) throws Exception;
	List<HouseDealDto> listHouse(BigInteger aptCode) throws Exception;
	List<HouseDealDto> OneYearlistHouse(BigInteger aptCode) throws Exception;
	List<HouseDealDto> YearlistHouse(BigInteger aptCode) throws Exception;
	
	

}
