package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleRepo;
import ru.kata.spring.boot_security.demo.dao.UserRepo;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Autowired
    public UserDetailsServiceImpl(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Transactional
    public void addUser(User user) {
        userRepo.save(user);
    }

    @Transactional
    public void updateUser(User user) {
        userRepo.save(user);
    }


    @Transactional
    public void removeUser(Long id) {
        userRepo.deleteById(id);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Transactional
    public User findUserById(long id) {
        return userRepo.findById(id).get();
    }

    @Transactional
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        user.setRoles(roleRepo.findAllByUsersId(user.getId()));
        return user;
    }
}
