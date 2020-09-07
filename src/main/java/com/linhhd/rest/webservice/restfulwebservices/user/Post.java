package com.linhhd.rest.webservice.restfulwebservices.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Post {
	@Id
	@GeneratedValue
	private Integer id;
	private String description;
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;
	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}

	
}
