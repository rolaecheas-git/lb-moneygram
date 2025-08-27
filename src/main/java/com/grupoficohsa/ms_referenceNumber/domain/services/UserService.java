package com.grupoficohsa.ms_referenceNumber.domain.services;

import com.grupoficohsa.ms_referenceNumber.application.dto.UserDto;
import com.grupoficohsa.ms_referenceNumber.domain.ports.in.UserUseCase;
import com.grupoficohsa.ms_referenceNumber.domain.ports.out.UserApiClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {

    private final UserApiClientPort userApiClientPort;

    @Override
    public List<UserDto> getUsers() {
        return userApiClientPort.getUsers();
    }
}
