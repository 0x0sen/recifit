package com.food.recifit.controller;



import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.food.recifit.domain.Comment;
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
			
			//페이지당 글 수
			@Value("${user.board.page}")
			int countPerPage;
			
			//페이지 이동 링크 수
			@Value("${user.board.group}")
			int pagePerGroup;

		//글쓰기 폼
		@GetMapping("/write")
		public String write() {		
			return "RecipeView/writeRecipe";
		}

		//글저장
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

		//글 목록 + 검색기능추가 
		@GetMapping("list")
		public String list(String searchWord
				, Model model) {
			
//			PageNavigator navi = 
//					service.getPageNavigator(pagePerGroup, countPerPage, page, type, searchWord);
			
			ArrayList<Recipe> recipeList = service.list(searchWord);
				
				model.addAttribute("recipeList", recipeList);
				//model.addAttribute("navi", navi);
				
				model.addAttribute("searchWord", searchWord);
			
			return "RecipeView/list";
		}
		
		//글 클릭해서 읽기, 조회수 증가

		//@GetMapping("/read")
		//int num만 쓰기는 위험하다. 
		//요청 파라미터를 넣어서 넣어달라.
//		public String read(
//				@RequestParam(name = "num", defaultValue="0") int num
//				, Model model) {
//			//본문글 정보
//			log.debug("read: ",num);
//			if(num == 0) {
//				return "redirect:list";
//			}
//			//num이라는 이름의 글번호를 전달받음
//			//전달받은 글번호를 서비스로 전달
//			Recipe recipe = service.selectrecipe(num);
//			//서비스가 리턴한 Board객체를 Model에 저장
//			model.addAttribute("Recipe", recipe);
//			log.debug("이거되남3");
//			
//			//해당 글에 달린 리플 목록 
//			ArrayList<Comment> replylist = service.listcomment(num);
//			model.addAttribute("replylist", replylist);
//			log.debug("{}글의 리플들 : {}", num, replylist);
//			
//			//HTML파일로 포워딩하여 출력
//			return "boardView/readForm";
//
//		}

		@GetMapping("/read")
		//int num만 쓰기는 위험하다. 
		//요청 파라미터를 넣어서 넣어달라.
		public String read(
				@RequestParam(name = "num", defaultValue="0") int num
				, Model model) {
			//본문글 정보
			log.debug("read: ",num);
			if(num == 0) {
				return "redirect:list";
			}
			//num이라는 이름의 글번호를 전달받음
			//전달받은 글번호를 서비스로 전달
			Recipe recipe = service.selectrecipe(num);
			//서비스가 리턴한 Board객체를 Model에 저장
			model.addAttribute("Recipe", recipe);
			log.debug("이거되남3");
			
			//해당 글에 달린 리플 목록 
			ArrayList<Comment> replylist = service.listcomment(num);
			model.addAttribute("replylist", replylist);
			log.debug("{}글의 리플들 : {}", num, replylist);
			
			//HTML파일로 포워딩하여 출력
			return "boardView/readForm";

		}

	

}
