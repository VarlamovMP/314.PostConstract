package ru.kata.spring.boot_security.demo.configs;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;


@Component
public class InitMetod {

    private final UserService userService;
    private final RoleService roleService;

    public InitMetod(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @PostConstruct
    public void initData() {
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");
        roleService.saveRole(adminRole);
        roleService.saveRole(userRole);

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);

        User user1 = new User((byte) 30, "admin", "admin", "admin", "admin@mail.ru", userRoles);
        User user2 = new User((byte) 30, "user", "user", "user", "user@mail.ru", userRoles);
        userService.saveUser(user1);
        userService.saveUser(user2);
    }

}
