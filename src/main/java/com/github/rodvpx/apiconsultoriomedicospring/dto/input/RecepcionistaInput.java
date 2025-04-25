package com.github.rodvpx.apiconsultoriomedicospring.dto.input;

import java.time.LocalDate;

public record RecepcionistaInput (
        String cpf,
        String nome,
        String telefone,
        LocalDate dataContratacao,
        Boolean status,
        UsuarioInput usuario
){
}
