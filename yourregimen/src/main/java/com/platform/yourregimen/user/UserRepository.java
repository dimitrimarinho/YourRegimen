package com.platform.yourregimen.user;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
	
	public List<UserModel> findAllByFirstNameContainingIgnoreCase(String firstName);
	public List<UserModel> findAllByLastNameContainingIgnoreCase(String lastName);
	public List<UserModel> findAllByUsernameContainingIgnoreCase(String username);

}