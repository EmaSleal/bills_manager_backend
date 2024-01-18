package com.ventas.facturas.controller;


import com.ventas.facturas.model.User;
import com.ventas.facturas.model.UserDto;
import com.ventas.facturas.service.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Slf4j
public class AuthenticationController {

    private final AuthenticationService service;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        Optional<List<User>> users = service.getUsers();
        return users.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody User request) {
        var userLogin = service.register(request);
        UserDto userDto = UserDto.builder()
                .userName(userLogin.getUsername())
                .email(userLogin.getEmail())
                .role(userLogin.getRole())
                .build();
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/id")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = service.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}