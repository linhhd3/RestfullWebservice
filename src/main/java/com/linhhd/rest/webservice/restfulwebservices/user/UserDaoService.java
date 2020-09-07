package com.linhhd.rest.webservice.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<User>();
	private static int userCounter = 5;
	
	static {
		users.add(new User(1, "Ho Linh", new Date()));
		users.add(new User(2, "Nguyen Vi", new Date()));
		users.add(new User(3, "Anh Thi", new Date()));
		users.add(new User(4, "Messi", new Date()));
		users.add(new User(5, "Ronaldo", new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User saveUser(User user) {
		if (user.getId() == null) {
			user.setId(++userCounter);
		}
		users.add(user);
		return user;
	}
	
	public User findOneUser(int id) {
		for(User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
		
	}
	
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
		
	}
}
