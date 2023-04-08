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

import com.food.recifit.domain.Zzim;
import com.food.recifit.service.ZzimServiceImpl;
import com.food.recifit.util.FileService;

import lombok.extern.slf4j.Slf4j;


/**
 * 로그인에 관한 컨트롤러 입니다. 
 * 회원가입 회원 탈퇴 등등
 */
@Slf4j
@RequestMapping("recipe")
@Controller
public class ZzimController {

	@Autowired
	ZzimServiceImpl service;

	//설정파일에 정의된 업로드할 경로를 읽어서 아래 변수에 대입
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	//찜 목록 불러오기
	@GetMapping("/listzzim")
	public String listzzim(			
			String searchWord
			,Model model
			,@AuthenticationPrincipal UserDetails user){

		String zzim_id = user.getUsername();
		
		ArrayList<Zzim> zzimList = service.listzzim(zzim_id, searchWord);
		log.debug("넘어간 값 : {}", searchWord);
		model.addAttribute("zzimList", zzimList);
		model.addAttribute("searchWord", searchWord);
		
		return "RecipeView/listZzim";
	}

	//찜 한개 글 읽기
	@GetMapping("/readzzim")
	//int num만 쓰기는 위험하다. 
	//요청 파라미터를 넣어서 넣어달라.
	public String readzzim(
			@RequestParam(name = "num", defaultValue="0") int num
			, Model model) {
		//본문글 정보
		log.debug("read: ",num);
		if(num == 0) {
			return "redirect:listZzim";
		}
		//num이라는 이름의 글번호를 전달받음
		//전달받은 글번호를 서비스로 전달
		Zzim zzim = service.selectzzim(num);
		//서비스가 리턴한 Recipe객체를 Model에 저장
		model.addAttribute("Zzim", zzim);

		//해당 글에 달린 리플 목록 

		//HTML파일로 포워딩하여 출력
		return "RecipeView/readZzim";
	}
	
	//찜 수정
	//수정 폼으로 이동
	@GetMapping("/updatezzim")
	public String updatezzim(int num, Model model, @AuthenticationPrincipal UserDetails user) {
		//전달된 번호의 글정보 읽기
		Zzim zzim = service.selectzzim(num);
		log.debug("잘되니");
		//본인글인지 확인. 아니면 글목록으로 이동.
		if (!zzim.getZzim_id().equals(user.getUsername())) {
			log.debug("꽈당");
			return "redirect:/recipe/listZzim";
		}
		//글정보를 모델에 저장
		log.debug("수정할 찜 번호 : {}", num);	
		model.addAttribute("Zzim", zzim);
		//수정폼 html로 포워딩	
		return "RecipeView/updateZzim";
	}

	// 찜수정폼에서 보낸 내용 처리
	@PostMapping("/updatezzim")
	public String update(Zzim zzim, @AuthenticationPrincipal UserDetails user, MultipartFile upload) {
		log.debug("저장할 글정보 : {}", zzim);
		log.debug("파일 정보: {}", upload);

		//전달된 User객체(글번호, 제목, 내용) 에 로그인한 아이디 추가 저장
		zzim.setZzim_id(user.getUsername());
		Zzim oldZzim = null;
		String oldZzim_savedfile = null;
		String savedfile = null;

		//첨부파일이 있는 경우 기존파일 삭제 후 새 파일 저장
		if (upload != null && !upload.isEmpty()) {
			oldZzim = service.selectzzim(zzim.getZzim_num());
			oldZzim_savedfile = oldZzim == null ? null : oldZzim.getZzim_savedfile();

			savedfile = FileService.saveFile(upload, uploadPath);
			zzim.setZzim_originalfile(upload.getOriginalFilename());
			zzim.setZzim_savedfile(savedfile);
			log.debug("새파일:{}, 구파일:{}", savedfile, oldZzim_savedfile);
		}

		int result = service.updatezzim(zzim);

		//글 수정 성공 and 첨부된 파일이 있는 경우 파일도 삭제
		if (result == 1 && savedfile != null) {
			FileService.deleteFile(uploadPath + "/" + oldZzim_savedfile);
		}
	return "redirect:readzzim?num=" + zzim.getZzim_num();
	}
	
	//찜 삭제
	@GetMapping("/deletezzim")
	public String deletezzim(@RequestParam(name="num", defaultValue="0") int num
			, @AuthenticationPrincipal UserDetails user) {
		//			레시피 읽기 화면에서 레시피번호가 전달됨

		log.debug("삭제할 찜 번호 : {}", num);	
		//			로그인한 사용자의 아이디를 읽음		
		String id = user.getUsername();
		log.debug("로그인한 사용자 아이디 : {}", id);	
		//			글번호로 DB에서 글 내용을 읽음
		Zzim zzim = service.selectzzim(num);
		//			해당번호의 글이 있는지 확인. 없으면 글목록으로
		if (zzim == null) return "redirect:listzzim";
		//			로그인한 본인의 글이 맞는지 확인. 아니면 글목록으로
		if (!zzim.getZzim_id().equals(id)) return "redirect:listzzim";
		//			실제 글 DB에서 삭제
		service.deletezzim(zzim);
		//			글 목록으로 리다이렉트
		return "redirect:listzzim";
	}		

}

