package com.whitemagic2014.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.whitemagic2014.pojo.UserPlan;

public interface UserPlanDao {

	List<UserPlan> findPlans(@Param("uid") String uid, @Param("itemName") String itemName);
	
	int instertPlan(UserPlan plan);

	int updatePlan(UserPlan plan);

	int deletePlan(@Param("id") Integer id);

}
