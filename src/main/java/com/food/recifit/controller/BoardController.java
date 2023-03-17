package com.food.recifit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 글쓰기에 관한 dao 입니다.
 */
@Slf4j
@RequestMapping("board")
@Controller
public class BoardController {

	
	@GetMapping({"/", ""})
	public String home() {
	//틀입니다. 다시 작성해주세요
		return "home";
	}
	

}

	