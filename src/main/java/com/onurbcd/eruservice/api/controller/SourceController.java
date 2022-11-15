package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.filter.SourceFilter;
import com.onurbcd.eruservice.dto.source.SourcePatchDto;
import com.onurbcd.eruservice.dto.source.SourceSaveDto;
import com.onurbcd.eruservice.service.SourceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/source")
public class SourceController extends PrimeController<SourceSaveDto, SourcePatchDto, SourceFilter> {

    public SourceController(SourceService service) {
        super(service);
    }
}
