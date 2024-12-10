package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImp implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private boolean validUser(User user) {
        return !user.getFirstName().isBlank() &&
                !user.getLastName().isBlank() &&
                !user.getEmail().isBlank() &&
                !user.getPassword().isBlank() &&
                user.getAge() > 0;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        userRepository.delete(getUserById(id));
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        if (validUser(user)) {
            if (getByEmail(user.getEmail()) == null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                return userRepository.save(user);
            } else {
                throw new EmailAlreadyExistsException("Email занят");
            }
        }
        throw new IllegalArgumentException("Invalid user data");
    }

    @Transactional
    @Override
    public User updateUserById(Long id, User user) {
        User existingUser = getUserById(id);
        User anotherUser = getByEmail(user.getEmail());
        if (anotherUser != null && !anotherUser.getId().equals(existingUser.getId())) {
            throw new EmailAlreadyExistsException("Email занят");
        }
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setAge(user.getAge());
        if (!user.getPassword().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return saveUser(existingUser);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getAuthorities());
    }

    public static class EmailAlreadyExistsException extends RuntimeException {
        public EmailAlreadyExistsException(String message) {
            super(message);
        }
    }
}
