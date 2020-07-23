package cn.scholar.common.message.enums;

import cn.scholar.common.message.BaseMsg;

public enum CommonEnum implements BaseMsg {
	SUCCESS(1,"success"),
	PARAMETER_ERROR(2,"参数有误"),
	FILE_IS_NULL(3, "文件上传失败"),
	UNKONW_ERROR(-1, "未知错误"),

	;
	
	private int errCode;
	
	private String errMsg;
	
	private CommonEnum(int errCode , String errMsg) {
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
