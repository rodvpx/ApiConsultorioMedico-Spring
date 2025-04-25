package com.github.rodvpx.apiconsultoriomedicospring.controller;

import com.github.rodvpx.apiconsultoriomedicospring.dto.input.ExameMedicoInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.ExameMedicoOutput;
import com.github.rodvpx.apiconsultoriomedicospring.service.ExameMedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/exame-medico")
public class ExameMedicoController {

    private final ExameMedicoService exameMedicoService;

    @Autowired
    public ExameMedicoController(ExameMedicoService exameMedicoService) {
        this.exameMedicoService = exameMedicoService;
    }

    // Endpoint para adicionar um exame médico
    @PostMapping("/adicionar/{historicoMedicoId}")
    public ResponseEntity<ExameMedicoOutput> adicionarExame(@PathVariable UUID historicoMedicoId,
                                                            @Valid @RequestBody ExameMedicoInput exameInput) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(exameMedicoService.adicionarExame(historicoMedicoId, exameInput));  // Retorna 201 Created
    }
}

