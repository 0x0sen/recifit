package com.food.recifit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 메인화면으로 이동
 */
@Slf4j
@Controller
public class HomeController {

	@GetMapping({"/", ""})
	public String home() {
		return "home";
	}
	/**
	 * 로그인 폼으로 이동
	 * @return
	 */
	@GetMapping("/user/loginForm")
	public String loginForm() {
		return "UserView/loginForm";
	}
	/**
	 * 회원 가입 폼으로 이동
	 * @return 회원가입 양식 HTMl
	 */
	
	@GetMapping("/user/join")
	public String join() {
		return "UserView/joinForm";
	}
	
	@GetMapping("/recipe/list")
	public String list() {
		return "RecipeView/list";
	}
    
    @GetMapping("/recipe/read")
	public String read() {
		return "RecipeView/read";
	}

}
