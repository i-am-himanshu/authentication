package com.himanshu.authentication.service;

import com.himanshu.authentication.dto.LoginRequest;
import com.himanshu.authentication.dto.LoginResponse;
import com.himanshu.authentication.dto.UserRequest;
import com.himanshu.authentication.dto.UserResponse;
import com.himanshu.authentication.exception.UsernameAlreadyExistException;
import com.himanshu.authentication.model.Role;
import com.himanshu.authentication.model.User;
import com.himanshu.authentication.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;

        this.authenticationManager = authenticationManager;
    }

    private UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setRole(user.getRole());
        userResponse.setUsername(user.getUsername());

        return userResponse;
    }

    public UserResponse registerUser(UserRequest userRequest) {

        if(userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistException("Username already exists");
        }



        User user = new User();

        user.setUsername(userRequest.getUsername());

        String hashedPassword = passwordEncoder.encode(userRequest.getPassword());

        user.setPassword(hashedPassword);
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);

        return mapToUserResponse(savedUser);
    }

    public LoginResponse login(LoginRequest loginRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        return new LoginResponse("Login Successful");
    }

}
