package com.whitemagic2014.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.whitemagic2014.pojo.CanEat;

public interface CanEatDao {

	int insert(CanEat ce);

	List<CanEat> findByName(@Param("itemName") String itemName);
	
}
