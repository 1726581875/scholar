package org.scholat.common.message.exception;


import org.scholat.common.message.BaseMsg;

public class CommonException extends RuntimeException implements BaseMsg {

	private BaseMsg errEnum;
	
	public CommonException(BaseMsg errEnum) {
		super(errEnum.getMsg());
		this.errEnum = errEnum;		
	}

	public CommonException(BaseMsg errEnum, String msg) {
		super(errEnum.getMsg());
		this.errEnum = errEnum;
		this.errEnum.setMsg(msg);
	}
	
	@Override
	public int getCode() {
		
		return this.errEnum.getCode();
	}

	@Override
	public String getMsg() {
		
		return this.errEnum.getMsg();
	}

	@Override
	public BaseMsg setMsg(String errMsg) {
		this.errEnum.setMsg(errMsg);
		return this;
	}

	
	
	
}
