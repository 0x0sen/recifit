package com.food.recifit.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.food.recifit.domain.Zzim;

/**
 * 로그인에 관한 DAO
 * @author user
 *
 */
@Mapper
public interface ZzimDAO {
	//찜 저장
	int insertZzim(Zzim zzim);
	//찜 리스트 불러오기
	ArrayList<Zzim> listZzim();
	//찜 삭제
	int deleteZzim(int num);
}
