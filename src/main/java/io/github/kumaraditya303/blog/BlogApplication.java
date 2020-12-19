package io.github.kumaraditya303.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BlogApplication {
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	// @PostConstruct
	// public void initUsers() {
	// 	List<Role> roles = new ArrayList<>();
	// 	roles.add(new Role().setRole("USER"));
	// 	roleRepository.saveAll(roles);
	// 	List<User> users = Stream
	// 			.of(new User().setUsername("testinguser").setEmail("test@test.com")
	// 					.setPassword(passwordEncoder.encode("testinguser")).setRoles(roles))
	// 			.collect(Collectors.toList());
	// 	userRepository.saveAll(users);
	// }
}
