package com.fundacion_habacuc.sistema_de_gestion.controller;

import com.fundacion_habacuc.sistema_de_gestion.entity.User;
import com.fundacion_habacuc.sistema_de_gestion.repository.UserRepository;
import com.fundacion_habacuc.sistema_de_gestion.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // REGISTRO (Solo Admin)
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')") // Solo ADMIN puede registrar
    public User registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // LOGIN con email y contraseña, devuelve un JWT
    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent() && passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            String token = jwtUtil.generateToken(existingUser.get().getEmail(), existingUser.get().getRole());

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("role", existingUser.get().getRole());
            return response;
        }

        throw new RuntimeException("Credenciales incorrectas");
    }
}
