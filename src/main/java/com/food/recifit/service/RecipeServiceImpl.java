package com.food.recifit.service;


import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.recifit.dao.RecipeDAO;
import com.food.recifit.domain.Comment;
import com.food.recifit.domain.Recipe;
import com.food.recifit.util.PageNavigator;

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
<<<<<<< HEAD
	public ArrayList<Recipe> list(int start, int count, String type, String searchWord) {
		HashMap<String, String> map = new HashMap<>();
		map.put("type", type);
		map.put("searchWord", searchWord);
		//조회 결과 중 위치, 개수 지정
		RowBounds rb = new RowBounds(start, count);
		
		ArrayList<Recipe> recipelist = recipeDAO.list(map, rb);
		return recipelist;
=======
	public Recipe selectrecipe(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Comment> listcomment(int num) {
		// TODO Auto-generated method stub
		return null;
>>>>>>> 1458adf4b5b9166c9be92523645bdd8c890fbf05
	}

	@Override
	public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type,
			String searchWord) {
		 
			//검색 대상과 검색어
			HashMap<String, String> map = new HashMap<>();
			map.put("type", type);
			map.put("searchWord", searchWord);
			//검색 결과 개수
			int t = RecipeDAO.total(map);
			//페이지 이동 링크수, 페이지당 글수, 현재페이지, 전체 글수를 전달하여 객체 생성
			PageNavigator navi = new PageNavigator(pagePerGroup, countPerPage, page, t);
			
			return navi;
	}
	
	
}
