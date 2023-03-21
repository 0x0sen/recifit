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


		public ArrayList<Recipe> list(String type, String searchWord);
		

		
		
//		public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type,
//				String searchWord);

		public Recipe selectrecipe(int num);
		//리플 보기 
		public ArrayList<Comment> listcomment(int num);
		
}
