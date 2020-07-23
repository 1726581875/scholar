package cn.scholar.common.message.enums;

import cn.scholar.common.message.BaseMsg;

/**
 * 课程相关枚举类
 * 记录一些，业务异常状态和类型
 */
public enum CourseEnum implements BaseMsg {
    COURSE_NOT_EXIST(2001,"该课程不存在"),
    COURSE_NAMAE_REPEAT(2002,"课程名重复"),
    COURSE_UPDATE_FAIL(2003,"课程更新失败"),
    UNKONW_ERROR(2003, "课程未知错误"),
    ;

    private int errCode;

    private String errMsg;

    private CourseEnum(int errCode , String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getCode() {

        return this.errCode;
    }

    @Override
    public String getMsg() {

        return this.errMsg;
    }

    @Override
    public BaseMsg setMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

}
