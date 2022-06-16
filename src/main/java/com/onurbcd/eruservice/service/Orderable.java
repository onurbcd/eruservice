package com.onurbcd.eruservice.service;

import com.onurbcd.eruservice.dto.enums.Direction;

import java.util.UUID;

public interface Orderable {

    void changeSequence(UUID id, Direction direction);
}
