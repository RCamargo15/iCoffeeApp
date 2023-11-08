package com.rcamargo15.icoffeeapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rcamargo15.icoffeeapp.R;
import com.rcamargo15.icoffeeapp.activities.payments.CreditAndDebitPaymentScreen;
import com.rcamargo15.icoffeeapp.activities.payments.PixPaymentScreen;
import com.rcamargo15.icoffeeapp.models.Order;
import com.rcamargo15.icoffeeapp.models.enums.PaymentEnum;
import com.rcamargo15.icoffeeapp.util.AppUtils;

public class OrderPaymentScreen extends AppCompatActivity {

    private Button buttonCreditPayment;
    private Button buttonDebitVRPayment;
    private Button buttonPixPayment;
    private Button buttonSairPaymentScreen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.esconderBarrasTarefas(this);
        setContentView(R.layout.activity_order_payment_screen);
        setScreenButtons();
        Intent intent = getIntent();
        Order clientOrder = intent.getSerializableExtra("clientOrder", Order.class);

        buttonCreditPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientOrder.setPaymentType(PaymentEnum.CREDITO);
                Intent intent1 = new Intent(OrderPaymentScreen.this, CreditAndDebitPaymentScreen.class);
                intent1.putExtra("clientOrder", clientOrder);
                startActivity(intent1);
            }
        });

        buttonDebitVRPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientOrder.setPaymentType(PaymentEnum.DEBITO_VR);
                Intent intent2 = new Intent(OrderPaymentScreen.this, CreditAndDebitPaymentScreen.class);
                intent2.putExtra("clientOrder", clientOrder);
                startActivity(intent2);
            }
        });

        buttonPixPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientOrder.setPaymentType(PaymentEnum.PIX);
                Intent intent3 = new Intent(OrderPaymentScreen.this, PixPaymentScreen.class);
                intent3.putExtra("clientOrder", clientOrder);
                startActivity(intent3);
            }
        });

        buttonSairPaymentScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(OrderPaymentScreen.this, OrderCanceledScreen.class);
                intent4.putExtra("clientOrder", clientOrder);
                startActivity(intent4);
            }
        });
    }

    private void setScreenButtons() {
        buttonCreditPayment = findViewById(R.id.buttonCreditPayment);
        buttonDebitVRPayment = findViewById(R.id.buttonDebitVRPayment);
        buttonPixPayment = findViewById(R.id.buttonPixPayment);
        buttonSairPaymentScreen = findViewById(R.id.buttonSairPayment);
    }
}
