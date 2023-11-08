package com.rcamargo15.icoffeeapp.activities.payments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rcamargo15.icoffeeapp.R;
import com.rcamargo15.icoffeeapp.activities.FinishPaymentScreen;
import com.rcamargo15.icoffeeapp.models.Order;
import com.rcamargo15.icoffeeapp.util.AppUtils;

public class CreditAndDebitPaymentScreen extends AppCompatActivity {

    private TextView creditAndDebitMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AppUtils.esconderBarrasTarefas(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_payment_screen);
        Intent intent = getIntent();
        Order clientOrder = intent.getSerializableExtra("clientOrder", Order.class);
        creditAndDebitMessage = findViewById(R.id.creditAndDebitPaymentMessage);
        creditAndDebitMessage.setText("INSIRA OU APROXIME O SEU CARTÃO PARA DAR INÍCIO AO PAGAMENTO");

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            public void run() {
                startPasswordStep(clientOrder);
            }
        }, 5000);

    }

    private void startPasswordStep(Order clientOrder) {
        creditAndDebitMessage.setText("DIGITE A SENHA PARA EFETUAR O PAGAMENTO");
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                    startPaymentApprovedStep(clientOrder);
            }
        }, 5000);
    }

    private void startPaymentApprovedStep(Order clientOrder) {
        creditAndDebitMessage.setText("AGUARDE UM MOMENTO...");
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(CreditAndDebitPaymentScreen.this, FinishPaymentScreen.class);
                intent.putExtra("clientOrder", clientOrder);
                startActivity(intent);
            }
        }, 5000);
    }
}
