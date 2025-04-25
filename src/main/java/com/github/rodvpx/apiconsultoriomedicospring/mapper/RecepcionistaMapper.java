package com.github.rodvpx.apiconsultoriomedicospring.mapper;

import com.github.rodvpx.apiconsultoriomedicospring.dto.input.RecepcionistaInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.RecepcionistaOutput;
import com.github.rodvpx.apiconsultoriomedicospring.model.Recepcionista;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecepcionistaMapper {
    Recepcionista toEntity(RecepcionistaInput input);

    RecepcionistaOutput toOutput(Recepcionista entity);

    List<RecepcionistaOutput> toOutputList(List<Recepcionista> entities);
}