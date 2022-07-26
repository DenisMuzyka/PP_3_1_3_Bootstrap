package ru.kata.spring.boot_security.bootstrap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.bootstrap.dao.RoleRepo;
import ru.kata.spring.boot_security.bootstrap.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepo roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Role findById(Long id) {
        return roleRepo.findById(id).get();
    }

    @Override
    public Role findByRole(String role) {
        return findByRole(role);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepo.findAll(Sort.by(Sort.Direction.ASC,"role"));
    }
}
