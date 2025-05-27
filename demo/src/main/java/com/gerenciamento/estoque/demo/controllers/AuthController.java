package com.gerenciamento.estoque.demo.controllers;


import com.gerenciamento.estoque.demo.services.AuthService;
import com.gerenciamento.estoque.demo.user.dto.LoginDTO;
import com.gerenciamento.estoque.demo.user.dto.LoginResponseDto;
import com.gerenciamento.estoque.demo.user.dto.RegisterDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

        @Autowired
        AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginDTO dto) {
        String tokenjwt = authService.login(dto.email(),  dto.senha());
        return ResponseEntity.ok(new LoginResponseDto(tokenjwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO dto) {
        authService.register(dto.email(), dto.senha());
        return ResponseEntity.ok().body("Registro realizado com sucesso");
    }

}

