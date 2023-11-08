package com.rcamargo15.icoffeeapp.models.enums;

public enum PaymentEnum {

    DEBITO_VR(1, "Debito/VR"),
    CREDITO(2, "Credito"),
    PIX(3, "Pix");

    private Integer paymentIdentifier;
    private String paymentName;

    private PaymentEnum(Integer identifier, String paymentName){
        this.paymentName = paymentName;
        this.paymentIdentifier = identifier;
    }

    public PaymentEnum getPaymentType(String paymentName){
        for(PaymentEnum payments : PaymentEnum.values()){
            if(paymentName.toLowerCase().equals(payments.paymentName.toLowerCase())){
                return payments;
            }
        }
        return null;
    }

    public String getPaymentName(){
        return paymentName;
    }
}
