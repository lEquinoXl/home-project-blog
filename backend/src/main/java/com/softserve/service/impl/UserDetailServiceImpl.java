package com.softserve.service.impl;

import com.softserve.model.User;
import com.softserve.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByName(username).orElseThrow(
                () -> new UsernameNotFoundException("User with username " + username + " does not exist")
        );

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add( new SimpleGrantedAuthority(user.getRole().getName().toUpperCase(Locale.ROOT)));
        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(), grantedAuthorities);
    }
}
