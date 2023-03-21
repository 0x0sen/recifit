package com.food.recifit.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.food.recifit.domain.Zzim;

@Service
public interface ZzimService {

		//찜 저장
		public int insertZzim(Zzim zzim);
		//찜 리스트 불러오기
		public ArrayList<Zzim> listZzim();
		//찜 삭제
		public int deleteZzim(int num);
}
