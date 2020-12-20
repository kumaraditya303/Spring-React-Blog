package io.github.kumaraditya303.blog;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

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
import io.github.kumaraditya303.blog.repository.DBFileRepository;
import io.github.kumaraditya303.blog.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class StaticControllerTests {
        @Autowired
        private MockMvc mockMvc;
        @Autowired
        private PasswordEncoder encoder;
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private DBFileRepository dbFileRepository;
        private String token;

        @BeforeEach
        public void setup() throws Exception {
                userRepository.deleteAll();
                dbFileRepository.deleteAll();
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
        }

        @Test
        public void testSaveStatic() throws Exception {
                MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "test.jpeg",
                                MediaType.IMAGE_JPEG_VALUE, "test data".getBytes());
                this.mockMvc.perform(multipart("/static").file(mockMultipartFile)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.token)
                                .contentType(MediaType.MULTIPART_FORM_DATA)).andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(content().string(new ObjectMapper()
                                                .writeValueAsString(dbFileRepository.findAll().get(0))));
        }

        @Test
        public void testGetStatic() throws Exception {
                MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "test.jpeg",
                                MediaType.IMAGE_JPEG_VALUE, "test data".getBytes());
                MvcResult mvcResult = this.mockMvc
                                .perform(multipart("/static").file(mockMultipartFile)
                                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.token)
                                                .contentType(MediaType.MULTIPART_FORM_DATA))
                                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").exists())
                                .andExpect(jsonPath("$.fileName").value("test.jpeg"))
                                .andExpect(jsonPath("$.fileType").value(MediaType.IMAGE_JPEG_VALUE)).andReturn();
                JsonNode response = new ObjectMapper().readTree(mvcResult.getResponse().getContentAsString());
                String fileId = response.get("id").asText();
                this.mockMvc.perform(get("/static/" + fileId).header(HttpHeaders.AUTHORIZATION, "Bearer " + this.token))
                                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.IMAGE_JPEG_VALUE))
                                .andExpect(content()
                                                .string(new String(dbFileRepository.findById(fileId).get().getFile())));
        }
}