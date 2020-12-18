package io.github.kumaraditya303.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.kumaraditya303.blog.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Boolean existsByUsername(String username);

}
