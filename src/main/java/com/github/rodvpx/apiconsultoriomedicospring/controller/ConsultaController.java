package com.github.rodvpx.apiconsultoriomedicospring.controller;

import com.github.rodvpx.apiconsultoriomedicospring.dto.input.ConsultaInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.ConsultaOutput;
import com.github.rodvpx.apiconsultoriomedicospring.model.Consulta;
import com.github.rodvpx.apiconsultoriomedicospring.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private final ConsultaService consultaService;

    @Autowired
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    // Endpoint para agendar uma consulta
    @PostMapping("/agendar")
    public ResponseEntity<ConsultaOutput> agendarConsulta(@Valid @RequestBody ConsultaInput consultaInput) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(consultaService.agendarConsulta(consultaInput));  // Retorna 201 Created
    }

    // Endpoint para atualizar o status de uma consulta
    @PutMapping("/status/{id}")
    public ResponseEntity<ConsultaOutput> atualizarStatus(@PathVariable UUID id, @RequestBody Consulta.StatusConsulta status) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(consultaService.atualizarStatus(id, status));  // Retorna 200 OK
    }

    // Endpoint para buscar todas as consultas de um paciente
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ConsultaOutput>> buscarConsultasPorPaciente(@PathVariable UUID pacienteId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(consultaService.buscarConsultasPorPaciente(pacienteId));  // Retorna 200 OK
    }
}
