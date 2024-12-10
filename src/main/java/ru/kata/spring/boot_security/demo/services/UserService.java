package ru.kata.spring.boot_security.demo.services;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User saveUser(User user);

    void deleteUserById(Long id);

    User updateUserById(Long id, User user);

    User getUserById(Long id);

    User getByEmail(String email);

}
