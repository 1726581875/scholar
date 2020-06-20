package org.scholat.provider.question.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class Question {
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)   //主键自增
   private Integer questionId;
   
   private Integer courseId;
   
   private Integer userId;
   
   @Column(name="question_content")
   private String questionContent;
   
   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
   private Timestamp createTime;
   

}
