package com.grupoficohsa.ms_referenceNumber.domain.ports.in;

import com.grupoficohsa.ms_referenceNumber.application.dto.UserDto;
import java.util.List;

public interface UserUseCase {
    List<UserDto> getUsers();
}
