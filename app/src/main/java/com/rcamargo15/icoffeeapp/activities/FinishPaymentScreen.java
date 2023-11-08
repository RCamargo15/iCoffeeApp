package com.rcamargo15.icoffeeapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.rcamargo15.icoffeeapp.R;
import com.rcamargo15.icoffeeapp.models.Order;
import com.rcamargo15.icoffeeapp.repositories.memDatabase.OrderDao;
import com.rcamargo15.icoffeeapp.repositories.memDatabase.OrderDatabase;
import com.rcamargo15.icoffeeapp.repositories.memDatabase.OrderEntity;
import com.rcamargo15.icoffeeapp.repositories.memDatabase.OrderRepository;
import com.rcamargo15.icoffeeapp.util.AppUtils;

import java.time.format.DateTimeFormatter;
import java.util.Random;

public class FinishPaymentScreen extends AppCompatActivity {

    private TextView finishedPaymentMessageTextView;
    private OrderRepository orderRepository = new OrderRepository();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_payment_screen);
        AppUtils.esconderBarrasTarefas(this);
        Intent intent = getIntent();
        Order clientOrder = intent.getSerializableExtra("clientOrder", Order.class);

        Random rand = new Random();
        int x = rand.nextInt(1000);

        clientOrder.setOrderNumber(x);
        finishedPaymentMessageTextView = findViewById(R.id.finishedPaymentMessage);
        StringBuilder finishedMessage = new StringBuilder("O SEU PAGAMENTO FOI APROVADO\nSUA SENHA É ");
        finishedMessage.append(x);
        finishedMessage.append("\nOBRIGADO PELA PREFERÊNCIA!");
        finishedPaymentMessageTextView.setText(finishedMessage.toString());

        orderRepository.saveOrderIntoMemDatabase(clientOrder, this);
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(FinishPaymentScreen.this, MainActivity.class);
                startActivity(intent);
            }
        }, 4000);
    }
}
