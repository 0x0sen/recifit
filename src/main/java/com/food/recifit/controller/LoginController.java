package com.food.recifit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.food.recifit.domain.Login;
import com.food.recifit.service.LoginService;

import lombok.extern.slf4j.Slf4j;

/**
 * 로그인에 관한 컨트롤러 입니다. 
 * 회원가입 회원 탈퇴 등등
 */
@Slf4j
@Controller
@RequestMapping("Login")

public class LoginController {
	
	
	@Autowired
	LoginService service;
	
	
    /**
     * 로그인 화면으로 이동
     */
    @GetMapping("/loginForm")
    public String loginForm() {

        return "LoginView/loginForm";
    }
	
	/**
	 * 가입 폼으로 이동
	 */
	
	@GetMapping("join")
	public String join() {
		return "LoginView/joinForm";
	}
	
	
	/**
	 * 가입 처리
	 * @param member 사용자가 폼에 입력한 정보
	 */
	@PostMapping("join")
	public String join(Login member) {
		log.debug("가입정보 : {}", member);
		int result = service.insertMember(member);
		
		return "redirect:/";
	}
	

	

}
