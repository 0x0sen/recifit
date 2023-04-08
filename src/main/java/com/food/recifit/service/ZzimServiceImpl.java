package com.food.recifit.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	@Autowired
	PasswordEncoder encoder;
	
	//찜 저장
	@Override
	public int insertzzim(Zzim zzim) {
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("recipe_num", zzim.getRecipe_num());
		map.put("zzim_id", zzim.getZzim_id());
		
		Zzim cnt = zzimDAO.findzzim(map);
		log.info(cnt+"");
		if(cnt == null) {
			result = zzimDAO.insertzzim(zzim);
			}
		return result;		
	}
	
	//찜 리스트 불러오기
	@Override
	public ArrayList<Zzim> listzzim(String zzim_id, String searchWord) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("zzim_id", zzim_id);
		map.put("searchWord", searchWord);
	
		ArrayList<Zzim> zzimList = zzimDAO.listzzim(map);
		return zzimList;
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
	
	//찜 목록에서 레시피 찾기
	@Override
	public Zzim findzzim(int zzim_num, String zzim_id) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("zzim_num", zzim_num);
		map.put("zzim_id", zzim_id);
		Zzim zzim = zzimDAO.findzzim(map);
		return zzim;
	}

	//찜 수정
	public int updatezzim(Zzim zzim) {
		int n = zzimDAO.updatezzim(zzim);
		return n;
	}
	
}
