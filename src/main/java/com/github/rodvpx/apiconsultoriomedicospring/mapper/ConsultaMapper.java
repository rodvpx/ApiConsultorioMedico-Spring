package com.github.rodvpx.apiconsultoriomedicospring.mapper;

import com.github.rodvpx.apiconsultoriomedicospring.dto.input.ConsultaInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.ConsultaOutput;
import com.github.rodvpx.apiconsultoriomedicospring.model.Consulta;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConsultaMapper {

    Consulta toEntity(ConsultaInput input);

    ConsultaOutput toOutput(Consulta entity);

    List<ConsultaOutput> toOutputList(List<Consulta> entities);
}

