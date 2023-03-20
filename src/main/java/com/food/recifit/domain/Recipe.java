package com.food.recifit.domain;

import java.util.Collection;

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
public class Recipe implements UserDetails{
    int recipe_num;         //시퀀스 번호
    String recipe_name;     //레시피 이름
    String originalfile;      //레시피 사진(첨부파일 원래이름)
    String savedfile;		//레시피 사진(첨부파일 서버에 저장)
    String recipe_need;    //레시피 재료
    String recipe_howto;    //레시피 설명
    String recipe_type;      //레시피 분류
    String recipe_date;      //올린 일자
    String recipe_icon;      //ex)('이유식,'매운맛,'다이어트식’)
    int recipe_hit;         //레시피 조회수
    int recipe_zzimSum;     //레시피 찜수
    String recipe_nick;    //레시피 닉네임
    
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
   
    
   
}