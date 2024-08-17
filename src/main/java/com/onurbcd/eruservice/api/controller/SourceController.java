package com.onurbcd.eruservice.api.controller;

/*import com.onurbcd.eruservice.dto.filter.SourceFilter;
import com.onurbcd.eruservice.dto.source.BalanceSumDto;
import com.onurbcd.eruservice.dto.source.SourcePatchDto;
import com.onurbcd.eruservice.dto.source.SourceSaveDto;
import com.onurbcd.eruservice.service.SourceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/source")
public class SourceController extends PrimeController<SourceSaveDto, SourcePatchDto, SourceFilter> {

    private final SourceService sourceService;

    public SourceController(SourceService service) {
        super(service);
        this.sourceService = service;
    }

    @GetMapping("/balance-sum")
    @ResponseStatus(HttpStatus.OK)
    public BalanceSumDto getBalanceSum(SourceFilter filter) {
        return sourceService.getBalanceSum(filter);
    }
}*/
