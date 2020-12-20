package io.github.kumaraditya303.blog;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.kumaraditya303.blog.entity.User;
import io.github.kumaraditya303.blog.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories
public class BlogApplication {
    public BlogApplication(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initUsers() {
        User user = new User();
        user.setUsername("testinguser");
        user.setEmail("test@test.com");
        user.setPassword(passwordEncoder.encode("testinguser"));
        userRepository.save(user);
    }
}
