package com.food.recifit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.food.recifit.domain.Recipe;
import com.food.recifit.service.RecipeService;
import com.food.recifit.util.FileService;

import lombok.extern.slf4j.Slf4j;

/**
 * 전체 레시피 보기에 관한 컨트롤러 입니다.
 */
@Slf4j
@RequestMapping("recipe")
@Controller
public class RecipeController {
	
	@Autowired
	RecipeService service;

	//설정파일에 정의된 업로드할 경로를 읽어서 아래 변수에 대입
			@Value("${spring.servlet.multipart.location}")
			String uploadPath;

		
		@GetMapping("write")
		public String write() {
		
			return "RecipeView/Recipe";
		}
		
		@PostMapping("write")
		public String write(Recipe recipe
				, @AuthenticationPrincipal UserDetails user
				, MultipartFile upload) {
			
			//첨부파일이 있으면 지정한 경로에 저장하고 파일명을 board객체에 추가
					if ( upload != null && !upload.isEmpty()) {
						String filename = FileService.saveFile(upload, uploadPath);
						recipe.setOriginalfile(upload.getOriginalFilename());
						recipe.setSavedfile(filename);
					}
					
					//로그인한 아이디 읽어서 board객체에 추가 
					recipe.setRecipe_nick(user.getUsername());
					log.debug("저장할 글 정보 : ", recipe);
					
					//DB에 저장
					service.write(recipe);
					return "redirect:/recipe/list";
			
			
		}
		
		@GetMapping("list")
		public String list() {
			
			return "RecipeView/list";
		}
	

}
