package com.alacriti.bookRental.model.vo;

public class BookRentalResponse<T> {
	private T responseVo;
	private int statusCode;
	private ErrorResponse errorResponse;

	public T getResponseVo() {
		return responseVo;
	}

	public void setResponseVo(T responseVo) {
		this.responseVo = responseVo;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}

}
