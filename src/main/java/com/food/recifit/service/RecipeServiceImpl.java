package com.food.recifit.service;


import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	@Override
	public int write(Recipe recipe) {
		int result= recipeDAO.insert(recipe);
		return result;
	}

	@Override
	public Recipe selectrecipe(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Comment> listcomment(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Recipe> list(String type, String searchWord) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
