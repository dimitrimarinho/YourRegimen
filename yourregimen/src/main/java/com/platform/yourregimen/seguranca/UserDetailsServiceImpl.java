package com.platform.yourregimen.seguranca;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.platform.yourregimen.model.Admin;
import com.platform.yourregimen.repository.AdminRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private AdminRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Admin> admin = repository.findByUsuario(username);
		admin.orElseThrow(() -> new UsernameNotFoundException(username+"Not found!"));
		
		return admin.map(UserDetailsImpl::new).get();
	}
}