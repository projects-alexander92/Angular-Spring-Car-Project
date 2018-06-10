package com;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class DemoApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ModelMapper getModelMapper()
    {
        return new ModelMapper();
    }

    @Bean
    public Gson getGson()
    {
        return new Gson();
    }
}
