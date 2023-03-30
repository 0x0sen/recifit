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
import org.springframework.web.bind.annotation.ResponseBody;
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
			, Model model){

		ArrayList<Zzim> zzimList = service.listzzim(searchWord);
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

	//찜 저장 폼
//	@GetMapping("/insertzzim")
//	public String write() {		
//		return "redirect:/recipe/listZzim";
//	}
	//여러개 받을 때, 하나하나변수선언하는 방법1
	@ResponseBody
	@PostMapping("insertzzim")
	public void insertzzim(String recipe_name
			,String recipe_type
			,String recipe_icon
			,String user_id
			,String recipe_need
			,String recipe_howto) {
		//전달받은 값 console에 출력
		log.debug("recipe_name:{}"
				+ ", recipe_type:{}"
				+ ", recipe_icon:{}"
				+ ", user_id:{}"
				+ ", recipe_need:{}"
				+ ", recipe_howto:{}"
				, recipe_name, recipe_type, recipe_icon, user_id, recipe_need, recipe_howto);
	}
//	//찜 저장
//	@PostMapping("/insertzzim")
//	public String insertzzim(Zzim zzim
//			, @AuthenticationPrincipal UserDetails user
//			, MultipartFile upload) {
//
//		//첨부파일이 있으면 지정한 경로에 저장하고 파일명을 zzim객체에 추가
//		if ( upload != null && !upload.isEmpty()) {
//			String filename = FileService.saveFile(upload, uploadPath);
//			zzim.setZzim_originalfile(upload.getOriginalFilename());
//			zzim.setZzim_savedfile(filename);
//		}
//		//로그인한 아이디 읽어서 board객체에 추가 
//		zzim.setZzim_id(user.getUsername());
//
//		//서비스로 전달해서 DB에 저장
//		log.debug("전달된 객체 : {}", zzim);
//		service.insertzzim(zzim);
//		return "redirect:/recipe/listZzim";
//	}

	//찜 수정
	//수정 폼으로 이동
	@GetMapping("/updatezzim")
	public String updatezzim(int num, Model model, @AuthenticationPrincipal UserDetails user) {
		//전달된 번호의 글정보 읽기
		Zzim zzim = service.selectzzim(num);
		log.debug("잘되니");
		//본인글인지 확인. 아니면 글목록으로 이동.
		if (!zzim.getZzim_id().equals(user.getUsername())) {
			return "redirect:/recipe/listZzim";
		}
		//글정보를 모델에 저장
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
		//			로그인한 사용자의 아이디를 읽음
		String id = user.getUsername();
		//			글번호로 DB에서 글 내용을 읽음
		Zzim zzim = service.selectzzim(num);
		//			해당번호의 글이 있는지 확인. 없으면 글목록으로
		if (zzim == null) return "redirect:list";
		//			로그인한 본인의 글이 맞는지 확인. 아니면 글목록으로
		if (!zzim.getZzim_id().equals(id)) return "redirect:list";
		//			첨부된 파일이 있으면 파일삭제
		if (zzim.getZzim_savedfile() != null) {
			FileService.deleteFile(uploadPath + "/" + zzim.getZzim_savedfile());
		}
		//			실제 글 DB에서 삭제
		service.deletezzim(zzim);
		//			글 목록으로 리다이렉트
		return "redirect:listZzim";
	}		


	//찜 파일 다운로드
	@GetMapping("/downloadzzim")
	public String downloadzzim(
			@RequestParam(name = "num", defaultValue="0") int num
			,HttpServletResponse response) {

		//num이라는 이름의 글번호를 전달받음
		//전달받은 글번호를 서비스로 전달
		Zzim zzim = service.selectzzim(num);
		if(zzim == null || zzim.getZzim_savedfile() == null) {
			return "redirect:listZzim";
		}

		String file = uploadPath + "/" + zzim.getZzim_savedfile();

		//FileInputStream
		FileInputStream in = null;
		ServletOutputStream out = null;

		try {
			//응답 정보의 헤더 세팅
			response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(zzim.getZzim_originalfile(), "UTF-8"));

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
}

