package com.github.rodvpx.apiconsultoriomedicospring.mapper;

import com.github.rodvpx.apiconsultoriomedicospring.dto.input.RecepcionistaInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.RecepcionistaOutput;
import com.github.rodvpx.apiconsultoriomedicospring.model.Recepcionista;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecepcionistaMapper extends MapperBase<Recepcionista, RecepcionistaInput, RecepcionistaOutput> {
}
