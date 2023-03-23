package com.food.recifit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
   int comment_num;         //코멘트 번호 (시퀀스)
   int recipe_num;          //레시피 번호
   String comment_text;     //코멘트 내용
   String comment_date;     //코멘트 작성 시간
   int comment_sum;         //코멘트 총 개수  
   String user_id;  	    //코멘트 아이디    


}