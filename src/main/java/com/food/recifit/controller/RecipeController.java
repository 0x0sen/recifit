package com.food.recifit.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

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
						recipe.setRecipe_originalfile(upload.getOriginalFilename());
						recipe.setRecipe_savedfile(filename);
					}
					
					//로그인한 아이디 읽어서 board객체에 추가 
					recipe.setUser_id(user.getUsername());
					log.debug("저장할 글 정보 : ", recipe);
					
					//DB에 저장
					service.write(recipe);
					return "redirect:/recipe/list";
						
		}
		
		//글 삭제
		@GetMapping("delete")
		public String delete(
				@RequestParam(name="num", defaultValue="0") int num
				, @AuthenticationPrincipal UserDetails user) {
//			글 읽기 화면에서 글번호가 전달됨
//			로그인한 사용자의 아이디를 읽음
			String id = user.getUsername();
//			글번호로 DB에서 글 내용을 읽음
			Recipe recipe = service.selectRecipe(num);
//			해당번호의 글이 있는지 확인. 없으면 글목록으로
			if (recipe == null) return "redirect:list";
//			로그인한 본인의 글이 맞는지 확인. 아니면 글목록으로
			if (!recipe.getUser_id().equals(id)) return "redirect:list";
//			첨부된 파일이 있으면 파일삭제
			if (recipe.getRecipe_savedfile() != null) {
				FileService.deleteFile(uploadPath + "/" + recipe.getRecipe_savedfile());
			}
//			실제 글 DB에서 삭제
			service.delete(recipe);
//			글 목록으로 리다이렉트
			return "redirect:list";
		}
		
		//수정 폼으로 이동
		@GetMapping("update")
		public String update(int recipe_num, Model model, @AuthenticationPrincipal UserDetails user) {
			//전달된 번호의 글정보 읽기
			Recipe recipe = service.selectRecipe(recipe_num);
			//본인글인지 확인. 아니면 글목록으로 이동.
			if (!recipe.getUser_id().equals(user.getUsername())) {
				return "redirect:list";
			}
			//글정보를 모델에 저장
			model.addAttribute("recipe", recipe);
			//수정폼 html로 포워딩	
			return "recipeView/update.html";
		}
		
		
		//수정폼에서 보낸 내용 처리
		@PostMapping("update")
		public String update(Recipe recipe, @AuthenticationPrincipal UserDetails user, MultipartFile upload) {
			log.debug("저장할 글정보 : {}", recipe);
			log.debug("파일 정보: {}", upload);
			
			//전달된 User객체(글번호, 제목, 내용) 에 로그인한 아이디 추가 저장
			recipe.setUser_id(user.getUsername());
			Recipe oldRecipe = null;
			String oldRecipe_savedfile = null;
			String savedfile = null;
			
			//첨부파일이 있는 경우 기존파일 삭제 후 새 파일 저장
			if (upload != null && !upload.isEmpty()) {
				oldRecipe = service.selectRecipe(recipe.getRecipe_num());
				oldRecipe_savedfile = oldRecipe == null ? null : oldRecipe.getRecipe_savedfile();
				
				savedfile = FileService.saveFile(upload, uploadPath);
				recipe.setRecipe_originalfile(upload.getOriginalFilename());
				recipe.setRecipe_savedfile(savedfile);
				log.debug("새파일:{}, 구파일:{}", savedfile, oldRecipe_savedfile);
			}
			
			int result = service.update(recipe);
			
			//글 수정 성공 and 첨부된 파일이 있는 경우 파일도 삭제
			if (result == 1 && savedfile != null) {
				FileService.deleteFile(uploadPath + "/" + oldRecipe_savedfile);
			}
			return "redirect:read?num=" + recipe.getRecipe_num();
		}

		//글 목록 + 검색기능추가 
		@GetMapping("list")
		public String list(
				
				String type
				, String searchWord
				, Model model) {
			
//			PageNavigator navi = 
//					service.getPageNavigator(pagePerGroup, countPerPage, page, type, searchWord);
			
			ArrayList<Recipe> recipeList = service.list(
					type, searchWord);
				
				model.addAttribute("recipeList", recipeList);
				//model.addAttribute("navi", navi);
				model.addAttribute("type", type);
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
			Recipe recipe = service.selectRecipe(num);
			//서비스가 리턴한 Board객체를 Model에 저장
			model.addAttribute("Recipe", recipe);
			log.debug("이거되남3");
			
			//해당 글에 달린 리플 목록 
			ArrayList<Comment> replylist = service.commentList(num);
			model.addAttribute("replylist", replylist);
			log.debug("{}글의 리플들 : {}", num, replylist);
			
			//HTML파일로 포워딩하여 출력
			return "boardView/readForm";

		}
		
		//리플 저장
		@PostMapping("writeComment")
		public String writeComment(Comment comment, @AuthenticationPrincipal UserDetails user) {
			//폼에서 전달된 본문글번호, 리플내용에 작성자 아이디 추가 저장
			comment.setUser_id(user.getUsername());
			//DB에 저장
			service.writeComment(comment);
			//읽던 글로 되돌아감	
			return "redirect:read?num=" + comment.getComment_num();
		}
		
		//리플 삭제
		@GetMapping("deleteComment")
		public String deleteComment(
				Comment comment
			, @AuthenticationPrincipal UserDetails user) {
			
			comment.setUser_id(user.getUsername());
			int result = service.deleteComment(comment);
			
			return "redirect:/board/read?num=" + comment.getComment_num();
		}
		


	

}
