package com.back.snail_be.config;

import com.back.snail_be.entity.User;
import com.back.snail_be.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            userRepository.deleteAll();

            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("12345678"));

            User test = new User();
            test.setEmail("test@gmail.com");
            test.setPassword(passwordEncoder.encode("12345678"));

            userRepository.save(admin);
            userRepository.save(test);

            System.out.println("Test data inserted successfully!");
        };
    }
}
