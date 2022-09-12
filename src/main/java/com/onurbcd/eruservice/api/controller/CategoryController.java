package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.CategoryDto;
import com.onurbcd.eruservice.service.CategoryService;
import com.onurbcd.eruservice.service.filter.CategoryFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController extends EruController<CategoryDto, CategoryFilter> {

    public CategoryController(CategoryService service) {
        super(service);
    }
}
