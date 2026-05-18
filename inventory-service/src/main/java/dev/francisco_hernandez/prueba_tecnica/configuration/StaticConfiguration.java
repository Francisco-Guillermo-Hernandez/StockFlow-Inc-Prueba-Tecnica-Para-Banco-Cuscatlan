package dev.francisco_hernandez.prueba_tecnica.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/{path:[^\\.]*}")
                .setViewName("forward:/index.html");
        registry.addViewController("/*/{path:[^\\.]*}")
                .setViewName("forward:/index.html");
    }
}