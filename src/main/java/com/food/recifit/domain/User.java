package com.food.recifit.domain;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 게시글 정보
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails{
	
	    String user_nick;      	//로그인시 사용할 아이디
	    String user_pw;			//비밀번호
	    String user_name;    	//가입자 이름
	    String user_phone;   	//가입자 전화번호
	    String user_email;		//가입자 이메일
	    String user_id;       	//닉네임ex)요리왕
	    String user_originalfile;   //가입자 사진(첨부파일 원래이름)
	    String user_savedfile; 		//가입자 사진(첨부파일 서버에 저장)
	    String user_pr;     		//가입자 소개
	    String user_role;   		//일반유저,관리자 구분 관리자는 ‘ROLE_ADMIN’
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return false;
		}
		public CharSequence getMemberpw() {
			// TODO Auto-generated method stub
			return null;
		}


		
}
