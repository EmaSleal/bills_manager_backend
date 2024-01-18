package com.ventas.facturas.service;



import com.ventas.facturas.model.AuthenticationResponse;
import com.ventas.facturas.model.User;
import com.ventas.facturas.model.UserDto;

import java.util.List;
import java.util.Optional;

public interface AuthenticationService {
    User register(User request);

    UserDto authenticate(User request);

    Optional<AuthenticationResponse> TokenforgotPassword(String email);

    Optional<AuthenticationResponse> resetPassword(User request);

    Optional<List<User>> getUsers();

    Optional<User> getUserById(Long id);
}
