package com.github.rodvpx.apiconsultoriomedicospring.dto.input;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultaInput(
        UUID pacienteId,
        UUID medicoId,
        UUID recepcionistaId,
        LocalDateTime dataHora,
        String observacoes
) {}