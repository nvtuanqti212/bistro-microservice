package com.bistrocheese.userservice.bootstrap;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Dataseeder implements ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {}
}
