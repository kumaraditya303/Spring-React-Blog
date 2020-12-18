package io.github.kumaraditya303.blog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.kumaraditya303.blog.entity.Role;
import io.github.kumaraditya303.blog.entity.User;
import io.github.kumaraditya303.blog.repository.RoleRepository;
import io.github.kumaraditya303.blog.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories
public class BlogApplication {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@PostConstruct
	public void initUsers() {
		List<Role> roles = new ArrayList<>();
		roles.add(new Role().setRole("USER"));
		roleRepository.saveAll(roles);
		List<User> users = Stream.of(new User().setUsername("testinguser")
				.setPassword(passwordEncoder.encode("testinguser")).setRoles(roles)).collect(Collectors.toList());
		userRepository.saveAll(users);
	}
}
