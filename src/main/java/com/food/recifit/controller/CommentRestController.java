package com.food.recifit.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.food.recifit.domain.Comment;
import com.food.recifit.service.CommentService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("recipe")
@ResponseBody
public class CommentRestController {
			//rest가 들어가 있으면 ajax가 들어간 컨트롤러
			//@ResponseBody 를 한방에 붙여버린다.
			//서비스로 전달해서 DB에 저장
			/*
			 * 서비스에 필요한 기능들
			 *1. 이름과 글내용 전달받아 DB에 저장 
			 *2. 번호를 전달받아 DB에서 삭제
			 *3. DB의 모든 정보를  ArrayList<Comment>로 리턴
 			 */
			
		@Autowired
		CommentService service;
		
		//코멘트 저장
		@PostMapping("insertComment")
		public String insert(Comment comment, @AuthenticationPrincipal UserDetails user) {
			//폼에서 전달된 본문글번호, 리플내용에 작성자 아이디 추가 저장
			comment.setUser_id(user.getUsername());
			//서비스로 전달해서 DB에 저장
			log.info("전달된 객체 : {}", comment);
			service.insertComment(comment);
			//읽던 글로 되돌아감	
			return "redirect:read?num=" + comment.getComment_num();
		}
		
		//코멘트 리스트 불러오기
		@GetMapping("listComment")
		public ArrayList<Comment> list(){
			ArrayList<Comment> list = service.listComment();
			log.debug("결과:{}", list);
			return list;
		}
		
		//코멘트 삭제
		@GetMapping("deleteComment")
		public String deleteComment(
				Comment comment
			, @AuthenticationPrincipal UserDetails user) {
			
			comment.setUser_id(user.getUsername());
			int result = service.deleteComment(comment);
			
			return "redirect:/board/read?num=" + comment.getComment_num();
		}
		
		
}
