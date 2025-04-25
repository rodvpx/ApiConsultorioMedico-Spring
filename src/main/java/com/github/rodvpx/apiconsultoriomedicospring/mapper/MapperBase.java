package com.github.rodvpx.apiconsultoriomedicospring.mapper;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MapperBase<E, I, O> {
    E toEntity(I input);
    O toOutput(E entity);
    List<O> toOutputList(List<E> entities);
}

