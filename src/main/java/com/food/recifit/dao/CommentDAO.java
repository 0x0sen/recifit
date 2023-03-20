package com.food.recifit.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.food.recifit.domain.Comment;

/**
 * 로그인에 관한 DAO
 * @author user
 *
 */
@Mapper
public interface CommentDAO {
	//코멘트 저장
	int insertComment(Comment comment);
	//코멘트 리스트 불러오기
	ArrayList<Comment> listComment();
	//코멘트 삭제
	int deleteComment(int num);	
}
