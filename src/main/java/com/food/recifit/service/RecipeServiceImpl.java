package com.food.recifit.service;


import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.food.recifit.dao.RecipeDAO;
import com.food.recifit.domain.Recipe;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring5.domain.Board;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
	
	@Autowired
	RecipeDAO recipeDAO;

	@Override
	public int write(Recipe recipe) {
		int result= recipeDAO.insert(recipe);
		return result;
	}

	@Override
	public ArrayList<Recipe> list(int start, int count, String type, String searchWord) {
		HashMap<String, String> map = new HashMap<>();
		map.put("type", type);
		map.put("searchWord", searchWord);
		//조회 결과 중 위치, 개수 지정
		RowBounds rb = new RowBounds(start, count);
		
		ArrayList<Recipe> recipelist = recipeDAO.list(map, rb);
		return recipelist;
	}
	
	
}
