package com.food.recifit.service;



import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.recifit.dao.RecipeDAO;
import com.food.recifit.domain.Comment;
import com.food.recifit.domain.Recipe;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
	
	@Autowired
	RecipeDAO recipeDAO;

	//마이레시피 저장
	@Override
	public int write(Recipe recipe) {
		int result= recipeDAO.insertRecipe(recipe);
		return result;
	}

	//레시피 전체 목록 or 검색
	@Override
	public ArrayList<Recipe> list(String type, String searchWord) {
		HashMap<String, String> map = new HashMap<>();
		map.put("type", type);
		map.put("searchWord", searchWord);
		//조회 결과 중 위치, 개수 지정
		//RowBounds rb = new RowBounds(start, count);
		
		ArrayList<Recipe> recipelist = recipeDAO.list(map);
		return recipelist;

	}

	@Override
	public Recipe selectRecipe(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Comment> commentList(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Recipe recipe) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Recipe recipe) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int writeComment(Comment comment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteComment(Comment comment) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
