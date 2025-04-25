package com.github.rodvpx.apiconsultoriomedicospring.dto.input;

import com.github.rodvpx.apiconsultoriomedicospring.model.Paciente;

import java.time.LocalDate;

public record PacienteInput(
        String cpf,
        String nome,
        String telefone,
        String endereco,
        LocalDate dataNascimento,
        Paciente.Genero genero) {
}
