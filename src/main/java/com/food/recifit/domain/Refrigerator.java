package com.food.recifit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Refrigerator {
	  String user_id;			 //냉장고 주인 (user테이블의 user_id 컬럼을 참조한다.)	
	  int refrigerator_num;		 //냉장고 시퀀스 사용
	  String refrigerator_need;  //냉장고 재료 
	  String refrigerator_date;  //재료 날짜
}