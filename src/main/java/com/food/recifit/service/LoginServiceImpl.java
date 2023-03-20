package com.food.recifit.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.food.recifit.dao.LoginDAO;
import com.food.recifit.domain.Login;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
	
	 @Autowired
	    private LoginDAO memberDAO;
	    
	    @Autowired
	    private PasswordEncoder passwordEncoder;

		@Override
		public int insertMember(Login member) {
			System.out.println(member);
			String encodedPassword = passwordEncoder.encode(member.getUser_pw());
			member.setUser_pw(encodedPassword);
			
			int result = memberDAO.insertMember(member);
			return result;
		}

	}
