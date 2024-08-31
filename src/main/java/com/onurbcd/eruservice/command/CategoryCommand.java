package com.onurbcd.eruservice.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.onurbcd.eruservice.command.enums.EruTable;
import com.onurbcd.eruservice.command.helper.ShellHelper;
import com.onurbcd.eruservice.dto.category.CategoryDto;
import com.onurbcd.eruservice.dto.category.CategoryPatchDto;
import com.onurbcd.eruservice.dto.category.CategorySaveDto;
import com.onurbcd.eruservice.dto.filter.CategoryFilter;
import com.onurbcd.eruservice.service.impl.CategoryService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.shell.component.context.ComponentContext;
import org.springframework.shell.component.flow.ComponentFlow;
import org.springframework.shell.component.flow.SelectItem;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ShellComponent
@ShellCommandGroup("Category")
@RequiredArgsConstructor
public class CategoryCommand {

    private static final String NAME = "name";
    private static final String PARENT_ID = "parentId";
    private static final String DESCRIPTION = "description";

    private final CategoryService service;
    private final ComponentFlow.Builder flowBuilder;
    private final ShellHelper shellHelper;

    @ShellMethod(key = "category-save", value = "Create or update a category.")
    public String save(
            @ShellOption(value = {"id", "-i"}, help = "The category's id.", defaultValue = ShellOption.NULL)
            UUID id
    ) {
        var result = runSaveFlow(id);

        var returnId = service.save(CategorySaveDto.of(result.get(NAME, String.class),
                result.get(PARENT_ID, String.class), result.get(DESCRIPTION, String.class)), id);

        return "Category with id: '%s' saved with success.".formatted(returnId);
    }

    @ShellMethod(key = "category-delete", value = "Delete category by id.")
    public String delete(
            @ShellOption(value = {"id", "-i"}, help = "The category's id.")
            @NotNull
            UUID id
    ) {
        service.delete(id);
        return "Category with id: '%s' deleted with success.".formatted(id);
    }

    @ShellMethod(key = "category-get", value = "Get category by id.")
    public String get(
            @ShellOption(value = {"id", "-i"}, help = "The category's id.")
            @NotNull
            UUID id
    ) throws JsonProcessingException {
        return shellHelper.printJson(service.getById(id));
    }

    @ShellMethod(key = "category-get-all", value = "Get category's list.")
    public String getAll(
            @ShellOption(value = {"pageNumber", "-n"}, help = "The page's number.", defaultValue = "1")
            @Min(1)
            Integer pageNumber,

            @ShellOption(value = {"pageSize", "-s"}, help = "The page's size.", defaultValue = "10")
            @Min(1)
            Integer pageSize,

            @ShellOption(value = {"direction", "-d"}, help = "The page's sort direction.", defaultValue = "ASC")
            Sort.Direction direction,

            @ShellOption(value = {"property", "-p"}, help = "The page's sort property.", defaultValue = "name")
            String property,

            @ShellOption(value = {"active", "-a"}, help = "Filter's active option.", defaultValue = ShellOption.NULL)
            Boolean active,

            @ShellOption(value = {"search", "-f"}, help = "Filter's search option.", defaultValue = ShellOption.NULL)
            String search,

            @ShellOption(value = {"parentId", "-i"}, help = "Filter's parent id.", defaultValue = ShellOption.NULL)
            UUID parentId,

            @ShellOption(value = {"level", "-l"}, help = "Filter's level.", defaultValue = ShellOption.NULL)
            Short level,

            @ShellOption(value = {"lastBranch", "-b"}, help = "Filter's last branch.", defaultValue = ShellOption.NULL)
            Boolean lastBranch
    ) {
        return shellHelper.printTable(
                service.getAll(
                        PageRequest.of(pageNumber - 1, pageSize, direction, property),
                        CategoryFilter.of(active, search, parentId, level, lastBranch)
                ),
                EruTable.CATEGORY
        );
    }

    @ShellMethod(key = "category-update", value = "Update category's status by id.")
    public String update(
            @ShellOption(value = {"id", "-i"}, help = "The category's id.")
            @NotNull
            UUID id,

            @ShellOption(value = {"active", "-a"}, help = "The category's status.", defaultValue = "false")
            Boolean active
    ) {
        service.update(CategoryPatchDto.of(active), id);
        return String.format("Category with id: '%s' updated with success.", id);
    }

    private ComponentContext<?> runSaveFlow(@Nullable UUID id) {
        String nameDefaultValue = null;
        String parentDefaultValue = null;
        var descriptionDefaultValue = ShellOption.NULL;

        if (id != null) {
            var category = (CategoryDto) service.getById(id);
            nameDefaultValue = category.getName();
            parentDefaultValue = category.getParentName();
            descriptionDefaultValue = category.getDescription() != null ? category.getDescription() : ShellOption.NULL;
        }

        return flowBuilder
                .clone()
                .reset()
                .withStringInput(NAME).name("* Name:").defaultValue(nameDefaultValue).and()
                .withSingleItemSelector(PARENT_ID).name("* Parent:").selectItems(getCategories(id)).defaultSelect(parentDefaultValue).max(20).and()
                .withStringInput(DESCRIPTION).name("Description:").defaultValue(descriptionDefaultValue).and()
                .build()
                .run()
                .getContext();
    }

    private List<SelectItem> getCategories(@Nullable UUID id) {
        var categories = service.getAll(PageRequest.of(0, 20), CategoryFilter.builder().build());
        var items = new ArrayList<SelectItem>();

        for (var dto : categories.getContent()) {
            var category = (CategoryDto) dto;

            if (id != null && id.equals(category.getId())) {
                continue;
            }

            items.add(SelectItem.of(category.getName(), category.getId().toString()));
        }

        return items;
    }
}
