package com.back.snail_be.service;

import com.back.snail_be.entity.User;
import com.back.snail_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(String email, String password){
        Optional<User> userOpt = userRepository.findByEmail(email);
        if(userOpt.isPresent() && passwordEncoder.matches(password,userOpt.get().getPassword())){
            return "JWT-TOKEN success";
        }
        throw new RuntimeException("Invalid Credentials");
    }
}
