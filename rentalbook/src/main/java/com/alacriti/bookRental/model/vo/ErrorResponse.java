package com.alacriti.bookRental.model.vo;

import java.util.List;

public class ErrorResponse {
	private List<Error> errors = null;

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

}
