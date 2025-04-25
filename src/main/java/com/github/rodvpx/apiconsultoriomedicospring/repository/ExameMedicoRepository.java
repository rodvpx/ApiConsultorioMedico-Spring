package com.github.rodvpx.apiconsultoriomedicospring.repository;

import com.github.rodvpx.apiconsultoriomedicospring.model.ExameMedico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExameMedicoRepository extends JpaRepository<ExameMedico, UUID> {
}
