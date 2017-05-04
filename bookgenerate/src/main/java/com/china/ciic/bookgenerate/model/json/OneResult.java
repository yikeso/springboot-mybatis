package com.china.ciic.bookgenerate.model.json;

public class OneResult<T> extends BaseResponse {
	private T one;

	public OneResult() {

	}

	public T getOne() {
		return one;
	}

	public void setOne(T one) {
		this.one = one;
	}

}
