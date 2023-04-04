package com.food.recifit.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.food.recifit.domain.Comment;
import com.food.recifit.domain.Recipe;
import com.food.recifit.domain.User;
import com.food.recifit.service.CommentService;
import com.food.recifit.service.RecipeService;
import com.food.recifit.util.FileService;

import lombok.extern.slf4j.Slf4j;


/**
 * 레시피 관련 컨트롤러 입니다.
 */

@Slf4j
@RequestMapping("recipe")
@Controller
public class RecipeController {

	@Autowired
	RecipeService service;

	@Autowired
	CommentService service2;

	//설정파일에 정의된 업로드할 경로를 읽어서 아래 변수에 대입
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;

	//페이지당 글 수
	@Value("${user.board.page}")
	int countPerPage;

	//페이지 이동 링크 수
	@Value("${user.board.group}")
	int pagePerGroup;
	

	//레시피 자세한 검색으로 추천받기
	@GetMapping("recifitCheck")
	public String recifitCheck() {

		return "RecipeView/recifitCheck";
	}
	
	@GetMapping("recifitCheck2")
	public String recifitCheck2(@RequestParam String searchWord,
            @RequestParam String recipe_type,
            @RequestParam String recipe_icon,
            Model model) {
		
		
	
	ArrayList<Recipe> recipeList = service.recifitCheck(searchWord, recipe_type, recipe_icon);
	
	model.addAttribute("recipeList", recipeList);
	
	log.debug("넘어간 값 : {}", searchWord);
	log.debug("넘어간 값 : {}", recipe_type);
	log.debug("넘어간 값 : {}", recipe_icon);
	
	//split해서 ,기준으로 자른 다음, 배열 list를 전달받는 mybatis의 동적 sql. mapper sql에서 and반복문
	
	
	//model.addAttribute("navi", navi);
	//model.addAttribute("searchWord", searchWord);
	
	return "RecipeView/list";
	
	}

	
	//글쓰기 폼
	@GetMapping("/write")
	public String write() {		
		return "/RecipeView/writeRecipe";
	}

	//글저장
	@PostMapping("/write")
	public String write(Recipe recipe
			, @AuthenticationPrincipal UserDetails user
			, MultipartFile upload) {

		//첨부파일이 있으면 지정한 경로에 저장하고 파일명을 recipe객체에 추가
		if ( upload != null && !upload.isEmpty()) {
			String filename = FileService.saveFile(upload, uploadPath);
			recipe.setRecipe_originalfile(upload.getOriginalFilename());
			recipe.setRecipe_savedfile(filename);
		}

		//로그인한 아이디 읽어서 recipe 객체에 추가 
		recipe.setUser_id(user.getUsername());
		log.debug("저장할 글 정보 : ", recipe);

		//DB에 저장
		service.insertrecipe(recipe);
		return "redirect:/recipe/list";

	}

	//글 삭제
	@GetMapping("delete")
	public String deleterecipe(
			@RequestParam(name="num", defaultValue="0") int num
			, @AuthenticationPrincipal UserDetails user) {
		//			글 읽기 화면에서 글번호가 전달됨
		log.debug("삭제할 글 번호 : {}", num);
		//			로그인한 사용자의 아이디를 읽음
		String id = user.getUsername();
		//			글번호로 DB에서 글 내용을 읽음
		Recipe recipe = service.selectrecipe(num);
		//			해당번호의 글이 있는지 확인. 없으면 글목록으로
		if (recipe == null) return "redirect:list";
		//			로그인한 본인의 글이 맞는지 확인. 아니면 글목록으로
		if (!recipe.getUser_id().equals(id)) return "redirect:list";
		//			실제 글 DB에서 삭제
		service.deleterecipe(recipe);
		//			글 목록으로 리다이렉트
		return "redirect:list";
	}

	//수정 폼으로 이동
	@GetMapping("/update")
	public String update(int recipe_num, Model model, @AuthenticationPrincipal UserDetails user) {
		//전달된 번호의 글정보 읽기
		Recipe recipe = service.selectrecipe(recipe_num);
		//본인글인지 확인. 아니면 글목록으로 이동.
		if (!recipe.getUser_id().equals(user.getUsername())) {
			return "redirect:list";
		}
		//글정보를 모델에 저장
		model.addAttribute("recipe", recipe);
		//수정폼 html로 포워딩	
		return "RecipeView/updateRecipe";
	}


	//수정폼에서 보낸 내용 처리
	@PostMapping("/update")
	public String update(Recipe recipe, @AuthenticationPrincipal UserDetails user, MultipartFile upload) {
		log.debug("저장할 글정보 : {}", recipe);
		log.debug("파일 정보: {}", upload);

		//전달된 Recipe객체(글번호, 제목, 내용) 에 로그인한 아이디 추가 저장
		recipe.setUser_id(user.getUsername());
		Recipe oldRecipe = null;
		String oldRecipe_savedfile = null;
		String savedfile = null;

		//첨부파일이 있는 경우 기존파일 삭제 후 새 파일 저장
		if (upload != null && !upload.isEmpty()) {
			oldRecipe = service.selectrecipe(recipe.getRecipe_num());
			oldRecipe_savedfile = oldRecipe == null ? null : oldRecipe.getRecipe_savedfile();

			savedfile = FileService.saveFile(upload, uploadPath);
			recipe.setRecipe_originalfile(upload.getOriginalFilename());
			recipe.setRecipe_savedfile(savedfile);
			log.debug("새파일:{}, 구파일:{}", savedfile, oldRecipe_savedfile);
		}

		int result = service.updaterecipe(recipe);

		//글 수정 성공 and 첨부된 파일이 있는 경우 파일도 삭제
		if (result == 1 && savedfile != null) {
			FileService.deleteFile(uploadPath + "/" + oldRecipe_savedfile);
		}

		return "redirect:read?num=" + recipe.getRecipe_num();
	}


	//글 목록 + 검색기능추가 
	@GetMapping("/list")
	public String list(
			String searchWord
			, Model model) {

		//			PageNavigator navi = 
		//					service.getPageNavigator(pagePerGroup, countPerPage, page, type, searchWord);

		ArrayList<Recipe> recipeList = service.list(searchWord);

		log.debug("넘어간 값 : {}", searchWord);

		model.addAttribute("recipeList", recipeList);
		//model.addAttribute("navi", navi);
		model.addAttribute("searchWord", searchWord);

		return "RecipeView/list";
	}


	//글 클릭해서 읽기, 조회수 증가

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
		//서비스가 리턴한 Recipe객체를 Model에 저장
		model.addAttribute("recipe", recipe);

		//해당 글에 달린 리플 목록 
		ArrayList<Comment> commentlist = service2.commentlist(num);
		model.addAttribute("commentlist", commentlist);
		log.debug("{}글의 리플들 : {}", num, commentlist);

		//HTML파일로 포워딩하여 출력
		return "RecipeView/readRecipe";

	}
	//첨부파일 다운로드
	//Stream
	//오늘 얘를 배웠다. HttpServletResponse response
	@GetMapping("/download")
	public String download(
			@RequestParam(name = "num", defaultValue="0") int num
			,HttpServletResponse response) {

		//num이라는 이름의 글번호를 전달받음
		//전달받은 글번호를 서비스로 전달
		Recipe recipe = service.selectrecipe(num);
		if(recipe == null || recipe.getRecipe_savedfile() == null) {
			return "redirect:list";
		}

		String file = uploadPath + "/" + recipe.getRecipe_savedfile();

		//FileInputStream
		FileInputStream in = null;
		ServletOutputStream out = null;

		try {
			//응답 정보의 헤더 세팅
			response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(recipe.getRecipe_originalfile(), "UTF-8"));

			in = new FileInputStream(file);
			out = response.getOutputStream();

			//파일 전송(하나씩 반복해서 받아서 출력해라..읽고 쓰고 반복)
			FileCopyUtils.copy(in, out);

			in.close();
			out.close();
		} 
		catch (IOException e) {
			//예외메시지 출력
		}
		return "redirect:/";
	}
	
	
	
	
	
	//음식 종류별 목록
	@GetMapping("Korean")
	public String Korean(String searchWord, Model model) {
				
		//			PageNavigator navi = 
		//					service.getPageNavigator(pagePerGroup, countPerPage, page, type, searchWord);

		ArrayList<Recipe> recipeList = service.KoreanList(searchWord);

		log.debug("넘어간 값 : {}", searchWord);

		model.addAttribute("recipeList", recipeList);
		//model.addAttribute("navi", navi);
		model.addAttribute("searchWord", searchWord);

		return "RecipeView/list";
	}
	
	@GetMapping("Western")
	public String Western(String searchWord, Model model) {
				
		//			PageNavigator navi = 
		//					service.getPageNavigator(pagePerGroup, countPerPage, page, type, searchWord);

		ArrayList<Recipe> recipeList = service.WesternList(searchWord);

		log.debug("넘어간 값 : {}", searchWord);

		model.addAttribute("recipeList", recipeList);
		//model.addAttribute("navi", navi);
		model.addAttribute("searchWord", searchWord);

		return "RecipeView/list";
	}
	
	@GetMapping("Japanese")
	public String Japanese(String searchWord, Model model) {
				
		//			PageNavigator navi = 
		//					service.getPageNavigator(pagePerGroup, countPerPage, page, type, searchWord);

		ArrayList<Recipe> recipeList = service.JapaneseList(searchWord);

		log.debug("넘어간 값 : {}", searchWord);

		model.addAttribute("recipeList", recipeList);
		//model.addAttribute("navi", navi);
		model.addAttribute("searchWord", searchWord);

		return "RecipeView/list";
	}
	
	@GetMapping("Chinese")
	public String Chinese(String searchWord, Model model) {
				
		//			PageNavigator navi = 
		//					service.getPageNavigator(pagePerGroup, countPerPage, page, type, searchWord);

		ArrayList<Recipe> recipeList = service.ChineseList(searchWord);

		log.debug("넘어간 값 : {}", searchWord);

		model.addAttribute("recipeList", recipeList);
		//model.addAttribute("navi", navi);
		model.addAttribute("searchWord", searchWord);

		return "RecipeView/list";
	}
	
	@GetMapping("Asian")
	public String Asian(String searchWord, Model model) {
				
		//			PageNavigator navi = 
		//					service.getPageNavigator(pagePerGroup, countPerPage, page, type, searchWord);

		ArrayList<Recipe> recipeList = service.AsianList(searchWord);

		log.debug("넘어간 값 : {}", searchWord);

		model.addAttribute("recipeList", recipeList);
		//model.addAttribute("navi", navi);
		model.addAttribute("searchWord", searchWord);

		return "RecipeView/list";
	}
	
	@GetMapping("dessert")
	public String dessert(String searchWord, Model model) {
				
		//			PageNavigator navi = 
		//					service.getPageNavigator(pagePerGroup, countPerPage, page, type, searchWord);

		ArrayList<Recipe> recipeList = service.dessertList(searchWord);

		log.debug("넘어간 값 : {}", searchWord);

		model.addAttribute("recipeList", recipeList);
		//model.addAttribute("navi", navi);
		model.addAttribute("searchWord", searchWord);

		return "RecipeView/list";
	}
	
}
