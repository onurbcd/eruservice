package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.api.dto.Dtoable;
import com.onurbcd.eruservice.api.dto.SecretDto;
import com.onurbcd.eruservice.service.SecretService;
import com.onurbcd.eruservice.service.filter.SecretFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secret")
public class SecretController {

    private final SecretService service;

    @Autowired
    public SecretController(SecretService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Dtoable> post(@RequestBody SecretDto secretDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(secretDto, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dtoable> put(@PathVariable("id") String id, @RequestBody SecretDto secretDto) {
        return ResponseEntity.ok(service.save(secretDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dtoable> get(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Dtoable>> getAll(@PageableDefault(sort = "name") Pageable pageable, SecretFilter filter) {
        return ResponseEntity.ok(service.getAll(pageable, filter));
    }
}
