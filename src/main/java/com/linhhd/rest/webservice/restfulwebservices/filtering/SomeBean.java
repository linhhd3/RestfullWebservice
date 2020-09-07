package com.linhhd.rest.webservice.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonFilter("SomeBeanFilter")
public class SomeBean {
	private String field1;
	private String field2;
	private String field3;
}
