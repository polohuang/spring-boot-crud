package com.paul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paul.model.User;
import com.paul.model.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository repo;
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("user", repo.findAll());
		return "index";
	}
	
	@RequestMapping("/user")
	@ResponseBody
	public String user() {
		return "user";
	}
	
	//listing all users
	@RequestMapping("/user/list")
	public String allUser(Model model) {
		model.addAttribute("user", repo.findAll());
		return "list";
	}

	//listing a user by email
	//@RequestMapping("/user/{email}")
	//public User findUser(@PathVariable String email) {
	//	return repo.findByEmail(email);
	//}

	//listing a user by id
	@RequestMapping("/user/{id}")
	public User findUser(@PathVariable Long id) {
		return repo.findById(id);
	}
	
	@RequestMapping("/user/create")
	public String create() {
		return "create";
	}
	
	//creating a user
	@RequestMapping(value = "/user/create/new", method = RequestMethod.POST)
	public String newUser(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email) {
		User user = new User(name, email);
		repo.save(user);
		return "redirect:../";
	}

	@RequestMapping("/user/delete")
	public String delete() {
		return "delete";
	}
	
	//delete a user by id
	@RequestMapping("/user/delete/{id}")
	public String deleteUser(@RequestParam(value = "id") Long id) {
		repo.delete(id);
		return "redirect:../";
	}

	@RequestMapping("/user/update")
	public String update() {
		return "update";
	}
	
	//update a user
	@RequestMapping("/user/update/old")
	public String update(@RequestParam(value = "id") Long id, @RequestParam(value = "name") String name, @RequestParam(value = "email") String email) {
		User user = repo.findById(id);
		user.setName(name);
		user.setEmail(email);
		repo.save(user);
		return "redirect:../";
	}
}
