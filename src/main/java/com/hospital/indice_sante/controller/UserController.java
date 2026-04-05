package com.hospital.indice_sante.controller;

import com.hospital.indice_sante.dto.UserResponseDTO;
import com.hospital.indice_sante.model.User;
import com.hospital.indice_sante.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Récupération de tous les mails des utilisateurs")
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(user -> new UserResponseDTO(user.getEmail()))
                .toList();
    }

    // GET user by id
    @Operation(summary = "Récupération du mail d'un utilisateur par son id")
    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new UserResponseDTO(user.getEmail());
    }
}
