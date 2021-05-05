package com.baratieri.dslearn.services;

import com.baratieri.dslearn.dto.UserDTO;
import com.baratieri.dslearn.entities.User;
import com.baratieri.dslearn.repositories.UserRepository;
import com.baratieri.dslearn.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public UserDTO finById(Long id) {
        authService.validateSelforAdmin(id);
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new UserDTO(entity);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username);
        if(user == null){
            logger.error("Email not found" + username);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("User found" + username);
        return user;
    }

}
