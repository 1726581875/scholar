package org.scholat.common.exception;

public enum CommonEnum implements BaseException{
	SUCCESS(1,"success"),
	PARAMETER_ERROR(2,"参数有误"),
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
	public BaseException setMsg(String errMsg) {
		this.errMsg = errMsg;
		return this;
	}

}
