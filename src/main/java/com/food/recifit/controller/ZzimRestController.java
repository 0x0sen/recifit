package com.food.recifit.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.food.recifit.domain.Comment;
import com.food.recifit.domain.Zzim;
import com.food.recifit.service.CommentService;
import com.food.recifit.service.ZzimServiceImpl;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("recipe")
@ResponseBody
public class ZzimRestController {
			//rest가 들어가 있으면 ajax가 들어간 컨트롤러
			//@ResponseBody 를 한방에 붙여버린다.
			//서비스로 전달해서 DB에 저장
			/*
			 * 서비스에 필요한 기능들
			 *1. 이름과 글내용 전달받아 DB에 저장 
			 *2. 번호를 전달받아 DB에서 삭제
			 *3. DB의 모든 정보를  ArrayList<Zzim>로 리턴
 			 */
			
		@Autowired
		ZzimServiceImpl service;
		
		//찜 저장
		@PostMapping("insertZzim")
		public void insert(Zzim zzim) {
			//서비스로 전달해서 DB에 저장
			log.info("전달된 객체 : {}", zzim);
			service.insertZzim(zzim);
		}
		//찜 목록 불러오기
		@GetMapping("listZzim")
		public ArrayList<Zzim> list(){
			ArrayList<Zzim> list = service.listZzim();
			log.debug("결과:{}", list);
			return list;
		}
		//찜 제거
		public int deleteComment(int num) {
			int cnt = service.deleteZzim(num);
			return cnt;
		}		
}
