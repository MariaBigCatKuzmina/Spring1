package ru.kuzmina.model.mapper;

import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kuzmina.model.Dto.UserDto;
import ru.kuzmina.model.User;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserDtoMapper {

    @Mapping(target = "password", ignore = true)
    UserDto map(User user);

//    @Mapping(source = "password", target = "password", qualifiedByName = "encode")
//    User map(UserDto userDto, @Context PasswordEncoder passwordEncoder);
    User map(UserDto userDto);

//    @Named("encode")
//    default String encode(String password, @Context PasswordEncoder passwordEncoder){
//        return passwordEncoder.encode(password);
//    }
}
