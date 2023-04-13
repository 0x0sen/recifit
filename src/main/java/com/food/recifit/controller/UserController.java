package com.food.recifit.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.food.recifit.domain.Comment;
import com.food.recifit.domain.Refrigerator;
import com.food.recifit.domain.User;
import com.food.recifit.service.RecipeService;
import com.food.recifit.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**111
 * 로그인에 관한 컨트롤러 입니다. 
 * 회원가입 회원 탈퇴 등등
 */
@Slf4j
@RequestMapping("user")
@Controller

public class UserController {


	@Autowired
	UserService service;
	
	@Autowired
	RecipeService service2;

	/**
	 * 로그인 폼으로 이동
	 * @return
	 */
	@GetMapping("loginForm")
	public String loginForm() {
		log.debug("로그인 되냐?");
		return "UserView/loginForm";
	}

	/**
	 * 회원 가입 폼으로 이동
	 * @return 회원가입 양식 HTMl
	 */

	@GetMapping("join")
	public String join() {
		return "UserView/joinForm";
	}



	@PostMapping("join")
	public String join(User member) {
		log.debug("가입데이터 : {}", member);
		service.insertUser_id(member);
		return "redirect:/";
	}

	@GetMapping("idCheck")
	public String idCheck() {

		return "UserView/idCheck";
	}

	@PostMapping("idCheck")
	public String idCheck(String id, Model model) {
		log.debug("검색할 아이디 : {}" , id);

		boolean res = service.idCheck(id);

		model.addAttribute("searchId", id);
		model.addAttribute("result", res);

		return "UserView/idCheck";

	}



	/**
	 * 개인정보 수정 폼으로 이동
	 * @return
	 */
	@GetMapping("mypage")
	public String mypage (@AuthenticationPrincipal UserDetails user, Model model) {
		log.debug("인증정보１ : {}", user.getUsername());
		//DB에서 현재 사용자 정보 읽어서  Member객체로 받음
		//Model에 Member객체 담기
		//수정폼으로 이동		

		User member = service.getMember(user.getUsername());

		model.addAttribute("member", member);
		return "UserView/mypageForm";
	}
	/**
	 * 개인정보 수정 처리
	 * @return
	 */
	@PostMapping("mypage")
	public String updateMember(@AuthenticationPrincipal UserDetails user, User member) {
		//수정폼에서 사용자가 입력한 정보를 member로 전달받음->Member member선언.
		//이름, 전화번호, 이메일, 주소
		//로그인한 ID를 읽어서 member객체에 추가저장
		//서비스로 전달하여 DB의 내용 수정
		member.setUser_id(user.getUsername());
		service.updateMember(member);
		return "redirect:/";

		
	}
	/**
	 * 회원탈퇴 폼으로 이동
	 * @return
	 */
	@GetMapping("delete")
	public String delete (@AuthenticationPrincipal UserDetails user, Model model) {
		log.debug("인증정보１ : {}", user.getUsername());
		//DB에서 현재 사용자 정보 읽어서  Member객체로 받음
		//Model에 Member객체 담기
		//탈퇴폼으로 이동		

		User member = service.getMember(user.getUsername());

		model.addAttribute("member", member);
		return "UserView/deleteForm";
	}
	/**
	 * 회원 탈퇴 처리
	 * @return
	 */
	@PostMapping("delete")
	public String delete(@AuthenticationPrincipal UserDetails user, User member, Model model) {
		//회원폼에서 사용자가 입력한 정보를 member로 전달받음->Member member선언.
		//로그인한 ID를 읽어서 member객체에 추가저장
		//서비스로 전달하여 DB의 내용 수정

		//로그인한 아이디
		log.debug("인증정보2 : {}", user.getUsername());

		//탈퇴성공하면 로그아웃, 탈퇴실패하면 딜리트폼
		int n = service.delete(member);
		if (n == 0) {
			model.addAttribute("error", 0);
			return "UserView/deleteForm";
		}
		return "redirect://user/logout";

	}

	
	//냉장고 보기
	@GetMapping("myrefrigerator")
	public String myrefrigerator(@AuthenticationPrincipal UserDetails user, Model model) {
		ArrayList<Refrigerator> refrigeratorList = service2.refrigeratorlist(user.getUsername());
		log.debug("넘어간 값 : {}", user.getUsername());
		model.addAttribute("refrigeratorList", refrigeratorList);		
		return "UserView/myrefrigerator";
	}
	
	/**
	 * 냉장고 채우기 폼으로 이동
	 * @return
	 */
	@GetMapping("inputrefrigerator")
	public String inputrefrigerator() {
		return "UserView/inputrefrigerator";
	}
	
	@PostMapping("inputrefrigerator")
	public String inputrefrigerator(Refrigerator need) {
		log.debug("냉장고넣기 : {}", need);
		int n = service.inputrefrigerator(need);
		return "redirect:/";
	}
	
	//냉장고 비우기
	@GetMapping("deleterefrigerator")
	public String deleterefrigerator(
			Refrigerator need) {
		log.debug("냉장고삭제 : {}", need);
		int result = service.deleterefrigerator(need);

		return "redirect:/user/myrefrigerator";
	}
}




