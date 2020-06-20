package org.scholat.provider.question.pojo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import lombok.Data;
/*
 * @author lsx
 */
@Data
public class SingleQueAndRepList {
	
	private Optional<Question> question;
	
	private Page<Reply> replyList;

}
