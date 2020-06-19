package org.scholat.provider.question.reporitory;

import java.util.List;

import org.scholat.provider.question.pojo.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
/*
 * @author lsx
 */
public interface ReplyReporitory extends JpaRepository<Reply, Integer>{

	//查找提问对应的评论
	List<Reply> findByQuestionId(Integer questionId);
	
	//查找个人评论
	List<Reply> findByUserId(Integer userId);
}
