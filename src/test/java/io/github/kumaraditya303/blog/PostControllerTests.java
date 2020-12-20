package io.github.kumaraditya303.blog;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import io.github.kumaraditya303.blog.entity.User;
import io.github.kumaraditya303.blog.repository.PostRepository;
import io.github.kumaraditya303.blog.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTests {
        @Autowired
        private PostRepository postRepository;
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private PasswordEncoder encoder;
        @Autowired
        private MockMvc mockMvc;
        private String token;
        private String image;

        @BeforeEach
        public void setup() throws Exception {
                postRepository.deleteAll();
                userRepository.deleteAll();
                Map<Object, Object> userDto = new HashMap<>();
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
                this.token = response.get("token").asText();
                MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "test.jpeg",
                                MediaType.IMAGE_JPEG_VALUE, "test data".getBytes());
                MvcResult mvcResult1 = this.mockMvc
                                .perform(multipart("/static").file(mockMultipartFile)
                                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.token)
                                                .contentType(MediaType.MULTIPART_FORM_DATA))
                                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").exists())
                                .andExpect(jsonPath("$.fileName").value("test.jpeg"))
                                .andExpect(jsonPath("$.fileType").value(MediaType.IMAGE_JPEG_VALUE)).andReturn();
                JsonNode response1 = new ObjectMapper().readTree(mvcResult1.getResponse().getContentAsString());
                this.image = response1.get("id").asText();
        }

        @Test
        public void testCreatePost() throws JsonProcessingException, Exception {
                Map<Object, Object> postDto = new HashMap<>();
                postDto.put("title", "");
                postDto.put("overview", "");
                postDto.put("content", "");
                postDto.put("featured", null);
                this.mockMvc.perform(post("/api/post/create").header(HttpHeaders.AUTHORIZATION, "Bearer " + this.token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(postDto)))
                                .andExpect(status().isBadRequest())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.error").exists())
                                .andExpect(jsonPath("$.error.title").value("*Title cannot be empty.*"))
                                .andExpect(jsonPath("$.error.overview").value("*Overview cannot be empty.*"))
                                .andExpect(jsonPath("$.error.content").value("*Content cannot be empty.*"))
                                .andExpect(jsonPath("$.error.featured").value("*Featured cannot be null.*"));
                postDto.put("title", "Test");
                postDto.put("overview", "Test");
                postDto.put("content", "Test");
                postDto.put("featured", true);
                postDto.put("thumbnail", this.image);
                this.mockMvc.perform(post("/api/post/create").header(HttpHeaders.AUTHORIZATION, "Bearer " + this.token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(postDto))).andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(content().string(new ObjectMapper()
                                                .writeValueAsString(postRepository.findAll().get(0))));
        }

        @Test
        public void testUpdatePost() throws Exception {
                Map<Object, Object> postDto = new HashMap<>();

                postDto.put("title", "Test");
                postDto.put("overview", "Test");
                postDto.put("content", "Test");
                postDto.put("featured", true);
                postDto.put("thumbnail", this.image);
                this.mockMvc.perform(post("/api/post/create").header(HttpHeaders.AUTHORIZATION, "Bearer " + this.token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(postDto))).andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(content().string(new ObjectMapper()
                                                .writeValueAsString(postRepository.findAll().get(0))));

                this.mockMvc.perform(
                                put("/api/post/100/update").header(HttpHeaders.AUTHORIZATION, "Bearer " + this.token))
                                .andExpect(status().isBadRequest());
                postDto.put("title", "Test1");
                postDto.put("overview", "Test1");
                postDto.put("content", "Test1");
                postDto.put("featured", false);
                postDto.put("thumbnail", this.image);
                this.mockMvc.perform(put("/api/post/1/update").header(HttpHeaders.AUTHORIZATION, "Bearer " + this.token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(postDto))).andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(content().string(new ObjectMapper()
                                                .writeValueAsString(postRepository.findAll().get(0))));
                System.out.println("Hello");
        }

        @Test
        public void testDeletePost() throws Exception {
                Map<Object, Object> postDto = new HashMap<>();
                postDto.put("title", "Test");
                postDto.put("overview", "Test");
                postDto.put("content", "Test");
                postDto.put("featured", true);
                postDto.put("thumbnail", this.image);
                this.mockMvc.perform(post("/api/post/create").header(HttpHeaders.AUTHORIZATION, "Bearer " + this.token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(postDto))).andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(content().string(new ObjectMapper()
                                                .writeValueAsString(postRepository.findAll().get(0))));
                this.mockMvc.perform(delete("/api/post/100/delete").header(HttpHeaders.AUTHORIZATION,
                                "Bearer " + this.token)).andExpect(status().isNotFound());
                this.mockMvc.perform(delete("/api/post/" + postRepository.findAll().get(0).getId() + "/delete")
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.token)).andExpect(status().isOk());
        }
}
