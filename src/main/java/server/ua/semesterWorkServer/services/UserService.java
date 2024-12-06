package server.ua.semesterWorkServer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import server.ua.semesterWorkServer.entities.User;
import server.ua.semesterWorkServer.repos.UserRepo;
import server.ua.semesterWorkServer.security.Roles;
import server.ua.semesterWorkServer.security.UserRole;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        user.setRoles(new ArrayList<>());
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(Roles.USER);
        user.getRoles().add(userRole);
        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );
        return userRepo.save(user);
    }

    public User getUser(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User wasn't found!"));
    }

    public User deleteUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " wasn't found!"));
        userRepo.delete(user);
        return user;
    }

    public User putUser(Long id, User updatedUser) {
        User existingUser = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User wasn't found!"));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());

        return userRepo.save(existingUser);
    }

    public List<User> getUsers() {

        return userRepo.findAll();
    }
}

