package com.food.recifit.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.food.recifit.domain.Comment;
import com.food.recifit.service.CommentService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("recipe")
@ResponseBody
public class CommentRestController {
	//rest가 들어가 있으면 ajax가 들어간 컨트롤러
	//@ResponseBody 를 한방에 붙여버린다.
	//서비스로 전달해서 DB에 저장
	/*
	 * 서비스에 필요한 기능들
	 *1. 이름과 글내용 전달받아 DB에 저장 
	 *2. 번호를 전달받아 DB에서 삭제
	 *3. DB의 모든 정보를  ArrayList<Comment>로 리턴
	 */

	@Autowired
	CommentService service;




}
