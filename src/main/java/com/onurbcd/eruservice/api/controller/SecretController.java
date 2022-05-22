package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.SecretDto;
import com.onurbcd.eruservice.service.SecretService;
import com.onurbcd.eruservice.service.filter.SecretFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.onurbcd.eruservice.api.ApiConstants.PATH_SECRET;

@RestController
@RequestMapping(PATH_SECRET)
public class SecretController extends EruController<SecretDto, SecretFilter> {

    @Autowired
    public SecretController(SecretService service) {
        super(service);
    }
}
