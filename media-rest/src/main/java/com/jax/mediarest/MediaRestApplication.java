package com.jax.mediarest;

import com.jax.mediarest.service.IFileService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class MediaRestApplication implements CommandLineRunner {

    @Resource
    IFileService service;

    public static void main(String[] args) {
        SpringApplication.run(MediaRestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.deleteAll();
        service.init();
    }
}
