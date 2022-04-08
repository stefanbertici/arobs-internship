package com.arobs.internship.musify.mapper;

import com.arobs.internship.musify.dto.UserDTO;
import com.arobs.internship.musify.dto.UserViewDTO;
import com.arobs.internship.musify.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "fullName", expression = "java(user.getFullName())")
    UserViewDTO toViewDto(User user);

    UserDTO toDto(User user);

    User toEntity(UserDTO userDTO);
}
