package com.nanshakov.bank;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@Log4j2
@SpringBootApplication
public class BankApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(BankApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
