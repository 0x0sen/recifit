package com.food.recifit.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.food.recifit.domain.Comment;
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
	
	//레시피 전체 글 보기
	public ArrayList<Recipe> list(HashMap<String, String> map);
	
	//전체 레시피 개수
	public int total(HashMap<String, String> map);

	//레시피 삭제
	public int deleteRecipe(Recipe recipe);

	//레시피 한개 보기
	public Recipe selectRecipe(int num);

	//레시피 수정
	public int updateRecipe(Recipe recipe); 
	
	//조회 수 1증가
	public int add(int recipe_num);

	//리플 목록
	public ArrayList<Comment> readComment(int recipe_num);

	//리플 저장
	public int insertComment(Comment comment);

	//리플 삭제
	public int deleteComment(Comment comment);
	
	
	}
