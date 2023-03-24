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


	//글쓰기 폼
	@GetMapping("/recipe/write")
	public String write() {		
		return "RecipeView/writeRecipe";
	}
}
