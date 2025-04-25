package com.github.rodvpx.apiconsultoriomedicospring.dto.input;

import java.time.LocalDate;

public record RecepcionistaInput (
        String cpf,
        String telefone,
        LocalDate dataContratacao
){
}
