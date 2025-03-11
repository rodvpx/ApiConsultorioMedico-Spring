package com.github.rodvpx.apiconsultoriomedicospring.repository;

import com.github.rodvpx.apiconsultoriomedicospring.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Usuario findByEmail(String email);

    boolean existsByEmail(String email);
}
