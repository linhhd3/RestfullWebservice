package com.linhhd.rest.webservice.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="All details about user.")
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	@Size(min=2, message="name should have least 2 characters")
	@ApiModelProperty(notes="Name should be have least 2 characters.")
	private String name;
	@Past
	@ApiModelProperty(notes="birthdate should be in the past.")
	private Date birthDate;
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	protected User() {} 
	
	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
	

}
