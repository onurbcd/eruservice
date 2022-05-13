package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.api.Constants;
import com.onurbcd.eruservice.api.dto.Dtoable;
import com.onurbcd.eruservice.api.dto.SecretDto;
import com.onurbcd.eruservice.service.CrudService;
import com.onurbcd.eruservice.service.filter.SecretFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public class EruController {

    protected final CrudService service;

    public EruController(CrudService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Dtoable> post(@RequestBody SecretDto secretDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(secretDto, null));
    }

    @PutMapping(Constants.PATH_ID)
    public ResponseEntity<Dtoable> put(@PathVariable("id") UUID id, @RequestBody SecretDto secretDto) {
        return ResponseEntity.ok(service.save(secretDto, id));
    }

    @DeleteMapping(Constants.PATH_ID)
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(Constants.PATH_ID)
    public ResponseEntity<Dtoable> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Dtoable>> getAll(@PageableDefault(sort = "name") Pageable pageable, SecretFilter filter) {
        return ResponseEntity.ok(service.getAll(pageable, filter));
    }

    @PatchMapping(Constants.PATH_ID)
    public ResponseEntity<Void> patch(@PathVariable("id") UUID id, @RequestBody SecretDto secretDto) {
        service.update(secretDto, id);
        return ResponseEntity.noContent().build();
    }
}
