package com.food.recifit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zzim {
    int zzim_num;         //시퀀스 사용
    int recipe_num;       //찜한 레시피 번호,
    String zzim_date;     //찜한 날짜
    String user_id;       //찜 아이디  
}