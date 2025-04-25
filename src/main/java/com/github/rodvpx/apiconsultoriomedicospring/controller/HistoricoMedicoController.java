package com.github.rodvpx.apiconsultoriomedicospring.controller;

import com.github.rodvpx.apiconsultoriomedicospring.dto.input.ExameMedicoInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.input.HistoricoMedicoInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.ExameMedicoOutput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.HistoricoMedicoOutput;
import com.github.rodvpx.apiconsultoriomedicospring.service.HistoricoMedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/historico-medico")
public class HistoricoMedicoController {

    private final HistoricoMedicoService historicoMedicoService;

    @Autowired
    public HistoricoMedicoController(HistoricoMedicoService historicoMedicoService) {
        this.historicoMedicoService = historicoMedicoService;
    }

    // Endpoint para criar um histórico médico
    @PostMapping("/criar/{pacienteId}/{medicoId}/{consultaId}")
    public ResponseEntity<HistoricoMedicoOutput> criarHistorico(@PathVariable UUID pacienteId,
                                                                @PathVariable UUID medicoId,
                                                                @PathVariable UUID consultaId,
                                                                @Valid @RequestBody HistoricoMedicoInput historicoInput) {
        HistoricoMedicoOutput historicoMedicoOutput = historicoMedicoService.criarHistorico(pacienteId, medicoId, consultaId, historicoInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(historicoMedicoOutput);  // Retorna 201 Created
    }

    // Endpoint para adicionar um exame médico ao histórico
    @PostMapping("/adicionar-exame/{historicoMedicoId}")
    public ResponseEntity<ExameMedicoOutput> adicionarExame(@PathVariable UUID historicoMedicoId,
                                                            @Valid @RequestBody ExameMedicoInput exameInput) {
        ExameMedicoOutput exameMedicoOutput = historicoMedicoService.adicionarExame(historicoMedicoId, exameInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(exameMedicoOutput);  // Retorna 201 Created
    }
}

