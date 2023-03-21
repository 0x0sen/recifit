package com.food.recifit.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.food.recifit.domain.Recipe;


/**
 *  모든 글 보기
 * @author user
 *
 */
@Mapper
public interface RecipeDAO {
	
	//레시피 저장
	public int insertRecipe(Recipe recipe);
	//레시피 제목으로 한개 조회
	public Recipe selectRecipe(int recipe_num);
	//레시피 수정
	public int updateRecipe(Recipe recipe);
	//레시피 삭제
	public int deleteRecipe(Recipe recipe);
	//레시피 전체 글 보기
	public ArrayList<Recipe> list(HashMap<String, String> map);
	//전체 레시피 개수 
	public int total(HashMap<String, String> map);
	
	}
