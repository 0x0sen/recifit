package com.food.recifit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.food.recifit.domain.Comment;
import com.food.recifit.domain.Recipe;
import com.food.recifit.domain.Refrigerator;

/**
 * 게시판
 */
@Service
public interface RecipeService {
	
	//레시피 저장
	public int insertrecipe(Recipe recipe);
	//레시피 제목으로 한개 조회
	public Recipe selectrecipe(int recipe_num);
	//전체 레시피 전체목록 + 검색 
	public ArrayList<Recipe> list(String searchWord);	
	//레시피 수정
	public int updaterecipe(Recipe recipe);
	//레시피 삭제
	public int deleterecipe(Recipe recipe);
	//페이지 정보 객체 생성
	//public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String searchWord);
	
	//한식 레시피
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
	
    public ArrayList<Recipe> recifitCheck(String searchWord_values, String recipe_type, String recipe_icon_values);
	
	public ArrayList<Recipe> Homelist();
	//냉장고 재료보기
	public ArrayList<Refrigerator> refrigeratorlist(String user_id);
	
	
	
	
	
	
	
	
	
	
	
	
	//레시피 추천
	//public ArrayList<Recipe> recifitCheck(String searchWord, String recipe_type, String recipe_icon);
	//public ArrayList<Recipe> recifitCheck(List<String> searchWords, String recipe_type, List<String> recipeIcons);
	
	
		
}
