package com.food.recifit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 코멘트에 관한 컨트롤러 입니다. 
 */
@Slf4j
@RequestMapping("recipe")
@Controller

public class CommentController {

	
	@GetMapping("comment")
	public String comment() {
	//틀입니다. 다시 작성해주세요
		return "recipe/comment";
	}
	

}

	