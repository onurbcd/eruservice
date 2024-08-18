package com.onurbcd.eruservice.config;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.PromptProvider;

@Configuration
public class PromptConfig implements PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("erucli:> ", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
    }
}
