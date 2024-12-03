//package ru.kata.spring.boot_security.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import ru.kata.spring.boot_security.demo.models.User;
//import ru.kata.spring.boot_security.demo.services.RoleService;
//import ru.kata.spring.boot_security.demo.services.UserService;
//
//
//import java.util.logging.Logger;
//
//@Controller
//@RequestMapping(value = "/admin/")
//public class AdminController {
//
//    private final UserService userService;
//
//    private final RoleService roleService;
//
//    private static final Logger LOGGER = Logger.getLogger(AdminController.class.getName());
//
//    @Autowired
//    public AdminController(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }
//
//    @GetMapping
//    public String getListUsers(Model model) {
//        LOGGER.info("Getting list of users");
//        model.addAttribute("list", userService.getListUsers());
//        return "admin/list";
//    }
//
//    @GetMapping(value = "/edit")
//    public String editUser(@RequestParam(value = "id") Long id,
//                           Model model) {
//        LOGGER.info("Editing user with id: " + id);
//        model.addAttribute("roles", roleService.getListRoles());
//        model.addAttribute("user", userService.findUser(id));
//        return "admin/edit";
//    }
//
//    @PostMapping(value = "/update")
//    public String updateUser(@RequestParam(value = "id") Long id, User user) {
//        LOGGER.info("Updating user with id: " + id);
//        userService.updateUser(user, id);
//        return "redirect:/admin/";
//    }
//
//    @GetMapping(value = "/delete")
//    public String deleteUser(@RequestParam("id") Long id) {
//        LOGGER.info("Deleting user with id: " + id);
//        userService.deleteUser(id);
//        return "redirect:/admin/";
//    }
//
//    @GetMapping(value = "/new")
//    public String newUser(@ModelAttribute("user") User user, Model model) {
//        LOGGER.info("Creating a new user");
//        model.addAttribute("roles", roleService.getListRoles());
//        return "admin/new_user";
//    }
//
//    @PostMapping(value = "/save")
//    public String saveUser(@ModelAttribute("user") User user) {
//        LOGGER.info("Saving a new user");
//        String encodedPassword = new BCryptPasswordEncoder(12).encode(user.getPassword());
//        if (!user.getPassword().isBlank()) {
//            user.setPassword(encodedPassword);
//        }
//        userService.saveUser(user);
//        return "redirect:/admin/";
//    }
//}
//
//
package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;


import javax.validation.Valid;
import java.util.logging.Logger;
//
//@Controller
//@RequestMapping(value = "/admin/")
//public class AdminController {
//
//    private final UserService userService;
//
//    private final RoleService roleService;
//
//    private static final Logger LOGGER = Logger.getLogger(AdminController.class.getName());
//
//    @Autowired
//    public AdminController(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }
//
//    @GetMapping
//    public String getListUsers(Model model) {
//        LOGGER.info("Getting list of users");
//        model.addAttribute("list", userService.getListUsers());
//        return "admin/list";
//    }
//
//    @GetMapping(value = "/edit")
//    public String editUser(@RequestParam(value = "id") Long id,
//                           Model model) {
//        LOGGER.info("Editing user with id: " + id);
//        model.addAttribute("roles", roleService.getListRoles());
//        model.addAttribute("user", userService.findUser(id));
//        return "admin/edit";
//    }
//
//    @PostMapping(value = "/update")
//    public String updateUser(@RequestParam(value = "id") Long id,  @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
//        LOGGER.info("Updating user with id: " + id);
//        if (bindingResult.hasErrors()) {
//            return "admin/update/";}
//        userService.updateUser(user, id);
//        return "redirect:/admin/";
//    }
//
//    @GetMapping(value = "/delete")
//    public String deleteUser(@RequestParam("id") Long id) {
//        LOGGER.info("Deleting user with id: " + id);
//        userService.deleteUser(id);
//        return "redirect:/admin/";
//    }
//
//    @GetMapping(value = "/new")
//    public String newUser(@ModelAttribute("user") User user, Model model) {
//        LOGGER.info("Creating a new user");
//        model.addAttribute("user", new User()); // Добавляем новый объект User
//        model.addAttribute("roles", roleService.getListRoles());
//        return "admin/new_user";
//    }
//
//    @PostMapping(value = "/save")
//    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
//        LOGGER.info("Saving a new user");
//        if (bindingResult.hasErrors()) {
//            return "admin/new_user";}
//        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
//        if (!user.getPassword().isBlank()) {
//            user.setPassword(encodedPassword);
//        }
//        userService.saveUser(user);
//        return "redirect:/admin/";
//    }
//}
//


@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    private static final Logger LOGGER = Logger.getLogger(AdminController.class.getName());

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getListUsers(Model model) {
        LOGGER.info("Getting list of users");
        model.addAttribute("list", userService.getListUsers());
        model.addAttribute("user", new User()); // Добавляем пустой объект User
        return "admin/list";
    }

//    @GetMapping(value = "/new")
//    public String newUserForm(Model model) {
//        LOGGER.info("Displaying new user form");
//        model.addAttribute("user", new User()); // Добавляем новый объект User к модели
//        model.addAttribute("roles", roleService.getListRoles()); // Если нужны роли для выбора
//        return "admin/list"; // Возвращаем тот же view как для списка пользователей
//    }

    @GetMapping(value = "/new")
    public String newUserForm(Model model) {
        LOGGER.info("Displaying new user form");
        model.addAttribute("user", new User()); // Добавляем новый объект User к модели
        model.addAttribute("roles", roleService.getListRoles()); // Получаем список ролей
        return "admin/list"; // Возвращаем тот же view как для списка пользователей
    }

    @PostMapping(value = "/save")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        LOGGER.info("Saving new user: " + user.getUsername());
        if (bindingResult.hasErrors()) {
            return "admin/list"; // Верните на тот же view, если есть ошибки
        }
        userService.saveUser(user);
        return "redirect:/admin"; // Перенаправление после успешного сохранения
    }

    @GetMapping(value = "/edit")
    public String editUser(@RequestParam(value = "id") Long id, Model model) {
        LOGGER.info("Editing user with id: " + id);
        model.addAttribute("roles", roleService.getListRoles());
        model.addAttribute("user", userService.findUser(id));
        return "admin/edit";
    }

    @PostMapping(value = "/update")
    public String updateUser(@RequestParam(value = "id") Long id, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        LOGGER.info("Updating user with id: " + id);
        if (bindingResult.hasErrors()) {
            return "admin/edit"; // Вернуть на форму редактирования, если есть ошибки
        }
        userService.updateUser(user, id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        LOGGER.info("Deleting user with id: " + id);
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}