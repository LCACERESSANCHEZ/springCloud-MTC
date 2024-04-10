package pe.gob.mtc.licencias.customercommandservice.expose.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.mtc.licencias.customercommandservice.model.dto.CustomerDto;
import pe.gob.mtc.licencias.customercommandservice.service.ICustomerService;
import pe.gob.mtc.licencias.customercommandservice.util.Constants;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(Constants.URL_CUSTOMER)
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerService service;

    @PostMapping("/save")
    public ResponseEntity<CustomerDto> save(@Valid @RequestBody CustomerDto customerDto){
        log.info(customerDto.toString());
        return new ResponseEntity<>(service.save(customerDto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> findAll(){
        List<CustomerDto> lstUserResponse = service.findAll();
        return new ResponseEntity<>(lstUserResponse, HttpStatus.OK);
    }

    @GetMapping("/findByDocumentNumber/{documentNumber}")
    public ResponseEntity<List<CustomerDto>> findByDocumentNumber(@PathVariable String documentNumber){
        List<CustomerDto> lstUserResponse = service.findByDocumentNumber(documentNumber);
        return new ResponseEntity<>(lstUserResponse, HttpStatus.OK);
    }

}
