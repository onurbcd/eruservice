package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.SecretDto;
import com.onurbcd.eruservice.service.SecretService;
import com.onurbcd.eruservice.dto.filter.SecretFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secret")
public class SecretController extends EruController<SecretDto, SecretFilter> {

    public SecretController(SecretService service) {
        super(service);
    }
}
