package pe.gob.mtc.licencias.userservice.expose.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.mtc.licencias.userservice.model.dto.UserDto;
import pe.gob.mtc.licencias.userservice.model.response.UserResponse;
import pe.gob.mtc.licencias.userservice.service.IUserService;
import pe.gob.mtc.licencias.userservice.util.Constants;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(Constants.URL_USERS)
@RequiredArgsConstructor
public class UserController {

    private final IUserService service;

    @PostMapping("/save")
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserDto userDto){
        log.info(userDto.toString());
        return new ResponseEntity<>(service.save(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> findAll(){
        List<UserResponse> lstUserResponse = service.findAll();
        return new ResponseEntity<>(lstUserResponse, HttpStatus.OK);
    }
}
