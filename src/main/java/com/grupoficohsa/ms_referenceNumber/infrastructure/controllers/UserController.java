package com.grupoficohsa.ms_referenceNumber.infrastructure.controllers;

import com.grupoficohsa.ms_referenceNumber.application.dto.UserDto;
import com.grupoficohsa.ms_referenceNumber.domain.ports.in.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;

    @GetMapping
    public List<UserDto> getUsers() {
        return userUseCase.getUsers();
    }
}
