package org.scholat.provider.question.reporitory;

import org.scholat.provider.question.pojo.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/*
 * @author lsx
 */
public interface ReplyReporitory extends JpaRepository<Reply, Integer>{

	//查找提问对应的评论
	Page<Reply> findByQuestionId(Integer questionId,Pageable pageable);
	
	//查找个人评论
	List<Reply> findByUserId(Integer userId);
	
}
