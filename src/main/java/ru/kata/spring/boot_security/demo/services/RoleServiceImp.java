package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;


import java.util.List;


@Service
public class RoleServiceImp implements RoleService {
//    private final RoleRepository roleRepository;
//
//    public RoleServiceImp(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }
//
////    @Override
////    public List<Role> getListRoles() {
////        return roleRepository.findAll();
////    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Role findRoleByName(String name) {
//        return roleRepository.findRoleByName(name);
//    }
//
////    @Transactional
////    @Override
////    public void saveRole(Role role) {
////        roleRepository.save(role);
////    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<Role> findAll() {
//        return roleRepository.findAll();
//    }

    private final RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    @Transactional(readOnly = true)
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll(){
        return roleRepository.findAll();
    }
}
