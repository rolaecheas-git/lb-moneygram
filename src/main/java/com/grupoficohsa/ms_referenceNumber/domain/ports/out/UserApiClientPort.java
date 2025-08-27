package com.grupoficohsa.ms_referenceNumber.domain.ports.out;

import com.grupoficohsa.ms_referenceNumber.application.dto.UserDto;
import java.util.List;

public interface UserApiClientPort {
    List<UserDto> getUsers();
}
