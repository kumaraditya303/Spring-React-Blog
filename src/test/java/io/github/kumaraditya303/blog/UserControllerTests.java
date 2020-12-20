package io.github.kumaraditya303.blog;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import io.github.kumaraditya303.blog.entity.User;
import io.github.kumaraditya303.blog.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

        @Autowired
        private MockMvc mockMvc;
        @Autowired
        private PasswordEncoder encoder;
        @Autowired
        private UserRepository userRepository;
        private Map<Object, Object> userDto = new HashMap<>();

        @BeforeEach
        @AfterEach
        public void setup() {
                userRepository.deleteAll();
        }

        @Test
        public void testLoginUser() throws Exception {
                this.mockMvc.perform(get("/api/login")).andExpect(status().isForbidden());
                this.mockMvc.perform(post("/api/login").contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isBadRequest());
                userDto.put("username", "test");
                userDto.put("password", "test");
                this.mockMvc.perform(post("/api/login").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(userDto)))
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.error").exists())
                                .andExpect(jsonPath("$.error.username")
                                                .value("*Username length should not be less than 8.*"))
                                .andExpect(jsonPath("$.error.password")
                                                .value("*Password length should not be less than 8.*"))
                                .andExpect(jsonPath("$.token").doesNotExist());
                User user = new User();
                user.setUsername("testinguser");
                user.setPassword(encoder.encode("testinguser"));
                user.setEmail("test@test.com");
                userRepository.save(user);
                userDto.put("username", "testinguser");
                userDto.put("password", "testinguser");
                this.mockMvc.perform(post("/api/login").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(userDto))).andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.token").exists()).andExpect(jsonPath("$.error").doesNotExist());

        }

        @Test
        public void testRegisterUser() throws Exception {
                this.mockMvc.perform(get("/api/register")).andExpect(status().isForbidden());
                this.mockMvc.perform(post("/api/register").contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isBadRequest());
                userDto.put("username", "test");
                userDto.put("email", "testest.com");
                userDto.put("firstName", "test");
                userDto.put("lastName", "user");
                userDto.put("password", "test");
                this.mockMvc.perform(post("/api/register").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(userDto)))
                                .andExpect(status().isBadRequest())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.error").exists())
                                .andExpect(jsonPath("$.error.username")
                                                .value("*Username length should not be less than 8.*"))
                                .andExpect(jsonPath("$.error.email").value("*Email should be valid.*"))
                                .andExpect(jsonPath("$.error.password")
                                                .value("*Password length should not be less than 8.*"))
                                .andExpect(jsonPath("$.token").doesNotExist());
                userDto.put("username", "testinguser");
                userDto.put("email", "test@test.com");
                userDto.put("firstName", "test@test.com");
                userDto.put("lastName", "user");
                userDto.put("password", "testinguser");
                this.mockMvc.perform(post("/api/register").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(userDto))).andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.token").exists()).andExpect(jsonPath("$.error").doesNotExist());
        }

        @Test
        public void testCurrentUser() throws Exception {
                User user = new User();
                user.setUsername("testinguser");
                user.setPassword(encoder.encode("testinguser"));
                user.setEmail("test@test.com");
                userRepository.save(user);
                userDto.put("username", "testinguser");
                userDto.put("password", "testinguser");
                MvcResult mvcResult = this.mockMvc
                                .perform(post("/api/login").contentType(MediaType.APPLICATION_JSON)
                                                .content(new ObjectMapper().writeValueAsString(userDto)))
                                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.token").exists()).andExpect(jsonPath("$.error").doesNotExist())
                                .andReturn();
                JsonNode response = new ObjectMapper().readTree(mvcResult.getResponse().getContentAsString());
                String token = response.get("token").asText();
                this.mockMvc.perform(get("/api/user")).andExpect(status().isForbidden());
                this.mockMvc.perform(get("/api/user").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(content().string(new ObjectMapper()
                                                .writeValueAsString(userRepository.findByUsername("testinguser"))));
        }
}
