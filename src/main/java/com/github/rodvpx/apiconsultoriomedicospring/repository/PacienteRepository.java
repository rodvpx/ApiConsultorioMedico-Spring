package com.github.rodvpx.apiconsultoriomedicospring.repository;

import com.github.rodvpx.apiconsultoriomedicospring.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {

}
