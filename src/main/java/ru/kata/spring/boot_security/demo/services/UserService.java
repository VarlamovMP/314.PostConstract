package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {

//    User findUserByEmail(String email);
//
//    UserDetails loadUserByUsername(String username);
//
//    void saveUser(User user);
//
//    List<User> getListUsers();
//
//    User findUser(Long id);
//
//    void deleteUser(Long id);
//
//    public void updateUser(User user, Long id);

    List<User> getAllUsers();

    User saveUser(User user);

    void deleteUserById(Long id);

    User updateUserById(Long id, User user);

    User getUserById(Long id);

    User getByEmail(String email);

}
