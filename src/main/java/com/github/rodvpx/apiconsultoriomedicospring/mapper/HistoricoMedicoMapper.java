package com.github.rodvpx.apiconsultoriomedicospring.mapper;

import com.github.rodvpx.apiconsultoriomedicospring.dto.input.HistoricoMedicoInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.HistoricoMedicoOutput;
import com.github.rodvpx.apiconsultoriomedicospring.model.HistoricoMedico;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistoricoMedicoMapper {
    HistoricoMedico toEntity(HistoricoMedicoInput input);

    HistoricoMedicoOutput toOutput(HistoricoMedico entity);

    List<HistoricoMedicoOutput> toOutputList(List<HistoricoMedico> entities);
}