package com.github.rodvpx.apiconsultoriomedicospring.dto.output;

import java.time.LocalDateTime;
import java.util.UUID;

public record HistoricoMedicoOutput(
        UUID id,
        String pacienteNome,
        String medicoNome,
        String consultaStatus,
        String diagnostico,
        String prescricao,
        String observacoes,
        LocalDateTime dataRegistro
) {}