package application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import application.dao.UserRepository;
import application.model.CustomUserDetail;
import application.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	 @Autowired
	    private UserRepository userRepository;


	    @Override
	    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
	        Optional<User> optionalUsers = userRepository.findByName(name);

	        optionalUsers
	                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
	        return optionalUsers
	                .map(CustomUserDetail::new).get();
	    }

}
