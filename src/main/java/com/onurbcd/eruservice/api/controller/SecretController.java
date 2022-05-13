package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secret")
public class SecretController extends EruController {

    @Autowired
    public SecretController(SecretService service) {
        super(service);
    }
}
