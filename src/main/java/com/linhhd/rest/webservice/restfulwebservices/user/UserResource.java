package com.linhhd.rest.webservice.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.concurrent.LinkedTransferQueue;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	@Autowired
	private UserDaoService userDaoService;
	
	@GetMapping("/users")
	public List<User> recieveAllUser() {
		return userDaoService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public Resource<User> recieveUser(@PathVariable(name="id") int id) {
		User user = userDaoService.findOneUser(id);
		if (user == null) {
			throw new UserNotFoundExeption("id-" + id);
		}
		Resource<User> resource= new Resource<User>(user);
//		ControllerLinkBuilder.linkTo(controller)
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).recieveAllUser());
		resource.add(linkTo.withRel("all-user"));
		return resource;
	}
	
	/**
	 * input detail of user
	 * @param user
	 * @return
	 */
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User saveUser = userDaoService.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(saveUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable(name="id") int id) {
		User user = userDaoService.deleteById(id);
		if (user == null) {
			throw new UserNotFoundExeption("id-" + id);
		}
	}

}
