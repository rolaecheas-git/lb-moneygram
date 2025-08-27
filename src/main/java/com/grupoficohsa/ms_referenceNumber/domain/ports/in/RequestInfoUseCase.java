package com.grupoficohsa.ms_referenceNumber.domain.ports.in;

import com.grupoficohsa.ms_referenceNumber.application.request.ReferenceNumberRequest;

public interface RequestInfoUseCase {
    ReferenceNumberRequest process(ReferenceNumberRequest requestInfoDto);
}
