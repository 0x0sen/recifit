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

	

//	@Override
//	public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type,
//			String searchWord) {
//		 
//			//검색 대상과 검색어
//			HashMap<String, String> map = new HashMap<>();
//			map.put("type", type);
//			map.put("searchWord", searchWord);
//			//검색 결과 개수
//			int t = RecipeDAO.total(map);
//			//페이지 이동 링크수, 페이지당 글수, 현재페이지, 전체 글수를 전달하여 객체 생성
//			PageNavigator navi = new PageNavigator(pagePerGroup, countPerPage, page, t);
//			
//			return navi;
//	}
	public Recipe selectRecipe(int num) {
		//조회수 1증가
		recipeDAO.add(num);
				
		//글 정보 읽기
		Recipe recipe = recipeDAO.selectRecipe(num);
		return recipe;
	}

	@Override
	public ArrayList<Comment> commentList(int recipe_num) {
		ArrayList<Comment> commentList = recipeDAO.readComment(recipe_num);
		return commentList;
	}

	@Override
	public int delete(Recipe recipe) {
		int result = recipeDAO.deleteRecipe(recipe);
		return result;
	}

	@Override
	public int update(Recipe recipe) {
		int result = recipeDAO.updateRecipe(recipe);
		return result;
	}

	@Override
	public int writeComment(Comment comment) {
		int n = recipeDAO.insertComment(comment);
		return n;
	}

	@Override
	public int deleteComment(Comment comment) {
		int result = recipeDAO.deleteComment(comment);
		return result;
	}


	
	
}
