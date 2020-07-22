package com.mortesubita.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptions {

    @ExceptionHandler(CampeonatoNotFound.class)
    public ResponseEntity<?> CampeonatoNotFound(CampeonatoNotFound campeonatoNotFound){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(campeonatoNotFound.getMessage());
    }

    @ExceptionHandler(ClubeJaExisteNotFound.class)
    public ResponseEntity<?> clubeJaExiste(ClubeJaExisteNotFound clubeJaExisteNotFound){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(clubeJaExisteNotFound.getMessage());
    }
}
