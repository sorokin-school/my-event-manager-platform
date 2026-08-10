package dev.sorokin.eventmanager.user.serivce;

import dev.sorokin.eventmanager.user.dto.UserRegistrationRequest;
import dev.sorokin.eventmanager.user.entity.User;
import dev.sorokin.eventmanager.user.exceptions.UserLoginAlreadyExistsException;
import dev.sorokin.eventmanager.user.mapper.UserMapper;
import dev.sorokin.eventmanager.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public User createUser(User userRequest) {
        if (userRepository.existsByLogin(userRequest.login())) {
            throw new UserLoginAlreadyExistsException("User login %s is already exists".formatted(userRequest.login()));
        }

        var userEntity = userMapper.toEntity(userRequest);

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        userRepository.save(userEntity);

        return userMapper.toDomainFromEntity(userEntity);
    }
}
