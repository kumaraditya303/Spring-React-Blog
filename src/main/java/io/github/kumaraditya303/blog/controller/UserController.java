package io.github.kumaraditya303.blog.controller;

import java.util.Collections;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.kumaraditya303.blog.dto.LoginDto;
import io.github.kumaraditya303.blog.entity.Role;
import io.github.kumaraditya303.blog.entity.User;
import io.github.kumaraditya303.blog.repository.RoleRepository;
import io.github.kumaraditya303.blog.repository.UserRepository;
import io.github.kumaraditya303.blog.util.ApiResponse;
import io.github.kumaraditya303.blog.util.JwtUtil;

@RestController
public class UserController {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserRepository userRepository,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/user")
    public User getUser(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> generateToken(@Valid @RequestBody LoginDto loginDto, BindingResult result) {
        var response = new ApiResponse();
        if (result.hasErrors()) {
            response.setError(result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, fieldError -> fieldError.getDefaultMessage())));
            return new ResponseEntity<>(response.getData(), HttpStatus.BAD_REQUEST);
        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            response.setData("token", jwtUtil.generateToken(loginDto.getUsername()));
            return new ResponseEntity<>(response.getData(), HttpStatus.OK);
        } catch (AuthenticationException e) {
            response.setError("username", "*Invalid Username or Password*");
        }
        return new ResponseEntity<>(response.getData(), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody User user, BindingResult result) {
        var response = new ApiResponse();
        if (result.hasErrors()) {
            response.setError(result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, fieldError -> fieldError.getDefaultMessage())));
            return new ResponseEntity<>(response.getData(), HttpStatus.BAD_REQUEST);
        }
        if (!userRepository.existsByUsername(user.getUsername())) {
            var roles = Collections.singletonList(new Role().setRole("ROLE_USER"));
            roleRepository.saveAll(roles);
            user.setPassword(passwordEncoder.encode(user.getPassword())).setRoles(roles);
            userRepository.save(user);
            response.setData("token", jwtUtil.generateToken(user.getUsername()));
            return new ResponseEntity<>(response.getData(), HttpStatus.CREATED);
        }
        response.setError("username", "User with username " + user.getUsername() + " already exists.");
        return new ResponseEntity<>(response.getData(), HttpStatus.BAD_REQUEST);

    }
}
