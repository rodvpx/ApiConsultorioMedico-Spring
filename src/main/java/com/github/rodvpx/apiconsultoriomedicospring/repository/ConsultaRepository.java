package com.github.rodvpx.apiconsultoriomedicospring.repository;

import com.github.rodvpx.apiconsultoriomedicospring.model.Consulta;
import com.github.rodvpx.apiconsultoriomedicospring.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {

    List<Consulta> findByPaciente(Paciente paciente);
}
