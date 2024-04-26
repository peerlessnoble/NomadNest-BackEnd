package com.sid.usermicroservice.mappers;

import com.sid.usermicroservice.dto.UserDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import com.sid.usermicroservice.dto.UserRequestDto;
import com.sid.usermicroservice.dto.UserResponseDto;
import com.sid.usermicroservice.entities.User;

public class MappingProfile {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static User mapToUserEntity(UserRequestDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public static UserDto mapToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
    static {
        Converter<User, UserResponseDto> userToUserResponseDtoConverter = new Converter<User, UserResponseDto>() {
            public UserResponseDto convert(MappingContext<User, UserResponseDto> context) {
                User source = context.getSource();
                UserResponseDto destination = new UserResponseDto();
                destination.setEmail(source.getEmail());
                return destination;
            }
        };
        modelMapper.addConverter(userToUserResponseDtoConverter);
    }

    public static UserResponseDto toUserResponseDto(User user){
        if( user == null) return null;
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFirstname(user.getFirstname());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setIsEnabled(user.getIsEnabled());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setRole(user.getRole());
        userResponseDto.setLastname(user.getLastname());
        return userResponseDto;
    }
}
