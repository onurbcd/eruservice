package com.onurbcd.eruservice.api.controller;

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

import static com.onurbcd.eruservice.api.Constants.*;

public class EruController {

    protected final CrudService service;

    public EruController(CrudService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Dtoable> post(@RequestBody SecretDto secretDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(secretDto, null));
    }

    @PutMapping(PATH_ID)
    public ResponseEntity<Dtoable> put(@PathVariable(ID) UUID id, @RequestBody SecretDto secretDto) {
        return ResponseEntity.ok(service.save(secretDto, id));
    }

    @DeleteMapping(PATH_ID)
    public ResponseEntity<Void> delete(@PathVariable(ID) UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(PATH_ID)
    public ResponseEntity<Dtoable> get(@PathVariable(ID) UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Dtoable>> getAll(@PageableDefault(sort = NAME) Pageable pageable,
                                                SecretFilter filter) {

        return ResponseEntity.ok(service.getAll(pageable, filter));
    }

    @PatchMapping(PATH_ID)
    public ResponseEntity<Void> patch(@PathVariable(ID) UUID id, @RequestBody SecretDto secretDto) {
        service.update(secretDto, id);
        return ResponseEntity.noContent().build();
    }
}
