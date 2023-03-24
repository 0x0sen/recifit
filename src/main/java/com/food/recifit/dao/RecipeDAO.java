package com.food.recifit.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.food.recifit.domain.Recipe;


/**
 *  레시피에 대한 DAO
 * @author user
 *
 */
@Mapper
public interface RecipeDAO {
	
	//레시피 저장
	public int insertrecipe(Recipe recipe);
	//레시피 제목으로 한개 조회
	public Recipe selectrecipe(int recipe_num);
	//레시피 수정
	public int updaterecipe(Recipe recipe);
	//레시피 삭제
	public int deleterecipe(Recipe recipe);
	//레시피 전체 글 보기
	public ArrayList<Recipe> list(String searchWord);
	//전체 레시피 개수 
	public int total(HashMap<String, String> map);

	}
