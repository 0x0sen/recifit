package com.food.recifit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 전체 레시피 보기에 관한 컨트롤러 입니다.
 */
@Slf4j
@RequestMapping("AllRecipeController")
@Controller
public class AllRecipeController {

	@GetMapping({"/", ""})
	public String home() {
		return "home";
	}
	

}
