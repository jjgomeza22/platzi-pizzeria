package com.platzi.pizzeria.service;

import com.platzi.pizzeria.persistence.entity.UserEntity;
import com.platzi.pizzeria.persistence.repository.UserRepository;
import com.platzi.pizzeria.service.enums.RolesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + "not found."));
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(RolesEnum.ADMIN.getValue())
                .accountLocked(user.getLocked())
                .disabled(user.getDisabled())
                .build();
    }
}
