package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.dto.category.CategoryPatchDto;
import com.onurbcd.eruservice.dto.category.CategorySaveDto;
import com.onurbcd.eruservice.service.CategoryService;
import com.onurbcd.eruservice.dto.filter.CategoryFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController extends PrimeController<CategorySaveDto, CategoryPatchDto, CategoryFilter> {

    public CategoryController(CategoryService service) {
        super(service);
    }
}
