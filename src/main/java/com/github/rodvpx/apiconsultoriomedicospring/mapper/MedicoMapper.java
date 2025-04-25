package com.github.rodvpx.apiconsultoriomedicospring.mapper;

import com.github.rodvpx.apiconsultoriomedicospring.dto.input.MedicoInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.MedicoOutput;
import com.github.rodvpx.apiconsultoriomedicospring.model.Medico;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicoMapper {

    Medico toEntity(MedicoInput input);

    MedicoOutput toOutput(Medico entity);

    List<MedicoOutput> toOutputList(List<Medico> entities);
}


