package com.rcamargo15.icoffeeapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rcamargo15.icoffeeapp.R;
import com.rcamargo15.icoffeeapp.models.Order;
import com.rcamargo15.icoffeeapp.models.Product;
import com.rcamargo15.icoffeeapp.util.AppUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class OrderConfirmationScreen extends AppCompatActivity {

    private int fieldCounter = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation_screen);
        Intent intent = getIntent();
        AppUtils.esconderBarrasTarefas(this);
        Order clientOrder = intent.getSerializableExtra("clientOrder", Order.class);
        HashMap<String, Integer> productQuantities = (HashMap<String, Integer>) intent.getSerializableExtra("productQuantities");
        LinkedHashMap<String, TextView> productNameIdentification = new LinkedHashMap<>();
        LinkedHashMap<String, TextView> productQuantityIdentification = new LinkedHashMap<>();
        LinkedHashMap<String, TextView> productPriceIdentification = new LinkedHashMap<>();
        setScreenButtonsAndView(clientOrder, productNameIdentification, productQuantityIdentification, productPriceIdentification);
        setSelectedProductsOnTheList(clientOrder, productQuantities, productNameIdentification, productQuantityIdentification, productPriceIdentification);
        fieldCounter = 1;

       buttonRetornarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(OrderConfirmationScreen.this, OrderScreen.class);
                intent1.putExtra("clientOrder", clientOrder);
                intent1.putExtra("productQuantities", productQuantities);
                startActivity(intent1);
            }
        });

       buttonSairPedidoNaConfirmacao.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent2 = new Intent(OrderConfirmationScreen.this, OrderCanceledScreen.class);
               startActivity(intent2);
           }
       });


        buttonFinalizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(OrderConfirmationScreen.this, OrderPaymentScreen.class);
                intent3.putExtra("clientOrder", clientOrder);
                startActivity(intent3);
            }
        });

    }

    private void setSelectedProductsOnTheList(Order clientOrder, HashMap<String,
            Integer> productQuantities,
                                              LinkedHashMap<String, TextView> nameFields,
                                              LinkedHashMap<String, TextView> qtdFields,
                                              LinkedHashMap<String, TextView> priceFields) {

        for (int i = 0; i < clientOrder.getProductList().size(); i++) {
            fieldCounter++;

            String nome = "nome".concat(String.valueOf(fieldCounter));
            String qtd = "qtd".concat(String.valueOf(fieldCounter));
            String valorUnit = "valorUnit".concat(String.valueOf(fieldCounter));

            TextView dynamicNameTextView = nameFields.get(nome);
            TextView dynamicQtdTextView = qtdFields.get(qtd);
            TextView dynamicPriceTextView = priceFields.get(valorUnit);
            Product product = clientOrder.getProductList().get(i);

            switch (product.getProductName()) {
                case "Pingado":
                    dynamicNameTextView.setText(product.getProductName());
                    dynamicQtdTextView.setText(productQuantities.get("qtdPingado").toString());
                    dynamicPriceTextView.setText("R$".concat(String.format("%.2f", product.getProductPrice())));
                    break;
                case "Café com Leite":
                    dynamicNameTextView.setText(product.getProductName());
                    dynamicQtdTextView.setText(productQuantities.get("qtdCafeComLeite").toString());
                    dynamicPriceTextView.setText("R$".concat(String.format("%.2f", product.getProductPrice())));
                    break;
                case "Café com Leite e Creme":
                    dynamicNameTextView.setText(product.getProductName());
                    dynamicQtdTextView.setText(productQuantities.get("qtdCafeComLeiteECreme").toString());
                    dynamicPriceTextView.setText("R$".concat(String.format("%.2f", product.getProductPrice())));
                    break;
                case "Cappuccino":
                    dynamicNameTextView.setText(product.getProductName());
                    dynamicQtdTextView.setText(productQuantities.get("qtdCappuccino").toString());
                    dynamicPriceTextView.setText("R$".concat(String.format("%.2f", product.getProductPrice())));
                    break;
                case "Espresso":
                    dynamicNameTextView.setText(product.getProductName());
                    dynamicQtdTextView.setText(productQuantities.get("qtdEspresso").toString());
                    dynamicPriceTextView.setText("R$".concat(String.format("%.2f", product.getProductPrice())));
                    break;
                case "Cookies":
                    dynamicNameTextView.setText(product.getProductName());
                    dynamicQtdTextView.setText(productQuantities.get("qtdCookies").toString());
                    dynamicPriceTextView.setText("R$".concat(String.format("%.2f", product.getProductPrice())));
                    break;
                case "Croissant":
                    dynamicNameTextView.setText(product.getProductName());
                    dynamicQtdTextView.setText(productQuantities.get("qtdCroissant").toString());
                    dynamicPriceTextView.setText("R$".concat(String.format("%.2f", product.getProductPrice())));
                    break;
                case "Bolo de Fubá":
                    dynamicNameTextView.setText(product.getProductName());
                    dynamicQtdTextView.setText(productQuantities.get("qtdBoloFuba").toString());
                    dynamicPriceTextView.setText("R$".concat(String.format("%.2f", product.getProductPrice())));
                    break;
                case "Bolo Floresta Negra":
                    dynamicNameTextView.setText(product.getProductName());
                    dynamicQtdTextView.setText(productQuantities.get("qtdBoloFlorestaNegra").toString());
                    dynamicPriceTextView.setText("R$".concat(String.format("%.2f", product.getProductPrice())));
                    break;
                case "Bolo de Morango":
                    dynamicNameTextView.setText(product.getProductName());
                    dynamicQtdTextView.setText(productQuantities.get("qtdBoloMorango").toString());
                    dynamicPriceTextView.setText("R$".concat(String.format("%.2f", product.getProductPrice())));
                    break;
                default:
                    break;
            }
            ;
        }
    }


    private void setScreenButtonsAndView(Order clientOrder,
                                         LinkedHashMap<String, TextView> productNameIdentification,
                                         LinkedHashMap<String, TextView> productQuantityIdentification,
                                         LinkedHashMap<String, TextView> productPriceIdentification) {
        nome2 = findViewById(R.id.nome2);
        nome3 = findViewById(R.id.nome3);
        nome4 = findViewById(R.id.nome4);
        nome5 = findViewById(R.id.nome5);
        nome6 = findViewById(R.id.nome6);
        nome7 = findViewById(R.id.nome7);
        nome8 = findViewById(R.id.nome8);
        nome9 = findViewById(R.id.nome9);
        nome10 = findViewById(R.id.nome10);
        nome11 = findViewById(R.id.nome11);

        qtd2 = findViewById(R.id.qtd2);
        qtd3 = findViewById(R.id.qtd3);
        qtd4 = findViewById(R.id.qtd4);
        qtd5 = findViewById(R.id.qtd5);
        qtd6 = findViewById(R.id.qtd6);
        qtd7 = findViewById(R.id.qtd7);
        qtd8 = findViewById(R.id.qtd8);
        qtd9 = findViewById(R.id.qtd9);
        qtd10 = findViewById(R.id.qtd10);
        qtd11 = findViewById(R.id.qtd11);

        valorUnit2 = findViewById(R.id.valorUnit2);
        valorUnit3 = findViewById(R.id.valorUnit3);
        valorUnit4 = findViewById(R.id.valorUnit4);
        valorUnit5 = findViewById(R.id.valorUnit5);
        valorUnit6 = findViewById(R.id.valorUnit6);
        valorUnit7 = findViewById(R.id.valorUnit7);
        valorUnit8 = findViewById(R.id.valorUnit8);
        valorUnit9 = findViewById(R.id.valorUnit9);
        valorUnit10 = findViewById(R.id.valorUnit10);
        valorUnit11 = findViewById(R.id.valorUnit11);

        buttonRetornarPedido = findViewById(R.id.buttonRetornarParaPedido);
        buttonFinalizarPedido = findViewById(R.id.finalizarPedidoButton);
        buttonSairPedidoNaConfirmacao = findViewById(R.id.buttonSairPedidoNaConfirmacao);

        String formattedTotalPrice = String.format("%.2f", clientOrder.getTotalOrderPrice());
        formattedTotalPrice = formattedTotalPrice.replace(".", ",");
        valorTotal = findViewById(R.id.valorTotalTextViewField);
        valorTotal.setText("R$".concat(formattedTotalPrice));

        productNameIdentification.put("nome2", nome2);
        productNameIdentification.put("nome3", nome3);
        productNameIdentification.put("nome4", nome4);
        productNameIdentification.put("nome5", nome5);
        productNameIdentification.put("nome6", nome6);
        productNameIdentification.put("nome7", nome7);
        productNameIdentification.put("nome8", nome8);
        productNameIdentification.put("nome9", nome9);
        productNameIdentification.put("nome10", nome10);
        productNameIdentification.put("nome11", nome11);

        productQuantityIdentification.put("qtd2", qtd2);
        productQuantityIdentification.put("qtd3", qtd3);
        productQuantityIdentification.put("qtd4", qtd4);
        productQuantityIdentification.put("qtd5", qtd5);
        productQuantityIdentification.put("qtd6", qtd6);
        productQuantityIdentification.put("qtd7", qtd7);
        productQuantityIdentification.put("qtd8", qtd8);
        productQuantityIdentification.put("qtd9", qtd9);
        productQuantityIdentification.put("qtd10", qtd10);
        productQuantityIdentification.put("qtd11", qtd11);

        productPriceIdentification.put("valorUnit2", valorUnit2);
        productPriceIdentification.put("valorUnit3", valorUnit3);
        productPriceIdentification.put("valorUnit4", valorUnit4);
        productPriceIdentification.put("valorUnit5", valorUnit5);
        productPriceIdentification.put("valorUnit6", valorUnit6);
        productPriceIdentification.put("valorUnit7", valorUnit7);
        productPriceIdentification.put("valorUnit8", valorUnit8);
        productPriceIdentification.put("valorUnit9", valorUnit9);
        productPriceIdentification.put("valorUnit10", valorUnit10);
        productPriceIdentification.put("valorUnit11", valorUnit11);
    }

    private TextView nome2;
    private TextView nome3;
    private TextView nome4;
    private TextView nome5;
    private TextView nome6;
    private TextView nome7;
    private TextView nome8;
    private TextView nome9;
    private TextView nome10;
    private TextView nome11;

    private TextView qtd2;
    private TextView qtd3;
    private TextView qtd4;
    private TextView qtd5;
    private TextView qtd6;
    private TextView qtd7;
    private TextView qtd8;
    private TextView qtd9;
    private TextView qtd10;
    private TextView qtd11;

    private TextView valorUnit2;
    private TextView valorUnit3;
    private TextView valorUnit4;
    private TextView valorUnit5;
    private TextView valorUnit6;
    private TextView valorUnit7;
    private TextView valorUnit8;
    private TextView valorUnit9;
    private TextView valorUnit10;
    private TextView valorUnit11;

    private TextView valorTotal;

    private Button buttonFinalizarPedido;

    private Button buttonRetornarPedido;

    private Button buttonSairPedidoNaConfirmacao;

}
