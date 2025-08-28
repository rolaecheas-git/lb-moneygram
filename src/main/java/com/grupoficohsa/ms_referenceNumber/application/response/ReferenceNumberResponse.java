package com.grupoficohsa.ms_referenceNumber.application.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ReferenceNumberResponse {

    private String transactionId;
    private String referenceNumber;
    private String transactionSendDateTime;
    private String expectedPayoutDate;
    private String transactionStatus;
    private List<TransactionSubStatus> transactionSubStatus;

    private String originatingCountryCode;
    private String destinationCountryCode;

    private String serviceOptionCode;
    private String serviceOptionName;
    private String serviceOptionRoutingCode;
    private String serviceOptionRoutingName;

    private SendAmount sendAmount;
    private ReceiveAmount receiveAmount;

    private Sender sender;
    private Receiver receiver;

    private List<AdditionalDetail> additionalDetails;


    @Data
    public static class TransactionSubStatus {
        private String subStatus;
        private String message;
        private String targetCustomer;
        private List<DataToCollect> dataToCollect;
    }

    @Data
    public static class DataToCollect {
        private String Code;
        private String dataCollection;
    }

    @Data
    public static class SendAmount {
        private Money amount;
        private Money fees;
        private Money taxes;
        private DiscountsApplied discountsApplied;
        private Money total;
    }

    @Data
    public static class ReceiveAmount {
        private Money amount;
        private Money fees;
        private Money taxes;
        private Money total;
        private BigDecimal fxRate;
        private boolean fxRateEstimated;
    }

    @Data
    public static class DiscountsApplied {
        private Money totalDiscount;
        private List<PromotionDetail> promotionDetails;
    }

    @Data
    public static class PromotionDetail {
        private String code;
        private Money discount;
        private String errorCode;
        private String errorMessage;
    }

    @Data
    public static class Money {
        private BigDecimal value;
        private String currencyCode;
    }

    @Data
    public static class Sender {
        private Name name;
        private String profileId;
        private List<AdditionalDetail> additionalDetails;
    }

    @Data
    public static class Receiver {
        private Name name;
    }

    @Data
    public static class Name {
        private String firstName;
        private String middleName;
        private String lastName;
        private String secondLastName;
    }

    @Data
    public static class AdditionalDetail {
        private String key;
        private String value;
    }
}
