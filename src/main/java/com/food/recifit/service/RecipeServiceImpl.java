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

	//레시피 저장
	@Override
	public int insertrecipe(Recipe recipe) {
		int result= recipeDAO.insertrecipe(recipe);
		return result;
	}
	
	//레시피 제목으로 한개 조회
	@Override
	public Recipe selectrecipe(int recipe_num) {
		// TODO Auto-generated method stub
		//조회수 1증가 (이게 있어서 조회수 증가가 가능함)
		//recipeDAO.add(recipe_num);
		//레시피 읽기
		Recipe recipe = recipeDAO.selectrecipe(recipe_num);
		return recipe;
	}
	
	//전체 레시피 전체목록 + 검색 
	@Override
	public ArrayList<Recipe> list(String searchWord) {
		ArrayList<Recipe> recipelist = recipeDAO.list(searchWord);
		return recipelist;
	}

	//레시피 수정
	@Override
	public int updaterecipe(Recipe recipe) {
		int n = recipeDAO.updaterecipe(recipe);
		return n;
	}
	
	//레시피 삭제
	@Override
	public int deleterecipe(Recipe recipe) {
		int n = recipeDAO.deleterecipe(recipe);
		return n;
	}

	
	
	//음식 종류별 목록
	@Override
	public ArrayList<Recipe> KoreanList(String searchWord) {
		ArrayList<Recipe> recipelist = recipeDAO.KoreanList(searchWord);
		return recipelist;
	}

	@Override
	public ArrayList<Recipe> WesternList(String searchWord) {
		ArrayList<Recipe> recipelist = recipeDAO.WesternList(searchWord);
		return recipelist;
	}

	@Override
	public ArrayList<Recipe> JapaneseList(String searchWord) {
		ArrayList<Recipe> recipelist = recipeDAO.JapaneseList(searchWord);
		return recipelist;
	}

	@Override
	public ArrayList<Recipe> ChineseList(String searchWord) {
		ArrayList<Recipe> recipelist = recipeDAO.ChineseList(searchWord);
		return recipelist;
	}

	@Override
	public ArrayList<Recipe> AsianList(String searchWord) {
		ArrayList<Recipe> recipelist = recipeDAO.AsianList(searchWord);
		return recipelist;
	}

	@Override
	public ArrayList<Recipe> dessertList(String searchWord) {
		ArrayList<Recipe> recipelist = recipeDAO.dessertList(searchWord);
		return recipelist;
	}

	
	
	//조회 결과 중 위치, 개수 지정
	//RowBounds rb = new RowBounds(start, count);

}
