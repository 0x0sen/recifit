package com.food.recifit.service;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.recifit.dao.CommentDAO;
import com.food.recifit.domain.Comment;
import com.food.recifit.domain.Zzim;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentDAO dao;

	
	
	//코멘트 삭제
	public int deleteComment(Comment comment) {
		int cnt = dao.deleteComment(comment);
		return cnt;
	}
	
	@Override
	public ArrayList<Comment> commentlist(int num) {
		ArrayList<Comment> commentlist = dao.commentlist(num);
		return commentlist;
	}

	//댓글 저장
	@Override
	public int writeComment(Comment comment) {
		int n = dao.writeComment(comment);
		return n;
	}

}
