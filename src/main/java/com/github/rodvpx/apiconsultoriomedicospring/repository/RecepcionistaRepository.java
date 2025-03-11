package com.github.rodvpx.apiconsultoriomedicospring.repository;

import com.github.rodvpx.apiconsultoriomedicospring.model.Recepcionista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecepcionistaRepository extends JpaRepository<Recepcionista, UUID> {

}
