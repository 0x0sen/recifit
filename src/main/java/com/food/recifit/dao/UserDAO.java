package com.food.recifit.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.food.recifit.domain.Refrigerator;
import com.food.recifit.domain.User;



/**
 * 로그인에 관한 DAO
 * @author user
 *
 */
@Mapper
public interface UserDAO {
	//회원정보 저장
	int insertUser_id(User member);
	//회원정보 조회
	User selectOneUser(String user_id);
	//회원정보 수정
	int updateMember(User member);
	//회원 탈퇴
	int delete(String id);
	//회원 멤버 보기
	ArrayList<User> selectAll();
	//냉장고 채우기
	int inputrefrigerator(Refrigerator need);
	//냉장고 비우기
	int deleterefrigerator(Refrigerator need);
}
