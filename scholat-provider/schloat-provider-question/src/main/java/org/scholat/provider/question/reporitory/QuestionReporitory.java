package org.scholat.provider.question.reporitory;

import java.util.List;

import org.scholat.provider.question.pojo.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import io.lettuce.core.GeoArgs.Sort;

/*
 *@author lsx
 */
public interface QuestionReporitory extends JpaRepository<Question, Integer>{

	Page<Question> findByUserId(Integer userId,Pageable pageable);
	
	Question findByQuestionId(Integer questionId);
	
	Page<Question> findAll(Pageable pageable);
    
}
