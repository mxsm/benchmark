package com.github.mxsm.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mapping(source = "name",target = "userName")
    @Mapping(source = "password",target = "pwd")
    UserEntity convertUser2UserEntity(User user);
}
