package com.food.recifit.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.recifit.dao.CommentDAO;
import com.food.recifit.dao.ZzimDAO;
import com.food.recifit.domain.Comment;
import com.food.recifit.domain.Zzim;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ZzimServiceImpl implements ZzimService{

	@Autowired
	ZzimDAO zzimDAO;

	@Override
	public int insertZzim(Zzim zzim) {
		int zim = zzimDAO.insertZzim(zzim);
		return zim;
		
	}
	
	@Override
	public ArrayList<Zzim> listZzim() {
		ArrayList<Zzim> list = zzimDAO.listZzim();
		return list;
	}
	
	@Override
	public int deleteZzim(int num) {
		int cnt = zzimDAO.deleteZzim(num);
		return cnt;
	}
	
}
