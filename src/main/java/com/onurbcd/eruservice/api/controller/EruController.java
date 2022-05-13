package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.api.dto.Dtoable;
import com.onurbcd.eruservice.service.CrudService;
import com.onurbcd.eruservice.service.filter.Filterable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

import static com.onurbcd.eruservice.api.Constants.*;

public class EruController<D extends Dtoable, F extends Filterable> {

    protected final CrudService service;

    public EruController(CrudService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Dtoable> post(@RequestBody D dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto, null));
    }

    @PutMapping(PATH_ID)
    public ResponseEntity<Dtoable> put(@PathVariable(ID) UUID id, @RequestBody D dto) {
        return ResponseEntity.ok(service.save(dto, id));
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
    public ResponseEntity<Page<Dtoable>> getAll(@PageableDefault(sort = NAME) Pageable pageable, F filter) {
        return ResponseEntity.ok(service.getAll(pageable, filter));
    }

    @PatchMapping(PATH_ID)
    public ResponseEntity<Void> patch(@PathVariable(ID) UUID id, @RequestBody D dto) {
        service.update(dto, id);
        return ResponseEntity.noContent().build();
    }
}
