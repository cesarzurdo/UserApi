package org.demo.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.demo.users.response.UserResponse;
import org.demo.users.security.AuthCredentials;
import org.demo.users.security.WebSecurityConfig;
import org.demo.users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(WebSecurityConfig.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private AuthCredentials authCredentials;
    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        authCredentials = new AuthCredentials();
        authCredentials.setEmail("test@example.com");
        authCredentials.setPassword("password");

        userResponse = new UserResponse();
        userResponse.setEmail("test@example.com");
        userResponse.setName("Test User");
    }

    @Test
    void getUser_shouldReturnUserResponse_whenCredentialsAreValid() throws Exception {
        when(userService.findUserByCredentials(any(AuthCredentials.class))).thenReturn(userResponse);

        /*MockHttpServletRequestBuilder request = post("/users/login")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)*/
        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(authCredentials)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("test@example.com")));
    }

    @Test
    void getUser_shouldReturnBadRequest_whenCredentialsAreInvalid() throws Exception {
        authCredentials.setEmail("");  // Invalid email
        authCredentials.setPassword("");  // Invalid password

        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(authCredentials)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("email must not be blank")))
                .andExpect(content().string(containsString("password must not be blank")));
    }
}
