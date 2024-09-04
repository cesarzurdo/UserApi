package org.demo.users.service;

import org.demo.users.request.UserRequest;
import org.demo.users.response.UserResponse;
import org.demo.users.security.AuthCredentials;

import java.util.List;

public interface UserService {
    List<UserResponse> findAll();
    UserResponse create(UserRequest user);

    UserResponse findUserByToken(String token);

    UserResponse findUserByCredentials(AuthCredentials credentials);
}
