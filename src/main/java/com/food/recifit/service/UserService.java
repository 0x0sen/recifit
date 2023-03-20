package com.food.recifit.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.food.recifit.domain.User;






/**
 * 게시판
 */
@Service
public interface UserService {

	/**
	 * 회원가입
	 * @param member 저장할 회원 정보
	 * @return 저장된 행 개수
	 */
	public int insertMember(User member);
}
