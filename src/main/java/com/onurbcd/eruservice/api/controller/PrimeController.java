package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.Dtoable;
import com.onurbcd.eruservice.dto.filter.Filterable;
import com.onurbcd.eruservice.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@Validated
public class PrimeController<S extends Dtoable, P extends Dtoable, F extends Filterable> {

    protected final CrudService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@Valid @RequestBody S dto) {
        service.save(dto, null);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void put(@PathVariable("id") UUID id, @Valid @RequestBody S dto) {
        service.save(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Dtoable get(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Dtoable> getAll(@PageableDefault(sort = "name") Pageable pageable, F filter) {
        return service.getAll(pageable, filter);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patch(@PathVariable("id") UUID id, @Valid @RequestBody P dto) {
        service.update(dto, id);
    }
}
