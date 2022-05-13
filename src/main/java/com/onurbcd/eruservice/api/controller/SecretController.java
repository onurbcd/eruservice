package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.onurbcd.eruservice.api.Constants.PATH_SECRET;

@RestController
@RequestMapping(PATH_SECRET)
public class SecretController extends EruController {

    @Autowired
    public SecretController(SecretService service) {
        super(service);
    }
}
