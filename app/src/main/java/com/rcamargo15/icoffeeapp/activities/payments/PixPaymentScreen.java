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
import com.rcamargo15.icoffeeapp.models.enums.PaymentEnum;
import com.rcamargo15.icoffeeapp.util.AppUtils;

public class PixPaymentScreen extends AppCompatActivity {

    private TextView pixScreenMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AppUtils.esconderBarrasTarefas(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pix_payment_screen);
        Intent intent = getIntent();
        Order clientOrder = intent.getSerializableExtra("clientOrder", Order.class);
        clientOrder.setPaymentType(PaymentEnum.PIX);
        pixScreenMessage = findViewById(R.id.pixScreenMessage);
        pixScreenMessage.setText("ESCANEIE O QR CODE PARA EFETUAR O PAGAMENTO ATRAVÉS DO PIX");

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                pixScreenMessage.setText("IDENTIFICANDO PAGAMENTO...");
                loadFinishedPaymentScreen(clientOrder);
            }
        }, 5000);
    }

    private void loadFinishedPaymentScreen(Order clientOrder) {
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PixPaymentScreen.this, FinishPaymentScreen.class);
                intent.putExtra("clientOrder", clientOrder);
                startActivity(intent);
            }
        }, 5000);
    }
}
