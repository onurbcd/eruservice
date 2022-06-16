package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.enums.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface SequenceApi {

    @PatchMapping("/{id}/sequence")
    ResponseEntity<Void> updateSequence(@PathVariable("id") UUID id, @RequestParam("direction") Direction direction);
}
