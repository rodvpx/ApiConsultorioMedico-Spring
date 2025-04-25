package com.github.rodvpx.apiconsultoriomedicospring.repository;
import com.github.rodvpx.apiconsultoriomedicospring.model.HistoricoMedico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HistoricoMedicoRepository extends JpaRepository<HistoricoMedico, UUID> {
}
