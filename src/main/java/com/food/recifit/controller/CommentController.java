package com.food.recifit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.food.recifit.domain.Comment;
import com.food.recifit.service.CommentService;

import lombok.extern.slf4j.Slf4j;


/**
 * 코멘트에 관한 컨트롤러 입니다. 
 */
@Slf4j
@RequestMapping("recipe")
@Controller
public class CommentController {

	
		@Autowired
		CommentService service;
		
		//댓글 저장
		@PostMapping("writeComment")
		public String writeComment(Comment comment, @AuthenticationPrincipal UserDetails user) {
			//폼에서 전달된 본문글번호, 리플내용에 작성자 아이디 추가 저장
			comment.setUser_id(user.getUsername());
			//DB에 저장
			service.writeComment(comment);
			//읽던 글로 되돌아감	
			return "redirect:read?num=" + comment.getRecipe_num();
		}
	
			//댓글 삭제
			@GetMapping("deleteComment")
			public String deleteComment(
				Comment comment
				, @AuthenticationPrincipal UserDetails user) {
				
				comment.setUser_id(user.getUsername());
				int result = service.deleteComment(comment);
				
				return "redirect:/recipe/read?num=" + comment.getRecipe_num();
			}
			
			
			
			

}

	