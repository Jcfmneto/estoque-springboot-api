package com.gerenciamento.estoque.demo.services;

import com.gerenciamento.estoque.demo.repositories.UserRepository;
import com.gerenciamento.estoque.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void register(String email, String password) {
        if(repository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email ja cadastrado");
        }
        String encryptedPassword = encoder.encode(password);
        repository.save(new User(email, encryptedPassword));
    }

    public String login(String email, String senha) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, senha);
        var auth = authenticationManager.authenticate(authentication);
        var token = jwtService.generateToken((User) auth.getPrincipal());
        return token;
    }
}
