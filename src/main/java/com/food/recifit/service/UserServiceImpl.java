package com.food.recifit.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.food.recifit.dao.UserDAO;
import com.food.recifit.domain.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO dao;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public int insertUser_id(User member) {
		//비밀번호 암호화
		String pw = encoder.encode(member.getUser_pw());//비번 꺼내옴
		member.setUser_pw(pw);//비번 암호화해서 넣음		member.setMemberpw(pw);//비번 암호화해서 넣음
		
		int n = dao.insertUser_id(member);
		return n;
	}

	@Override
	public boolean idCheck(String id) {
		User member = dao.selectOneUser(id);
//		boolean res;
//		if (member == null) {
//			res = false;			
//		}
//		else {
//			res = true;
//		}
//		return res;

		return member != null;
	}

	@Override
	public User getMember(String id) {
		User member = dao.selectOneUser(id);		
		return member;
	}
	
	@Override
	public int updateMember(User member) {

		//수정할 비밀번호 있으면 암호화
		if(member.getUser_pw() != null && member.getUser_pw().length() != 0) {
			String pw = encoder.encode(member.getUser_pw());//비번 꺼내옴
			member.setUser_pw(pw);//비번 암호화해서 넣음
		}
		int n = dao.updateMember(member);
		return n;

	}
	
	@Override
	public int delete(User a) {
		String id = a.getUser_id();
		User list = dao.selectOneUser(id);
		// Member라는 객체의 사용자a를 선언함.
		//사용자a의 아이디를 불러와서 id에 넣음.
		//DB에 id의 객체를 불러옴.
		
		//사용자 비번이랑 DB id 비번이랑 같니?
		if(encoder.matches(a.getUser_pw(),(list.getPassword()))){
		int n = dao.delete(id);		
		return n;		
		}
	return 0;
	}

	@Override
	public ArrayList<User> selectAll() {
		ArrayList<User> list = dao.selectAll();
		return list;
	}

	}
