package com.himanshu.authentication.service;

import com.himanshu.authentication.dto.UserRequest;
import com.himanshu.authentication.dto.UserResponse;
import com.himanshu.authentication.model.Role;
import com.himanshu.authentication.model.User;
import com.himanshu.authentication.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
    }

    private UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setRole(user.getRole());
        userResponse.setUsername(user.getUsername());

        return userResponse;
    }

    public UserResponse registerUser(UserRequest userRequest) {

        User user = new User();

        user.setUsername(userRequest.getUsername());

        String hashedPassword = passwordEncoder.encode(userRequest.getPassword());

        user.setPassword(hashedPassword);
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);

        return mapToUserResponse(savedUser);
    }

}
