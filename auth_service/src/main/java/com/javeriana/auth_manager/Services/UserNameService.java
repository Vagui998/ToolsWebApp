package com.javeriana.auth_manager.Services;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.javeriana.auth_manager.Entities.Role;
import com.javeriana.auth_manager.Entities.User;
import com.javeriana.auth_manager.Repos.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserNameService 
{
    @Autowired
    UserRepository repo;

    public User getByUsername(String pUsername)
    {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> targetEntity = repo.findByUsername(pUsername);
        String role = authenticatedUser.getAuthorities().toArray()[0].toString();
        if (targetEntity.isPresent() && ((role.equals(Role.Admin.name()) || targetEntity.get().getId() == authenticatedUser.getId())))
        { 
            return targetEntity.get();       
        } 
        else 
        {
            return null;
        }
    }


}

