package com.food.recifit.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
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
@ResponseBody
public class ZzimRestController {

	@Autowired
	ZzimServiceImpl service;
	
	//설정파일에 정의된 업로드할 경로를 읽어서 아래 변수에 대입
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;

	//찜 저장
	@PostMapping("insertzzim")
	public int insertzzim(Zzim zzim
			, @AuthenticationPrincipal UserDetails user
			, MultipartFile upload) {

		//첨부파일이 있으면 지정한 경로에 저장하고 파일명을 zzim객체에 추가
		if ( upload != null && !upload.isEmpty()) {
			String filename = FileService.saveFile(upload, uploadPath);
			zzim.setZzim_originalfile(upload.getOriginalFilename());
			zzim.setZzim_savedfile(filename);
		}
		//로그인한 아이디 읽어서 zzim객체에 추가 
		zzim.setZzim_id(user.getUsername());

		//서비스로 전달해서 DB에 저장
		log.debug("찜저장성공");
		int result = service.insertzzim(zzim);
		return result;
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

