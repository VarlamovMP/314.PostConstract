package ru.kata.spring.boot_security.demo.services;

import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service

public class UserServiceImp implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email);
    }

    @Query("Select u from User u left join fetch u.roles")
    public List<User> getListUsers() {
        return userRepository.findAll();
    }

    public User findUser(Long id) {
        return userRepository.getById(id);
    }

    private boolean validUser(User user) {
        return !user.getName().isBlank() &&
                !user.getLastname().isBlank() &&
                !user.getEmail().isBlank() &&
                !user.getPassword().isBlank() &&
                user.getAge() != 0;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        if (validUser(user)) {
            Optional<User> existingUser = Optional.ofNullable(findUserByEmail(user.getEmail()));
            if (existingUser.isEmpty()) {
                userRepository.save(user);
            }
        }
    }

    @Override
    @Transactional
    public void updateUser(User user, Long id) {
        User updateUser = findUser(id);
        if (user.getPassword().isBlank()) {
            user.setPassword(updateUser.getPassword());
        } else {
            String encodedPassword = new BCryptPasswordEncoder(12).encode(user.getPassword());
            user.setPassword(encodedPassword);
        }
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepository.findUserByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.delete(findUser(id));
    }
}

