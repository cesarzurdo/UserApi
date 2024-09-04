package org.demo.users.service;

import org.demo.users.mapper.UserToUserResponseMapper;
import org.demo.users.model.User;
import org.demo.users.repository.UserRepository;
import org.demo.users.request.UserRequest;
import org.demo.users.response.UserResponse;
import org.demo.users.security.AuthCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserToUserResponseMapper responseMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserRequest userRequest;
    private UserResponse userResponse;
    private AuthCredentials authCredentials;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("test@example.com");
        user.setName("Test User");
        user.setPassword("password");
        // Add additional user setup as needed

        userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setName("Test User");
        userRequest.setPassword("password");
        // Add additional userRequest setup as needed

        userResponse = new UserResponse();
        userResponse.setEmail("test@example.com");
        userResponse.setName("Test User");
        // Add additional userResponse setup as needed

        authCredentials = new AuthCredentials();
        authCredentials.setEmail("test@example.com");
        authCredentials.setPassword("password");
    }

    @Test
    void findUserByCredentials_shouldReturnUserResponse_whenCredentialsAreValid() {
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(repository.findOneByEmailAndPassword(anyString(), anyString())).thenReturn(Optional.of(user));
        when(responseMapper.convertToResponse(any(User.class))).thenReturn(userResponse);

        UserResponse result = userService.findUserByCredentials(authCredentials);

        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getName(), result.getName());

        verify(passwordEncoder, times(1)).encode(anyString());
        verify(repository, times(1)).findOneByEmailAndPassword(anyString(), anyString());
        verify(responseMapper, times(1)).convertToResponse(any(User.class));
    }

    @Test
    void findUserByCredentials_shouldThrowException_whenCredentialsAreInvalid() {
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(repository.findOneByEmailAndPassword(anyString(), anyString())).thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () ->
            userService.findUserByCredentials(authCredentials)
        );

        assertEquals("User with email test@example.com doesn't exist.", exception.getMessage());

        verify(passwordEncoder, times(1)).encode(anyString());
        verify(repository, times(1)).findOneByEmailAndPassword(anyString(), anyString());
        verify(responseMapper, never()).convertToResponse(any(User.class));
    }
}