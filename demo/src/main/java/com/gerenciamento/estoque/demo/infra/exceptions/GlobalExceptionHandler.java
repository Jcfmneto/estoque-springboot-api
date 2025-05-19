package com.gerenciamento.estoque.demo.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(EstoqueException.class)
        public ResponseEntity<Object> handleEstoqueException(EstoqueException ex) {
            return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
            return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
        }

        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex) {
            return buildResponseEntity(HttpStatus.FORBIDDEN, ex.getMessage());
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
            return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<Object> handleGenericException(Exception ex) {
            return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor");
        }

        private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String message) {
            Map<String, Object> body = new HashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("status", status.value());
            body.put("error", status.getReasonPhrase());
            body.put("message", message);
            return new ResponseEntity<>(body, status);
        }
    }

