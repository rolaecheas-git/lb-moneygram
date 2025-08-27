package com.grupoficohsa.ms_referenceNumber.infrastructure.webconsumers;

import com.grupoficohsa.ms_referenceNumber.application.dto.UserDto;
import com.grupoficohsa.ms_referenceNumber.domain.ports.out.UserApiClientPort;
import com.grupoficohsa.ms_referenceNumber.infrastructure.configuration.UserApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserWebClient implements UserApiClientPort {

    private final WebClient.Builder webClientBuilder;
    private final UserApiProperties userApiProperties;

    @Override
    public List<UserDto> getUsers() {
        return webClientBuilder.build()
                .get()
                .uri(userApiProperties.getUrl())
                .retrieve()
                .bodyToFlux(UserDto.class)
                .collectList()
                .block();
    }
}
