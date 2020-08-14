package com.dys.service;

import com.dys.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static List<User> users = new ArrayList<>();

    public CustomUserDetailsService() {
        IntStream.range(1, 5).forEach( index -> {
            users.add(new User(index, "admin" + index, "password" + index));
        });
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optional = users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }
}
