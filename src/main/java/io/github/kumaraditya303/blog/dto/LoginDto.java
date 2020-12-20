package io.github.kumaraditya303.blog.dto;

import javax.validation.constraints.Size;

public class LoginDto {
    @Size(min = 8, message = "*Username length should not be less than 8.*")
    private String username;
    @Size(min = 8, message = "*Password length should not be less than 8.*")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
