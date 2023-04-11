package com.food.recifit.controller;

import java.io.FileInputStream;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.food.recifit.domain.Recipe;
import com.food.recifit.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

/**
 * 메인화면으로 이동
 */
@Slf4j
@Controller
public class HomeController {
	
	@Autowired
	RecipeService service;

	@GetMapping({"/", ""})
	public String home(Model model) {
		
		ArrayList<Recipe> recipeList = service.Homelist();
		
			
		model.addAttribute("recipeList", recipeList);
			
		return "home";
	}
	/**
	 * 로그인 폼으로 이동
	 * @return
	 */

}
