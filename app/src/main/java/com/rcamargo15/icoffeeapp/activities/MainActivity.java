package com.rcamargo15.icoffeeapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.rcamargo15.icoffeeapp.R;
import com.rcamargo15.icoffeeapp.util.AppUtils;

public class MainActivity extends AppCompatActivity {

    private TextView clientNameText;

    private Button startOrderButton;

    private String clientName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.esconderBarrasTarefas(this);
        setContentView(R.layout.activity_main);

        clientNameText = findViewById(R.id.insertClientNameText);
        startOrderButton = findViewById(R.id.startOrderButton);

        startOrderButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    if(clientNameText.getText() == null || clientNameText.getText().toString().trim().equals("")){
                                                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                                                        alertDialog.setTitle("Campo nome vazio");
                                                        alertDialog.setMessage("Você deve inserir o nome antes de darmos início ao atendimento");
                                                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Fechar", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                alertDialog.dismiss();
                                                            }
                                                        });
                                                        alertDialog.show();
                                                    } else {
                                                        clientName = clientNameText.getText().toString();
                                                        Intent intent = new Intent(MainActivity.this, OrderScreen.class);
                                                        intent.putExtra("clientName", clientName.trim().toUpperCase());
                                                        startActivity(intent);
                                                    }
                                                }
                                            }
        );
    }
}
