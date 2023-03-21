package com.food.recifit.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.food.recifit.domain.Comment;
import com.food.recifit.domain.Recipe;


/**
 * 게시판
 */
@Service
public interface RecipeService {
	
		//마이레시피 저장
		public int write(Recipe recipe);

		public Recipe selectrecipe(int num);
		//리플 보기 
		public ArrayList<Comment> listcomment(int num);
		
}
