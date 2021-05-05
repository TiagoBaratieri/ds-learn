package com.baratieri.dslearn.services;

import com.baratieri.dslearn.entities.User;
import com.baratieri.dslearn.repositories.UserRepository;
import com.baratieri.dslearn.services.exceptions.ForbiddenException;
import com.baratieri.dslearn.services.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public User authenticated(){
        try{
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return userRepository.findByEmail(username);
        }catch (Exception e){
            throw new UnauthorizedException("Invalid user");
        }
    }

    public void validateSelforAdmin(Long userId){
        User user = authenticated();
        if(!user.getId().equals(userId) && !user.hasHole("ROLE_ADMIN")){
            throw new ForbiddenException("Acess denied");
        }
    }
}
