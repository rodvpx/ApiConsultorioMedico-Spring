package com.github.rodvpx.apiconsultoriomedicospring.mapper;


import com.github.rodvpx.apiconsultoriomedicospring.dto.input.ExameMedicoInput;
import com.github.rodvpx.apiconsultoriomedicospring.dto.output.ExameMedicoOutput;
import com.github.rodvpx.apiconsultoriomedicospring.model.ExameMedico;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExameMedicoMapper {
    ExameMedico toEntity(ExameMedicoInput input);

    ExameMedicoOutput toOutput(ExameMedico entity);

    List<ExameMedicoOutput> toOutputList(List<ExameMedico> entities);
}