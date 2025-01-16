package com.dscrud.crud.controllers;

import com.dscrud.crud.dto.ClientDTO;
import com.dscrud.crud.dto.CustomError;
import com.dscrud.crud.services.ClientService;
import com.dscrud.crud.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            ClientDTO dto = service.findById(id);
            return ResponseEntity.ok(dto);
        }
        catch (ResourceNotFoundException e) {
            CustomError error = new CustomError(Instant.now(), 404, e.getMessage(), "/clients/" + id);
            return ResponseEntity.status(404).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
        Page<ClientDTO> dtos = service.findAll(pageable);
        return ResponseEntity.ok(dtos);
    }



}
