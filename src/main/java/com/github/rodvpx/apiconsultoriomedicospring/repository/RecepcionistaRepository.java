package com.github.rodvpx.apiconsultoriomedicospring.repository;


import com.github.rodvpx.apiconsultoriomedicospring.model.Recepcionista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecepcionistaRepository extends JpaRepository<Recepcionista, UUID> {

    // Buscar médico por nome
    List<Recepcionista> findByNome(String nome);

    // Buscar médico por CRM
    Optional<Recepcionista> findByCpf(String cpf);

    // Verificar se o CRM já está em uso
    boolean existsByCpf(String cpf);

    // Buscar todos os médicos (já disponível via JpaRepository)
    List<Recepcionista> findAll();

    Optional<Recepcionista> findById(UUID id);
}
