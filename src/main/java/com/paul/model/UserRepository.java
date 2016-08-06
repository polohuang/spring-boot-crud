package com.paul.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	//public User findByEmail(String email);
	public User findById(Long id);
}
