package com.github.rodvpx.apiconsultoriomedicospring.dto.output;

import java.time.LocalDate;

public record RecepcionistaOutput (
        String nome,
        LocalDate dataContratacao,
        boolean status
) {
}
