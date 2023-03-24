package com.food.recifit.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.food.recifit.domain.Zzim;

@Service
public interface ZzimService {

		//찜 저장
		public int insertzzim(Zzim zzim);
		//찜 리스트 불러오기
		ArrayList<Zzim> listzzim(String searchWord);
		//찜 삭제
		public int deletezzim(Zzim zzim);
		//찜 한개 글 읽기
		public Zzim selectzzim(int zzim_num);
		//찜 수정
		public int updatezzim(Zzim zzim);

}
