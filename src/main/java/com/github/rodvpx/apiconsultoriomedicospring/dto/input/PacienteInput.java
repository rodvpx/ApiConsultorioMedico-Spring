package com.github.rodvpx.apiconsultoriomedicospring.dto.input;

import com.github.rodvpx.apiconsultoriomedicospring.model.Paciente;
import com.github.rodvpx.apiconsultoriomedicospring.repository.UsuarioRepository;

import java.time.LocalDate;

public record PacienteInput(
        String nome,
        String cpf,
        String telefone,
        String endereco,
        LocalDate dataNascimento,
        Paciente.Genero genero,
        UsuarioInput usuario
) {
}

