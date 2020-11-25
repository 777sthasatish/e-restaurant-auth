package com.cotiviti.erestaurantauth.service.impl;

import com.cotiviti.erestaurantauth.model.CustomUserDetail;
import com.cotiviti.erestaurantauth.model.User;
import com.cotiviti.erestaurantauth.repository.UserRepository;
import com.cotiviti.erestaurantauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Bad credentials"));
        UserDetails userDetails = new CustomUserDetail(user);
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;
    }
}
