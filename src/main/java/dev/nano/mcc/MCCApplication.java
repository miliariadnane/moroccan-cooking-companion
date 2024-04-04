package dev.nano.mcc;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Theme(value = "moroccan-cooking-companion")
public class MCCApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(MCCApplication.class, args);
    }

}
