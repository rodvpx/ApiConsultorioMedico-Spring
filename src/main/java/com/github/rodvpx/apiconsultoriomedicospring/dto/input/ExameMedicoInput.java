package com.github.rodvpx.apiconsultoriomedicospring.dto.input;

import java.time.LocalDateTime;

public record ExameMedicoInput(
        String tipoExame,
        String resultado,
        LocalDateTime dataExame,
        String arquivoUrl
) {}