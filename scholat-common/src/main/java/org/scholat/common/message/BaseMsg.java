package org.scholat.common.message;

public interface BaseMsg {
	public int getCode();
	public String getMsg();
	public BaseMsg setMsg(String errMsg);

}
