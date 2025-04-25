package com.github.rodvpx.apiconsultoriomedicospring.dto.output;

import com.github.rodvpx.apiconsultoriomedicospring.model.Medico;

import java.time.LocalDate;

public record MedicoOutput(
        String nome,
        String crm,
        Medico.Especialidade especialidade,
        LocalDate dataContratacao,
        Boolean status
) {
}
