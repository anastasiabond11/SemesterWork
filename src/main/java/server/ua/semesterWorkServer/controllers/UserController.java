package server.ua.semesterWorkServer.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.ua.semesterWorkServer.entities.User;
import server.ua.semesterWorkServer.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin (origins = "*")
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser = userService.createUser(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdUser);
    }

    @CrossOrigin (origins = "*")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id){
        User user = userService.getUser(id);
        return ResponseEntity
                .status(200)
                .body(user);
    }

    @CrossOrigin (origins = "*")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getUsers();
        return ResponseEntity
                .status(200)
                .body(users);

    }

    @CrossOrigin (origins = "*")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("User with ID " + id + " was deleted successfully.");
    }

    @CrossOrigin (origins = "*")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<User> putUser(@PathVariable("id") Long id,
                                        @RequestBody User updatedUser){
        User user = userService.putUser(id, updatedUser);
        return ResponseEntity
                .status(200)
                .body(user);

    }



}
