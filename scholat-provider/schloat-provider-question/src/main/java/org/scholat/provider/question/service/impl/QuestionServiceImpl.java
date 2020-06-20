package org.scholat.provider.question.service.impl;

import java.util.List;
import java.util.Optional;

import org.scholat.provider.question.pojo.Question;
import org.scholat.provider.question.pojo.Reply;
import org.scholat.provider.question.pojo.SingleQueAndRepList;
import org.scholat.provider.question.reporitory.QuestionReporitory;
import org.scholat.provider.question.service.QuestionService;
import org.scholat.provider.question.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
/*
 * @author lsx
 */
@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionReporitory questionReporitory;
	
	@Autowired
	private ReplyService replyService;
	
	@Override
	public void addQuestion(Question question) {
		// TODO Auto-generated method stub
		questionReporitory.saveAndFlush(question);
		
	}

	@Override
	public void deleteQuestion(Integer questionId) {
		// TODO Auto-generated method stub
		questionReporitory.deleteById(questionId);
	}

	@Override
	public SingleQueAndRepList findSingleQuestion(Integer questionId,Pageable pageable) {
		// TODO Auto-generated method stub		
		SingleQueAndRepList singQueAndRepList = new SingleQueAndRepList();
		Optional<Question> question = questionReporitory.findById(questionId);
		singQueAndRepList.setQuestion(question);
		Page<Reply> replyList = replyService.findReply(questionId,pageable);
		singQueAndRepList.setReplyList(replyList);
		return singQueAndRepList;
	}

	@Override
	public Page<Question> allQuestion(Pageable pageable) {
		// TODO Auto-generated method stub
		return questionReporitory.findAll(pageable);
	}

	@Override
	public Page<Question> findQuestionByTeacher(Integer userId,Pageable pageable) {
		// TODO Auto-generated method stub
		return questionReporitory.findByUserId(userId,pageable);
	}

	@Override
	public Question findQuestion(Integer questionId) {
		// TODO Auto-generated method stub
		return questionReporitory.findByQuestionId(questionId);
	}

}
