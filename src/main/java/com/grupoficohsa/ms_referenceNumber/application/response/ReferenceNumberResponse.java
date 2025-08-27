package com.grupoficohsa.ms_referenceNumber.application.response;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ReferenceNumberResponse {
    private String destinationCountryCode;
    private String destinationCountrySubdivisionCode;
    private BigDecimal amount;
    private String sendCurrencyCode;
    private String receiveCurrencyCode;
    private String serviceOptionCode;
    private String serviceOptionName;
    private String serviceOptionRoutingCode;
    private String serviceOptionRoutingName;
    private List<FieldInfo> fieldInfo;

    @Data
    public static class FieldInfo {
        private String field;
        private String dataType;
        private boolean required;
        private String fieldLabel;
        private String displayOrder;
        private String fieldMin;
        private String fieldMax;
        private String regEx;
        private String exampleText;
        private String helpTextShort;
        private String helpTextLong;
        private List<EnumerationItem> enumerationItem;
    }

    @Data
    public static class EnumerationItem {
        private String value;
        private String description;
    }
}
