package com.food.recifit.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.food.recifit.domain.Zzim;

/**
 * 찜에 대한 zzimDAO
 * @author user
 *
 */
@Mapper
public interface ZzimDAO {
	//찜 저장
	public int insertzzim(Zzim zzim);
	//찜 리스트 불러오기
	public ArrayList<Zzim> listzzim(HashMap<String, Object> map);
	//찜 삭제
	public int deletezzim(Zzim zzim);
	//찜 한개 글 읽기
	public Zzim selectzzim(int num);
	//찜 목록에서 레시피 찾기
	public Zzim findzzim(HashMap<String, Object> map);
	//찜 수정
	public int updatezzim(Zzim zzim); 
	//전체 찜 개수 
	public int totalzzim(HashMap<String, String> map);
	//찜 pdf
	public int pdfzzim(Zzim zzim);

}
