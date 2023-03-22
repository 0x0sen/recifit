package com.food.recifit.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.food.recifit.domain.User;


/**
 * 로그인에 관한 DAO
 * @author user
 *
 */
@Mapper
public interface UserDAO {
	  //회원정보 저장
    public int insertMember(User member);
	//글 저장
	
}