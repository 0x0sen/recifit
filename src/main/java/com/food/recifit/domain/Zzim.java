package com.food.recifit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zzim {
	  int zzim_num;				//찜 번호 시퀀스 사용
	  int recipe_num;			//찜한 레시피 번호,
	  String zzim_date; 		//찜한 날짜
	  String zzim_id;			//찜한사람 아이디(user테이블의 user_id 컬럼을 참조한다.)
	  String zzim_name;     	//찜 레시피 이름
	  String zzim_originalfile; //찜 레시피 사진(originalfile)
	  String zzim_savedfile;	//찜 레시피 사진 저장(savedfile)
	  String zzim_need; 		//찜 레시피 재료
	  String zzim_howto;  		//찜 레시피 설명
	  String zzim_type; 		//찜 레시피 분류
	  String zzim_icon; 		//ex)('이유식,'매운맛','다이어트식’) 
}