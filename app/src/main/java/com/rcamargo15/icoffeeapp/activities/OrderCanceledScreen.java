package com.rcamargo15.icoffeeapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.rcamargo15.icoffeeapp.R;
import com.rcamargo15.icoffeeapp.util.AppUtils;

public class OrderCanceledScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.esconderBarrasTarefas(this);
        setContentView(R.layout.activity_order_canceled_screen);
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(OrderCanceledScreen.this, MainActivity.class);
                startActivity(intent);
            }
        }, 5000);
    }
}

