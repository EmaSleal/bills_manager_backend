package com.ventas.facturas.controller;


import com.ventas.facturas.model.User;
import com.ventas.facturas.model.UserDto;
import com.ventas.facturas.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/Login")
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final AuthenticationService service;

    @PostMapping
    public ResponseEntity<UserDto> authenticate(
            @RequestBody User request
    ) {
        log.info("authenticate: {}", request);
        return ResponseEntity.ok(service.authenticate(request));
    }

}
