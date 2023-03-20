package com.food.recifit.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.food.recifit.dao.CommentDAO;
import com.food.recifit.domain.Comment;


public class CommentService {
	
	@Autowired
	CommentDAO dao;

	//코멘트 저장
	public int insertComment(Comment comment) {
		int cnt = dao.insertComment(comment);
		return cnt;
		
	}

	//코멘트 리스트 불러오기
	public ArrayList<Comment> listComment() {
		ArrayList<Comment> list = dao.listComment();
		return list;
	}

	//코멘트 삭제
	public int deleteComment(int num) {
		int cnt = dao.deleteComment(num);
		return cnt;
	}

}
