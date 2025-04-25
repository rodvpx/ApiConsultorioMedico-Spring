package com.github.rodvpx.apiconsultoriomedicospring.controller;

import com.github.rodvpx.apiconsultoriomedicospring.dto.input.PacienteInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.PacienteOutput;
import com.github.rodvpx.apiconsultoriomedicospring.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // Endpoint para criar um paciente
    @PostMapping("/cadastro")
    public ResponseEntity<PacienteOutput> criarPaciente(@Valid @RequestBody PacienteInput pacienteInput) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pacienteService.save(pacienteInput));  // Retorna 201 Created
    }

    // Endpoint para atualizar um paciente
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PacienteOutput> atualizarPaciente(@Valid @RequestBody PacienteInput pacienteInput, @PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pacienteService.update(id, pacienteInput));  // Retorna 200 OK
    }

    // Endpoint para buscar um paciente pelo CPF
    @GetMapping("/busca/{cpf}")
    public ResponseEntity<PacienteOutput> buscarPaciente(@PathVariable String cpf) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pacienteService.findByCpf(cpf));  // Retorna 200 OK
    }

    // Endpoint para buscar todos os pacientes
    @GetMapping("/busca/all")
    public ResponseEntity<List<PacienteOutput>> buscarTodosPacientes() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pacienteService.findAll());  // Retorna 200 OK
    }

    // Endpoint para excluir um paciente
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> excluirPaciente(@PathVariable UUID id) {
        pacienteService.excluirPaciente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // Retorna 204 No Content
    }
}

