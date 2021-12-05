package com.bankin.challengebackend.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import com.bankin.challengebackend.clients.BridgeClient;

import javax.net.ssl.*;
import java.security.cert.CertificateException;

@Configuration
public class BridgeClientConfiguration {

    @Bean
    public BridgeClient bridgeClient(@Value("${bridge.api.base-url}") String baseUrl, ObjectMapper objectMapper) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        return new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient.build())
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .build()
            .create(BridgeClient.class);
    }
}
