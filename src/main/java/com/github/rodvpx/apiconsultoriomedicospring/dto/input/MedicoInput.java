package com.github.rodvpx.apiconsultoriomedicospring.dto.input;

import com.github.rodvpx.apiconsultoriomedicospring.model.Medico;

import java.time.LocalDate;

public record MedicoInput (
        String nome,
        String telefone,
        String crm,
        Medico.Especialidade especialidade,
        LocalDate dataContratacao
) {
}
