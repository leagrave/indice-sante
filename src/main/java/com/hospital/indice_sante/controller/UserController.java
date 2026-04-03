package com.hospital.indice_sante.controller;

import com.hospital.indice_sante.dto.UserResponseDTO;
import com.hospital.indice_sante.model.User;
import com.hospital.indice_sante.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET all users
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(user -> new UserResponseDTO(user.getEmail()))
                .toList();
    }

    // GET user by id
    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new UserResponseDTO(user.getEmail());
    }
}
