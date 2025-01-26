package com.example.RisProyect.utils;
import com.example.RisProyect.role.model.Role;
import com.example.RisProyect.role.model.RoleRepository;
import com.example.RisProyect.user.model.User;
import com.example.RisProyect.user.model.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {


            Optional<Role> optionalRole = roleRepository.findByName("ROLE_TOWN_ACCESS");
            if (!optionalRole.isPresent()) {
                Role roleTown = new Role("ROLE_TOWN_ACCESS");
                roleRepository.saveAndFlush(roleTown);

                Optional<User> optionalUser = userRepository.findByUsername("townUser");
                if (!optionalUser.isPresent()) {
                    User userTown = new User("townUser", passwordEncoder.encode("password123"));
                    userTown.getRoles().add(roleTown);
                    userRepository.saveAndFlush(userTown);
                }
            }

            optionalRole = roleRepository.findByName("ROLE_STATE_ACCESS");
            if (!optionalRole.isPresent()) {
                Role roleState = new Role("ROLE_STATE_ACCESS");
                roleRepository.saveAndFlush(roleState);

                Optional<User> optionalUser = userRepository.findByUsername("stateUser");
                if (!optionalUser.isPresent()) {
                    User userState = new User("stateUser", passwordEncoder.encode("password123"));
                    userState.getRoles().add(roleState);
                    userRepository.saveAndFlush(userState);
                }
            }

        };
    }
}

