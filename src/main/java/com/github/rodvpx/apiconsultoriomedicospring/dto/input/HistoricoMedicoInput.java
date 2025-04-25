package com.github.rodvpx.apiconsultoriomedicospring.dto.input;

public record HistoricoMedicoInput(
        String diagnostico,
        String prescricao,
        String observacoes
) {}