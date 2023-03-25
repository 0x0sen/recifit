package com.food.recifit.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.food.recifit.domain.User;



@Service
public interface UserService {

	/**
	 * 회원정보 저장 (가입)
	 * @param member 가입양식에서 전달된 회원정보
	 * @return 저장된 개수
	 */
	public int insertUser_id(User member);

	/**
	 * 아이디 존재 확인
	 * @param id 찾을 아이디
	 * @return 해당 아이디 존재 여부 (있으면 true)
	 */

	public boolean idCheck(String id);
	
	/**
	 * 아이디로 회원 정보 찾기
	 * @param id 검색할 아이디
	 * @return 해당 회원의 정보
	 */
	
	public User getMember(String id);
	/**
	 * 회원정보 수정
	 * @param member 수정할 정보
	 * @return 처리한 행 개수
	 */
	public int updateMember(User member);
	/**
	 * 회원 삭제
	 * @param member 삭제할 정보
	 * @return 처리한 행 개수
	 */
	public int delete(User member);

	public ArrayList<User> selectAll();
}
