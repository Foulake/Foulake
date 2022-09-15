package com.example.Fenalait.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Fenalait.dto.ClientDto;
import com.example.Fenalait.dto.ClientResponse;
import com.example.Fenalait.service.ClientService;
import com.example.Fenalait.utils.AppConstants;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

	private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

   
    @PreAuthorize("hasRole('ADMIN')")
    // create blog client rest api
    @PostMapping("/add")
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto clientDto){
        return new ResponseEntity<>(clientService.createClient(clientDto), HttpStatus.CREATED);
    }

    // get all clients rest api
    @GetMapping("/getAll")
    public ClientResponse getAllClients(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return clientService.getAllClients(pageNo, pageSize, sortBy, sortDir);
    }

    // get client by id
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    //@PostAuthorize("hasRole('ADMIN')")
    // update client by id rest api
    @PutMapping("/edit/{id}")
    public ResponseEntity<ClientDto> updateClient(@Valid @RequestBody ClientDto clientDto, @PathVariable(name = "id") long id){

       ClientDto clientResponse = clientService.updateClient(clientDto, id);

       return new ResponseEntity<>(clientResponse, HttpStatus.OK);
    }

    //@PostAuthorize("hasRole('ADMIN')")
    // delete client rest api
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable(name = "id") long id){

        clientService.deleteClientById(id);

        return new ResponseEntity<>("Client entity deleted successfully.", HttpStatus.OK);
    }
    
}
