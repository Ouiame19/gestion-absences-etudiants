package com.backend.gestion_absences.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> gererRuntimeException(RuntimeException ex) {
        Map<String, String> erreur = new HashMap<>();
        erreur.put("erreur", ex.getMessage());
        erreur.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erreur);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> gererValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> erreurs = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
            .forEach(error -> erreurs.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erreurs);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> gererException(Exception ex) {
        Map<String, String> erreur = new HashMap<>();
        erreur.put("erreur", "Erreur interne du serveur");
        erreur.put("details", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erreur);
    }
}
