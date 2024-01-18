package com.ventas.facturas.controller;


import com.ventas.facturas.model.AuthenticationResponse;
import com.ventas.facturas.model.User;
import com.ventas.facturas.service.AuthenticationService;
import com.ventas.facturas.service.impl.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.Optional;

@RestController
@RequiredArgsConstructor

@RequestMapping("/forgot-password")
public class ForgotPasswordController {

    private final AuthenticationService service;
    @Autowired
    private final EmailService emailService;

    @GetMapping("/email")
    public RedirectView RedirecionarConToken(@RequestParam("token") String token, Model model) {
        // Validar el token y redirigir al usuario a la página de restablecimiento de contraseña

        //envia el token a la direccion localhost:4200/reset-password?token=tokenDecode
        model.addAttribute("token", token);//envia el token a la vista
        return new RedirectView("http://localhost:4200/Login?token=" + token);
    }

    @PostMapping
    public ResponseEntity<Date> forgotPassword(@RequestBody User request) {
        Optional<AuthenticationResponse> response = service.TokenforgotPassword(request.getEmail());
        if (response.isPresent()) {
            var fecha = emailService.sendForgotPasswordEmail(request.getEmail(), response.get().getToken());
            return ResponseEntity.ok(fecha);
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping("/reset-password")
    public ResponseEntity<AuthenticationResponse> resetPassword(@RequestBody User request) {
        Optional<AuthenticationResponse> response = service.resetPassword(request);
        if (response.isPresent()) {
            return ResponseEntity.ok(response.get());
        }
        return ResponseEntity.notFound().build();

    }

}
