package com.food.recifit.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.recifit.dao.CommentDAO;
import com.food.recifit.dao.ZzimDAO;
import com.food.recifit.domain.Comment;
import com.food.recifit.domain.Recipe;
import com.food.recifit.domain.Zzim;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ZzimServiceImpl implements ZzimService{

	@Autowired
	ZzimDAO zzimDAO;

	//찜 저장
	@Override
	public int insertzzim(Zzim zzim) {
		int result = zzimDAO.insertzzim(zzim);
		return result;
		
	}
	
	//찜 리스트 불러오기
	@Override
	public ArrayList<Zzim> listzzim(String searchWord) {
		ArrayList<Zzim> zzimlist = zzimDAO.listzzim(searchWord);
		return zzimlist;
	}
	
	//찜 삭제
	@Override
	public int deletezzim(Zzim zzim) {
		int cnt = zzimDAO.deletezzim(zzim);
		return cnt;
	}
	//찜 한개 글 읽기
	public Zzim selectzzim(int zzim_num) {
		Zzim zzim = zzimDAO.selectzzim(zzim_num);
		return zzim;
	}
	//찜 수정
	public int updatezzim(Zzim zzim) {
		int n = zzimDAO.updatezzim(zzim);
		return n;
	}
	
}
