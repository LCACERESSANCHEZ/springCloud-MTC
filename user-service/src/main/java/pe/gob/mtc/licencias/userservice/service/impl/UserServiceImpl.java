package pe.gob.mtc.licencias.userservice.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.UserEntity;
import pe.gob.mtc.licencias.userservice.model.dto.UserDto;
import pe.gob.mtc.licencias.userservice.model.response.UserResponse;
import pe.gob.mtc.licencias.userservice.repository.IUserRepository;
import pe.gob.mtc.licencias.userservice.service.IUserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository repository;

    @Override
    public UserDto save(UserDto userDto) {
        UserEntity productEntity = UserEntity.builder()
                .name(userDto.getName())
                .lastname(userDto.getLastname())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .roles(userDto.getRoles())
                .build();
        repository.save(productEntity);
        return userDto;
    }

    @Override
    public List<UserResponse> findAll() {
        Iterable<UserEntity> iUserEntity = repository.findAll();
        return StreamSupport.stream(iUserEntity.spliterator(), false)
                .map(user -> {
                    UserResponse userResponse = new UserResponse();
                    userResponse.setUsername(user.getUsername());
                    userResponse.setEmail(user.getEmail());
                    userResponse.setRoles(user.getRoles());
                    return userResponse;
                })
                .collect(Collectors.toList());
    }
}
