package com.food.recifit.service;



import org.springframework.stereotype.Service;

import com.food.recifit.domain.Recipe;





/**
 * 게시판
 */
@Service
public interface RecipeService {
	
		//마이레시피 저장
		public int write(Recipe recipe);
		

}
