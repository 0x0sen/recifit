package com.food.recifit.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.food.recifit.domain.Recipe;

/**
 *  모든 글 보기
 * @author user
 *
 */
@Mapper
public interface RecipeDAO {

	int insert(Recipe recipe);

	ArrayList<Recipe> list();
	
}
