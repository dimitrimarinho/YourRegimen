package com.platform.yourregimen.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platform.yourregimen.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
	
	public List<UserModel> findAllByFirstNameContainingIgnoreCase(String firstName);
	public List<UserModel> findAllByLastNameContainingIgnoreCase(String lastName);
	public List<UserModel> findAllByUsernameContainingIgnoreCase(String username);

}