package com.github.rodvpx.apiconsultoriomedicospring.mapper;

import com.github.rodvpx.apiconsultoriomedicospring.dto.input.MedicoInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.MedicoOutput;
import com.github.rodvpx.apiconsultoriomedicospring.model.Medico;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicoMapper extends MapperBase<Medico, MedicoInput, MedicoOutput> {
}

