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
	public int insertComment(Comment comment);
	//코멘트 전체 보기
	public ArrayList<Comment> listComment();
	//리플 한개 조회 
	public Comment selectComment(int comment_num);
	//코멘트 삭제 
	public int deleteComment(Comment comment);
	//조회 수 1증가
	public int add(int recipe_num);
}
