package org.scholat.proviser.course.handle;

import lombok.extern.slf4j.Slf4j;
import org.scholat.common.ResultMsg;
import org.scholat.common.message.enums.CommonEnum;
import org.scholat.common.message.exception.CommonException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class CourseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object courseExceptionHandler(Exception e){
        if(e instanceof CommonException){//如果捕获的异常是自定义异常
            CommonException common = (CommonException) e;
            return new ResultMsg<Object>(common.getCode(),common.getMsg(),null);//给前台传对应信息
        }else{//否则
            log.error("系统异常===>");
            e.printStackTrace();
            return new ResultMsg<Object>(CommonEnum.UNKONW_ERROR,null);
        }

    }



}
