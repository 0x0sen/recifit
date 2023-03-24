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
	
	//리플 한개 조회 
	public Comment selectComment(int comment_num);
	//코멘트 삭제 
	public int deleteComment(Comment comment);
	
	
	//댓글 목록
	public ArrayList<Comment> commentlist(int num);

	//댓글 저장
	public int writeComment(Comment comment);
}
