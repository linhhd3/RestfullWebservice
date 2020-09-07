package com.linhhd.rest.webservice.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundExeption extends RuntimeException {

	public UserNotFoundExeption(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
