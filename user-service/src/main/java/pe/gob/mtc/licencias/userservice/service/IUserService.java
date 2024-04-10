package pe.gob.mtc.licencias.userservice.service;


import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.UserEntity;
import pe.gob.mtc.licencias.userservice.model.dto.UserDto;
import pe.gob.mtc.licencias.userservice.model.response.UserResponse;

import java.util.List;

public interface IUserService  {

    UserDto save(UserDto userDto);

    List<UserResponse> findAll ();

}
