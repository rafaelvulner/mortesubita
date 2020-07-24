package com.mortesubita.config;

import com.mortesubita.domain.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResponseConfiguration {

    @Bean
    public Response retornaRespose(){
        return new Response();
    }
}
