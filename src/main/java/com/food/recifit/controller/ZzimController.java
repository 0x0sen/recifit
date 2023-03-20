package com.food.recifit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 로그인에 관한 컨트롤러 입니다. 
 * 회원가입 회원 탈퇴 등등
 */
@Slf4j
@RequestMapping("Zzim")
@Controller

public class ZzimController {

	
	@GetMapping()
	public String Zzim() {
	//틀입니다. 다시 작성해주세요
		return "home";
	}
	

}

	