package org.scholat.common.exception;

public interface BaseException {

	public int getCode();
	public String getMsg();
	public BaseException setMsg(String errMsg);
	
}
