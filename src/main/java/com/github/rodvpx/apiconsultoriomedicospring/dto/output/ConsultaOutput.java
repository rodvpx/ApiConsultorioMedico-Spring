package com.github.rodvpx.apiconsultoriomedicospring.dto.output;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultaOutput(
        UUID id,
        String pacienteNome,
        String medicoNome,
        String recepcionistaNome,
        LocalDateTime dataHora,
        String status,
        String motivoCancelamento,
        String observacoes
) {}