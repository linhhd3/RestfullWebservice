package com.linhhd.rest.webservice.restfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
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
public class UserJPAResource {
	@Autowired
	private UserDaoService userDaoService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepositry;
	
	@GetMapping("/jpa/users")
	public List<User> recieveAllUser() {
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public Resource<User> recieveUser(@PathVariable(name="id") int id) {
//		User user = userDaoService.findOneUser(id);
		Optional<User> opt = userRepository.findById(id);
		User user = opt.get();
		if (!opt.isPresent()) {
			throw new UserNotFoundExeption("id-" + id);
		}
		Resource<User> resource= new Resource<User>(user);
//		ControllerLinkBuilder.linkTo(controller)
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).recieveAllUser());
		resource.add(linkTo.withRel("all-user"));
		return resource;
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User saveUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(saveUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable(name="id") int id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id) {
		Optional<User> optional = userRepository.findById(id);
		if (!optional.isPresent())
			throw new UserNotFoundExeption("id-" + id);
		return optional.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundExeption("id- " + id);
		}
		User user = optionalUser.get();
		post.setUser(user);
		postRepositry.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}
