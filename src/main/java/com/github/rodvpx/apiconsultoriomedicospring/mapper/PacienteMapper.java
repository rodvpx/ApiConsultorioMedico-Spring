package com.github.rodvpx.apiconsultoriomedicospring.mapper;

import com.github.rodvpx.apiconsultoriomedicospring.dto.input.PacienteInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.PacienteOutput;
import com.github.rodvpx.apiconsultoriomedicospring.model.Paciente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    Paciente toEntity(PacienteInput input);

    PacienteOutput toOutput(Paciente entity);

    List<PacienteOutput> toOutputList(List<Paciente> entities);
}