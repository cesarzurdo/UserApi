package org.demo.users.service;

import org.demo.users.mapper.UserRequestToUserMapper;
import org.demo.users.mapper.UserToUserResponseMapper;
import org.demo.users.model.User;
import org.demo.users.repository.UserRepository;
import org.demo.users.request.UserRequest;
import org.demo.users.response.UserResponse;
import org.demo.users.security.AuthCredentials;
import org.demo.users.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserRequestToUserMapper userRequestToUserMapper;

    @Autowired
    private UserToUserResponseMapper responseMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<UserResponse> findAll() {
        List<User> response = repository.findAll();
        if (!CollectionUtils.isEmpty(response)) {
            return repository.findAll().stream()
                    .map(u -> responseMapper.convertToResponse(u))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public UserResponse create(UserRequest ur) {
        User newUser = userRequestToUserMapper.convertRequestToEntity(ur);
        newUser.getPhones().forEach(phone -> phone.setUser(newUser));
        newUser.setPassword(passwordEncoder.encode(ur.getPassword()));
        User userSaved = repository.save(newUser);
        userSaved.setToken(TokenUtils.createToken(userSaved.getName(), userSaved.getEmail()));
        return responseMapper.convertToResponse(userSaved);
    }

    @Override
    public UserResponse findUserByToken(String token) {
        String email = (String) TokenUtils.getAuthentication(token.replace("Bearer ", "")).getPrincipal();
        return repository.findOneByEmail(email)
                .map(responseMapper::convertToResponse)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " doesn't exist."));
    }

    @Override
    public UserResponse findUserByCredentials(AuthCredentials credentials) {
        return repository.findOneByEmailAndPassword(credentials.getEmail(), passwordEncoder.encode(credentials.getPassword()))
                .map(responseMapper::convertToResponse)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + credentials.getEmail() + " doesn't exist."));
    }

}
