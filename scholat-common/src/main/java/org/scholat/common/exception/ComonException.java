package org.scholat.common.exception;


public class ComonException extends RuntimeException implements BaseException{

	private BaseException errEnum;
	
	public ComonException(BaseException errEnum) {
		super(errEnum.getMsg());
		this.errEnum = errEnum;		
	}

	public ComonException(BaseException errEnum,String msg) {
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
	public BaseException setMsg(String errMsg) {
		this.errEnum.setMsg(errMsg);
		return this;
	}

	
	
	
}
