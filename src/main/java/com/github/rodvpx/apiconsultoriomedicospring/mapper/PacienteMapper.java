package com.github.rodvpx.apiconsultoriomedicospring.mapper;

import com.github.rodvpx.apiconsultoriomedicospring.dto.input.PacienteInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.PacienteOutput;
import com.github.rodvpx.apiconsultoriomedicospring.model.Paciente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacienteMapper extends MapperBase<Paciente, PacienteInput, PacienteOutput> {
}

