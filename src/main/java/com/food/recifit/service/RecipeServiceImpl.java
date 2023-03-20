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
	public ArrayList<Recipe> list() {
		ArrayList<Recipe> recipeList = recipeDAO.list();
		return recipeList;
		
	}
	
	
}
