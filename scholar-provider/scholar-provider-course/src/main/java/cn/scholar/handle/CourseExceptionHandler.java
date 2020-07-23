package cn.scholar.handle;

import lombok.extern.slf4j.Slf4j;
import cn.scholar.common.RespBean;
import cn.scholar.common.message.enums.CommonEnum;
import cn.scholar.common.message.exception.CommonException;
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
            return new RespBean<Object>(common.getCode(),common.getMsg(),null);//给前台传对应信息
        }else{//否则
            log.error("系统异常===>");
            e.printStackTrace();
            return new RespBean<Object>(CommonEnum.UNKONW_ERROR,null);
        }

    }



}
