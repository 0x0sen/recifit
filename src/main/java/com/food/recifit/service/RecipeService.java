package com.food.recifit.service;

import java.util.ArrayList;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.food.recifit.domain.Comment;
import com.food.recifit.domain.Recipe;
import com.food.recifit.util.PageNavigator;



/**
 * 게시판
 */
@Service
public interface RecipeService {
	
		//마이레시피 저장
		public int write(Recipe recipe);

		//레시피 전체 목록 or 검색
		public ArrayList<Recipe> list(String type, String searchWord);
		

		
		
//		public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type,
//				String searchWord);

		
		//레시피 하나 보기
		public Recipe selectRecipe(int num);
		
		//리플 보기 
		public ArrayList<Comment> commentList(int num);


		//레시피 삭제
		public int delete(Recipe recipe);

		//레시피 수정
		public int update(Recipe recipe);

		//리플 달기
		public int writeComment(Comment comment);

		//리플 삭제
		public int deleteComment(Comment comment);
		
}
