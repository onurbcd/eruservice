package com.onurbcd.eruservice.command;

import com.onurbcd.eruservice.dto.Constants;
import com.onurbcd.eruservice.dto.secret.SecretSaveDto;
import com.onurbcd.eruservice.service.impl.SecretService;
import com.onurbcd.eruservice.util.Extension;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.hibernate.validator.constraints.URL;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@ShellCommandGroup("Secret")
@RequiredArgsConstructor
@ExtensionMethod({Extension.class})
public class SecretCommand {

    private final SecretService service;

    @ShellMethod(key = "secret-create", value = "Create a secret.")
    public String create(
            @ShellOption(value = {"name", "n"}, help = "The unique name of the secret.")
            @NotBlank
            @Size(min = 3, max = 50)
            String name,

            @ShellOption(value = {"description", "d"}, help = "The description of the secret.", defaultValue = ShellOption.NULL)
            @Size(min = 5, max = 250)
            String description,

            @ShellOption(value = {"link", "l"}, help = "The link to the secret app.", defaultValue = ShellOption.NULL)
            @URL(regexp = Constants.REGEXP_URL)
            @Size(min = 7, max = 2048)
            String link,

            @ShellOption(value = {"username", "u"}, help = "The username to the secret app.")
            @NotBlank
            @Size(min = 3, max = 50)
            String username,

            @ShellOption(value = {"password", "p"}, help = "The password to the secret app.")
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
}
