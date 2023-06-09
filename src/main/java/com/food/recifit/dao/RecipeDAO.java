package com.food.recifit.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.food.recifit.domain.Comment;
import com.food.recifit.domain.Recipe;
import com.food.recifit.domain.Refrigerator;


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
	
	public ArrayList<Recipe> Homelist();
	
	//레시피 전체 글 보기
	public ArrayList<Recipe> list(String searchWord);
	//전체 레시피 개수 
	public int total(HashMap<String, String> map);
	//댓글 목록
	public ArrayList<Comment> commentlist(int num);
	
	//한식 목록
	public ArrayList<Recipe> KoreanList(String searchWord);
	
	//양식 레시피
	public ArrayList<Recipe> WesternList(String searchWord);
	
	//일식 레시피
	public ArrayList<Recipe> JapaneseList(String searchWord);
	
	//중식 레시피
	public ArrayList<Recipe> ChineseList(String searchWord);
	
	//아시안 레시피
	public ArrayList<Recipe> AsianList(String searchWord);
	
	//디저트 레시피
	public ArrayList<Recipe> dessertList(String searchWord);
	
	public ArrayList<Recipe> recifitCheck(HashMap<String, Object> map);
	
    public int add(int recipe_num);
    
    //냉장고 재료보기
	public ArrayList<Refrigerator> refrigeratorlist(String user_id);
	
	
	
	
	
	
	//레시피 추천
	//public ArrayList<Recipe> recifitCheck(HashMap<String, String> map);
	//public ArrayList<Recipe> recifitCheck(List<String> searchWords, String recipe_type, List<String> recipeIcons);

	}
