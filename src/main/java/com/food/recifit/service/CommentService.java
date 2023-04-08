package com.food.recifit.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.food.recifit.domain.Comment;

@Service
public interface CommentService {
	
	//댓글 목록
	public ArrayList<Comment> commentlist(int num);

	
	//코멘트 삭제
	public int deleteComment(Comment comment);

	//댓글 저장
	public int writeComment(Comment comment);

}
