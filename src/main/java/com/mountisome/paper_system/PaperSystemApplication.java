package com.mountisome.paper_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mountisome.paper_system.dao")
public class PaperSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaperSystemApplication.class, args);
    }

}
