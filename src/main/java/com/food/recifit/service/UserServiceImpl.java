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
	    private UserDAO memberDAO;
	    
	    @Autowired
	    private PasswordEncoder passwordEncoder;

		@Override
		public int insertMember(User member) {
			System.out.println(member);
			String encodedPassword = passwordEncoder.encode(member.getUser_pw());
			member.setUser_pw(encodedPassword);
			
			int result = memberDAO.insertMember(member);
			return result;
		}


	}
