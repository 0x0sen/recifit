package com.food.recifit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
   int comment_num;       //시퀀스 사용
   int recipe_num;         // 레시피 번호
   String comment_nick;     //댓글 작성자
   String comment_text;     // 댓글 내용
   String comment_date;     //댓글 작성 시간
    int comment_sum;         //댓글의 총 개수  
    
}