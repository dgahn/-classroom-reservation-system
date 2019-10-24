package me.dgahn.crs;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CrsApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrsApplication.class, args);
  }

}
