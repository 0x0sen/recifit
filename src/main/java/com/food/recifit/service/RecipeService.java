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
<<<<<<< HEAD

		public ArrayList<Recipe> list(int start, int count, String type, String searchWord);
		
=======
>>>>>>> 1458adf4b5b9166c9be92523645bdd8c890fbf05

		public Recipe selectrecipe(int num);
		//리플 보기 
		public ArrayList<Comment> listcomment(int num);
		
		public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type,
				String searchWord);
		
}
