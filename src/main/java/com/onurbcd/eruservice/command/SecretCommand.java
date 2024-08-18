package com.onurbcd.eruservice.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onurbcd.eruservice.command.enums.EruTable;
import com.onurbcd.eruservice.command.helper.ShellHelper;
import com.onurbcd.eruservice.dto.Constants;
import com.onurbcd.eruservice.dto.filter.SecretFilter;
import com.onurbcd.eruservice.dto.secret.SecretSaveDto;
import com.onurbcd.eruservice.service.impl.SecretService;
import com.onurbcd.eruservice.util.Extension;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.UUID;

@ShellComponent
@ShellCommandGroup("Secret")
@RequiredArgsConstructor
@ExtensionMethod({Extension.class})
public class SecretCommand {

    private final SecretService service;
    private final ObjectMapper eruMapper;
    private final ShellHelper shellHelper;

    @ShellMethod(key = "secret-create", value = "Create a secret.")
    public String create(
            @ShellOption(value = {"name", "-n"}, help = "The unique name of the secret.")
            @NotBlank
            @Size(min = 3, max = 50)
            String name,

            @ShellOption(value = {"description", "-d"}, help = "The description of the secret.", defaultValue = ShellOption.NULL)
            @Size(min = 5, max = 250)
            String description,

            @ShellOption(value = {"link", "-l"}, help = "The link to the secret app.", defaultValue = ShellOption.NULL)
            @URL(regexp = Constants.REGEXP_URL)
            @Size(min = 7, max = 2048)
            String link,

            @ShellOption(value = {"username", "-u"}, help = "The username to the secret app.")
            @NotBlank
            @Size(min = 3, max = 50)
            String username,

            @ShellOption(value = {"password", "-p"}, help = "The password to the secret app.")
            @NotBlank
            @Size(min = 3, max = 50)
            String password
    ) {
        var secretSaveDto = SecretSaveDto
                .builder()
                .name(name.normalizeSpace())
                .active(Boolean.TRUE)
                .description(description.defaultToNull())
                .link(link.defaultToNull())
                .username(username.normalizeSpace())
                .password(password.normalizeSpace())
                .build();

        return service.save(secretSaveDto, null);
    }

    @ShellMethod(key = "secret-get", value = "Get secret by id.")
    public String get(
            @ShellOption(value = {"id", "-i"}, help = "The secret's id.")
            @NotNull
            UUID id
    ) throws JsonProcessingException {
        var secret = service.getById(id);
        return eruMapper.writerWithDefaultPrettyPrinter().writeValueAsString(secret);
    }

    @ShellMethod(key = "secret-get-all", value = "Get secrets list.")
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
            String search
    ) {
        var page = service.getAll(
                PageRequest.of(pageNumber - 1, pageSize, direction, property),
                SecretFilter.of(active, search)
        );

        return shellHelper.printTable(page, EruTable.SECRET);
    }
}