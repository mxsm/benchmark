package com.github.mxsm.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StructMapper {

    StructMapper INSTANCE = Mappers.getMapper(StructMapper.class);

    ClassA classA2classA(ClassA source);
}
