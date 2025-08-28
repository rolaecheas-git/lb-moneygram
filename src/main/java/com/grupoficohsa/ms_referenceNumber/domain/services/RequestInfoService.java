package com.grupoficohsa.ms_referenceNumber.domain.services;


import com.grupoficohsa.ms_referenceNumber.application.request.ReferenceNumberRequest;
import com.grupoficohsa.ms_referenceNumber.domain.ports.in.RequestInfoUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RequestInfoService implements RequestInfoUseCase {

    @Override
    public ReferenceNumberRequest process(ReferenceNumberRequest requestInfoDto) {
        log.info("Request received: {}", requestInfoDto);
        return requestInfoDto;
    }
}
