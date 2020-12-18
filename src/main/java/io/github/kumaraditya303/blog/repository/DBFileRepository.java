package io.github.kumaraditya303.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.kumaraditya303.blog.entity.DBFile;

public interface DBFileRepository extends JpaRepository<DBFile, String> {

}
