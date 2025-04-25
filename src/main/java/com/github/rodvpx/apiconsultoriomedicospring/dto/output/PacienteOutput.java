package com.github.rodvpx.apiconsultoriomedicospring.dto.output;

import com.github.rodvpx.apiconsultoriomedicospring.model.Paciente;

import java.time.LocalDate;

public record PacienteOutput(
        String nome,
        LocalDate dataNascimento,
        Paciente.Genero genero
) {
}
