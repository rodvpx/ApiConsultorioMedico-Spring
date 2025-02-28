package com.github.rodvpx.apiconsultoriomedicospring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "Nome é obrigatório.")
    private String nome;

    @NotNull
    @Email(message = "Email inválido.")
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(min = 5, message = "A senha deve ter no mínimo 5 caracteres.")
    private String senha;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "Formato de telefone inválido. Use (XX) XXXXX-XXXX.")
    private String telefone;

    @NotNull
    @Pattern(regexp = "\\d{4,6}", message = "CRM inválido. Deve conter apenas números.")
    @Column(unique = true)
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @NotNull
    private LocalDate dataContratacao;

    @NotNull
    private Boolean status;

    public enum Especialidade {
        CARDIOLOGIA,
        PEDIATRIA,
        ORTOPEDIA,
        GINECOLOGIA,
        DERMATOLOGIA
    }

}

