package io.github.kumaraditya303.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.kumaraditya303.blog.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
