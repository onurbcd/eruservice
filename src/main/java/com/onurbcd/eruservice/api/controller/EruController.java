package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.Dtoable;
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

public class EruController<D extends Dtoable, F extends Filterable> {

    protected final CrudService service;

    public EruController(CrudService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Dtoable> post(@RequestBody D dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dtoable> put(@PathVariable("id") UUID id, @RequestBody D dto) {
        return ResponseEntity.ok(service.save(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dtoable> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Dtoable>> getAll(@PageableDefault(sort = "name") Pageable pageable, F filter) {
        return ResponseEntity.ok(service.getAll(pageable, filter));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patch(@PathVariable("id") UUID id, @RequestBody D dto) {
        service.update(dto, id);
        return ResponseEntity.noContent().build();
    }
}
