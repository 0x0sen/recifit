package com.food.recifit.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.food.recifit.domain.Recipe;

/**
 * 게시판
 */
@Service
public interface RecipeService {
	
	//레시피 저장
	public int insertrecipe(Recipe recipe);
	//레시피 제목으로 한개 조회
	public Recipe selectrecipe(int recipe_num);
	//전체 레시피 전체목록 + 검색 
	public ArrayList<Recipe> list(String searchWord);	
	//레시피 수정
	public int updaterecipe(Recipe recipe);
	//레시피 삭제
	public int deleterecipe(Recipe recipe);
	//페이지 정보 객체 생성
	//public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String searchWord);	
		
}
