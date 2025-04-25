package com.github.rodvpx.apiconsultoriomedicospring.dto.output;

import java.time.LocalDateTime;
import java.util.UUID;

public record ExameMedicoOutput(
        UUID id,
        String tipoExame,
        String resultado,
        LocalDateTime dataExame,
        String arquivoUrl
) {}